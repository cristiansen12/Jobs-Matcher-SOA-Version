package com.databaseRestApi.springboot.service;

import com.databaseRestApi.springboot.model.Profile;

import java.util.List;

public interface ProfileService {



    Profile findByUserId(long userId);

    void saveProfile(Profile profile);

    void updateProfile(Profile profile);

    void deleteProfileById(long userId);

    List<Profile> findAllProfiles();

    void deleteAllProfiles();

    boolean isProfileExist(Profile profile);
}
