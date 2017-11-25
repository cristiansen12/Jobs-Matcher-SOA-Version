package com.databaseRestApi.springboot.repository;

import com.databaseRestApi.springboot.model.Job;
import com.databaseRestApi.springboot.model.Profile;

import java.util.List;

public interface ProfileRepository {

    List<Profile> listAll();
    void save(Profile profile);
    void delete(Profile profile);
    void update(Profile profile);
}
