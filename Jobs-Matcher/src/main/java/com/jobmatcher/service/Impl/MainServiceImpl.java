package com.jobmatcher.service.Impl;

import com.jobmatcher.service.SignInService;
import com.jobmatcher.utility.models.Job;
import com.jobmatcher.utility.models.Profile;
import com.jobmatcher.utility.parsers.CVParser;
import com.jobmatcher.service.MainService;
import com.jobmatcher.utility.parsers.LinkedinParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.*;

/**
 * Created by gevlad on 08-Jan-17.
 */
@Service
public class MainServiceImpl implements MainService {


    @Autowired
    SignInService signInService;

    public String getJobFromAuthenticJobs(String key) {

        String result = "";

        // Take input from PDF and make it string with "," in between words
        System.out.println(key);

        String[] profileText = key.split(":");

        // Post content to DatabaseService REST api

        System.out.println("Testing create Profile API----------");
        RestTemplate restTemplateDB = new RestTemplate();
        Profile profile = new Profile(Long.valueOf(profileText[0]).longValue(), profileText[1]);
        URI uri = restTemplateDB.postForLocation(DATABASE_REST_SERVICE_URL+"/profile/", profile, Profile.class);
        System.out.println("Location : "+uri.toASCIIString());


        // Get Job from JobsService REST api

        System.out.println("Testing getJob API----------");
        RestTemplate restTemplateJS = new RestTemplate();
        Job job = restTemplateJS.getForObject(JOB_REST_SERVICE_URL+"/job/"+profileText[0], Job.class);
        System.out.println(job.toString());

        return result;
    }

    public String getWordsFromCVFile(String filePath) {

        String[] keyWords = new String[MAX_LENGTH];
        String userId = signInService.getIdForCurrentUser();
        String key = "";

        keyWords = CVParser.parseFile(filePath);
        key += userId + ":";

        for (String s : keyWords) {
            key += s + ",";
        }

        key = key.substring(0, key.length()-1);
        return key;
    }

    public String getWordsFromLinkedinFile(String filePath) {

        String[] keyWords = new String[MAX_LENGTH];

        keyWords = LinkedinParser.parseFile(filePath);
        String key = "";

        for (String s : keyWords) {
            key += s + ",";
        }
        key = key.substring(0, key.length()-1);
        return key;
    }

}


