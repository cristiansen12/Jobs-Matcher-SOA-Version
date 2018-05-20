package com.jobRestApi.springboot.service.Impl;

import com.jobRestApi.springboot.dto.Profile;
import com.jobRestApi.springboot.service.JobService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import com.jobRestApi.springboot.utility.JobParser;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service("jobService")
public class JobServiceImpl implements JobService{

    class ValueComparator implements Comparator<Map.Entry<String,Integer>> {

        /**
         * Implements descending order.
         */
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o1.getValue() < o2.getValue()) {
                return 1;
            } else if (o1.getValue() > o2.getValue()) {
                return -1;
            }
            return 0;
        }

    }

    private String getKeyWords(String words){

        String[] wordsInCV = words.split(",");
        String[] connectors = {"present", "used", "project", "Degree", "order", "a", "about", "also", "an", "and", "are", "as", "as soon", "at", "by", "but", "for", "from", "if", "in", "is", "just", "of", "on", "or", "than", "the", "then", "to", "when", "while", "with", "000"};
        List<String> connectorList = Arrays.asList(connectors);

        HashMap<String, Integer> wordsCounter = new HashMap<>();

        for(String word:wordsInCV) {
            if (!connectorList.contains(word.toLowerCase()) && !(word.length() == 1)) {
                if (wordsCounter.containsKey(word))
                    wordsCounter.replace(word, wordsCounter.get(word) + 1);
                else
                    wordsCounter.put(word, 1);
            }
        }

        List<Map.Entry<String, Integer>> l = new ArrayList(wordsCounter.entrySet());

        Collections.sort(l, new ValueComparator());
        l = l.subList(0,5);

        String result = "";
        for(int i=0; i<l.size() - 1; i++) {
            result += l.get(i).getKey() + ",";
        }
        result += l.get(l.size() - 1).getKey();

        return result;
    }

    /* Utility function to get max of 2 integers */
    private int max(int a, int b)
    {
        return (a > b)? a : b;
    }

    private int longestCommonSubsequence(String[] profileCV, String[] jobDescription, int cvLength, int jobLength ) {

        int L[][] = new int[cvLength+1][jobLength+1];

        for (int i=0; i<=cvLength; i++)
        {
            for (int j=0; j<=jobLength; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (profileCV[i-1].equals(jobDescription[j-1]))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = max(L[i-1][j], L[i][j-1]);
            }
        }
        return L[cvLength][jobLength];
    }



    public String getProfileFromIdUser(long idUser){

        String profileUrl = "http://localhost:8083/DBServiceRestApi/api/profile/" + idUser;
        System.out.println("Testing getJob API for profile----------");
        RestTemplate restTemplateJS = new RestTemplate();
        Profile profile = restTemplateJS.getForObject(profileUrl, Profile.class);
        System.out.println("\nContent: " + profile.getContent() );
        return profile.getContent();

    }

    public String getBestJob(String key){

        String keyWords = this.getKeyWords(key);

        System.out.println(keyWords);

        //This will be in MatchingService *****
        String url = "https://authenticjobs.com/api/?api_key=d3bc95973199abbd28fd6b587e09645d&method=aj.jobs.search&keywords="+keyWords+"&format=json&perpage=5";
        //This will be in MatchingService *****

        String result = "";
        URL obj = null;
        StringBuffer response = new StringBuffer();
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            if (response == null) return null;

            String jsonTxt = response.toString();
            jsonTxt = response.toString();
            JSONObject json = null;
            try {
                json = new JSONObject(jsonTxt);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject listings = json.getJSONObject("listings");
            JSONArray listing = listings.getJSONArray("listing");
            for(int i=0; i<listing.length(); i++){
                JSONObject job = listing.getJSONObject(i);
                JobParser jp = new JobParser();
                jp.setId(job.getString("id"));
                jp.setTitle(job.getString("title"));
                jp.setDescription(job.getString("description"));
                jp.setCompany(job.getJSONObject("company").getString("name"));
                jobs.add(jp);
            }

            String[] wordsFromInputFile = key.split(",");

            for(int i=0; i<jobs.size(); i++){
                String[] wordsInJobDescription = jobs.get(i).getDescription().split("[^a-zA-Z']+");
                similarityMap.put(jobs.get(i).getId(), longestCommonSubsequence(wordsFromInputFile, wordsInJobDescription, wordsFromInputFile.length, wordsInJobDescription.length));

            }

            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : similarityMap.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }

            for (int i=0; i< jobs.size();i++){
                if (jobs.get(i).getId().equals(maxEntry.getKey())){
                    String description = Jsoup.parse(jobs.get(i).getDescription()).text();
                    result += "{ \"company\": \"" + jobs.get(i).getCompany() + "\" , \""
                            + "title\": \"" + jobs.get(i).getTitle() + "\" , \""
                            + "description\": \"" + description + "\"}";
                    break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
