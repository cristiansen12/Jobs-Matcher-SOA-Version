package com.jobRestApi.springboot.service;

import com.jobRestApi.springboot.utility.JobParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JobService {


    public static final int  MAX_LENGTH = 99999;
    public List<JobParser> jobs = new ArrayList<>();
    public Map<String, Integer> similarityMap = new HashMap<>();


}
