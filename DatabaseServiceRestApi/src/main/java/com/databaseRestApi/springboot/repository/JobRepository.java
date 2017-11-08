package com.databaseRestApi.springboot.repository;

import com.databaseRestApi.springboot.model.Job;

import java.util.List;

public interface JobRepository {

    List<Job> listAll();
    void save(Job job);
    void delete(Job job);
    void update(Job job);
}
