package com.databaseRestApi.springboot.service.impl;

import com.databaseRestApi.springboot.model.Job;
import com.databaseRestApi.springboot.repository.JobRepository;
import com.databaseRestApi.springboot.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository;

    @Override
    @Transactional
    public List<Job> findAllJobs() {
        return jobRepository.listAll();
    }

    @Override
    @Transactional
    public Job findById(long id) {
        List<Job> jobs = jobRepository.listAll();
        for (Job j:jobs){
            if (j.getId() == id)
                return j;
        }
        return null;
    }

    @Transactional
    public Job findByTitle(String title) {
        List<Job> job = jobRepository.listAll();
        for (Job j:job){
            if (j.getTitle().equals(title))
                return j;
        }
        return null;
    }

    @Transactional
    public Job findByCompany(String title) {
        List<Job> job = jobRepository.listAll();
        for (Job j:job){
            if (j.getCompany().equals(title))
                return j;
        }
        return null;
    }

    @Transactional
    public void saveJob(Job Job) { jobRepository.save(Job); }

    @Transactional
    public void updateJob(Job Job) {
        jobRepository.update(Job);
    }

    @Transactional
    public void deleteJobById(long id) {
        jobRepository.delete(this.findById(id));
    }

    @Transactional
    public boolean isJobExist(Job job) {
        return (findByTitle(job.getTitle())!=null & findByCompany(job.getCompany())!=null);
    }

    @Transactional
    public void deleteAllJobs(){
        List<Job> jobs = jobRepository.listAll();
        for (Job j:jobs){
            jobRepository.delete(j);
        }
    }
}
