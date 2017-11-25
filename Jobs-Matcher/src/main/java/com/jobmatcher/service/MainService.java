package com.jobmatcher.service;

import com.jobmatcher.utility.models.Job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gevlad on 08-Jan-17.
 */
public interface MainService {

    public static final int  MAX_LENGTH = 99999;
    public List<Job> jobs = new ArrayList<>();
    public Map<String, Integer> similarityMap = new HashMap<>();
    public static final String DATABASE_REST_SERVICE_URL = "http://localhost:8083/DBServiceRestApi/api";
    public static final String JOB_REST_SERVICE_URL = "http://localhost:8082/JobServiceRestApi/api";

    String getJobFromAuthenticJobs(String key);
    String getWordsFromCVFile(String filePath);
    String getWordsFromLinkedinFile(String filePath);
}
