package com.databaseRestApi.springboot.repository.impl;

import com.databaseRestApi.springboot.model.Profile;
import com.databaseRestApi.springboot.repository.ProfileRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository{

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Make a user managed and persistent.
     *
     * @param profile
     */
    @Override
    public void save(Profile profile) {
        entityManager.persist(profile);
    }

    /**
     * Make a job managed and persistent.
     *
     * @param profile
     */
    @Override
    public void delete(Profile profile) {
        entityManager.remove(profile);
    }

    @Override
    public List<Profile> listAll(){
        return entityManager.createQuery("select p from Profile p")
                .getResultList();
    }

    @Override
    public void update(Profile profile){


    }
}
