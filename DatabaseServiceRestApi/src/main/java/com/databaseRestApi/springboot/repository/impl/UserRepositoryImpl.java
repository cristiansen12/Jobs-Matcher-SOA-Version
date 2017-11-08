package com.databaseRestApi.springboot.repository.impl;

import com.databaseRestApi.springboot.model.User;
import com.databaseRestApi.springboot.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Make a user managed and persistent.
     *
     * @param user
     */
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    /**
     * Make a user managed and persistent.
     *
     * @param user
     */
    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public List<User> listAll(){
        return entityManager.createQuery("select u from User u")
                .getResultList();
    }

    @Override
    public void update(User user){


    }
}
