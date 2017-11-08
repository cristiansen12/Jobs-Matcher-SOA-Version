package com.jobmatcher.service.Impl;

import com.jobmatcher.service.SignInService;
import org.springframework.stereotype.Service;

@Service
public class SignInServiceImpl implements SignInService{


    @Override
    public String getIdForCurrentUser(){
        return "1";
    }
}
