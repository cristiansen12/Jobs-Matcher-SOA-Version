package com.databaseRestApi.springboot.service;

import com.databaseRestApi.springboot.model.Job;

import java.util.List;

public interface JobService {

    Job findById(long id);

    Job findByTitle(String title);

    void saveJob(Job job);

    void updateJob(Job job);

    void deleteJobById(long id);

    List<Job> findAllJobs();

    void deleteAllJobs();

    boolean isJobExist(Job job);
}
