package com.databaseRestApi.springboot.repository.impl;

import com.databaseRestApi.springboot.repository.JobRepository;
import com.databaseRestApi.springboot.model.Job;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JobRepositoryImpl implements JobRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Make a user managed and persistent.
     *
     * @param job
     */
    @Override
    public void save(Job job) {
        entityManager.persist(job);
    }

    /**
     * Make a job managed and persistent.
     *
     * @param job
     */
    @Override
    public void delete(Job job) {
        entityManager.remove(job);
    }

    @Override
    public List<Job> listAll(){
        return entityManager.createQuery("select j from Job j")
                .getResultList();
    }

    @Override
    public void update(Job job){


    }
}
