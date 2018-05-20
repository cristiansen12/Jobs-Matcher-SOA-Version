package com.databaseRestApi.springboot.service;

import com.databaseRestApi.springboot.model.Profile;

import java.util.List;

public interface ProfileService {



    Profile findByUserId(long user_id);

    void saveProfile(Profile profile);

    void updateProfile(Profile profile);

    void deleteProfileById(long user_id);

    List<Profile> findAllProfiles();

    void deleteAllProfiles();

    boolean isProfileExist(Profile profile);
}
