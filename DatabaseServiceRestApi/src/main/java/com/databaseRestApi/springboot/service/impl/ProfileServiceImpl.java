package com.databaseRestApi.springboot.service.impl;

import com.databaseRestApi.springboot.model.Profile;
import com.databaseRestApi.springboot.repository.ProfileRepository;
import com.databaseRestApi.springboot.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Override
    @Transactional
    public List<Profile> findAllProfiles() {
        return profileRepository.listAll();
    }

    @Override
    @Transactional
    public Profile findByUserId(long userId) {
        List<Profile> profiles = profileRepository.listAll();
        for (Profile p:profiles){
            if (p.getUserId() == userId)
                return p;
        }
        return null;
    }

    @Transactional
    public void saveProfile(Profile profile) { profileRepository.save(profile); }

    @Transactional
    public void updateProfile(Profile profile) {
        profileRepository.update(profile);
    }

    @Transactional
    public void deleteProfileById(long userId) {
        profileRepository.delete(this.findByUserId(userId));
    }

    @Transactional
    public boolean isProfileExist(Profile profile) {
        return findByUserId(profile.getUserId())!=null;
    }

    @Transactional
    public void deleteAllProfiles(){
        List<Profile> profiles = profileRepository.listAll();
        for (Profile p:profiles){
            profileRepository.delete(p);
        }
    }
}
