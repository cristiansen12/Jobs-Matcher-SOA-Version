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
    public Profile findByUserId(long user_id) {
        List<Profile> profiles = profileRepository.listAll();
        for (Profile p:profiles){
            if (p.getUserId() == user_id)
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
    public void deleteProfileById(long user_id) {
        profileRepository.delete(this.findByUserId(user_id));
    }

    @Transactional
    public boolean isProfileExist(Profile profile) {
        return false; //findByUserId(profile.getUserId())!=null;
    }

    @Transactional
    public void deleteAllProfiles(){
        List<Profile> profiles = profileRepository.listAll();
        for (Profile p:profiles){
            profileRepository.delete(p);
        }
    }
}
