package com.jobRestApi.springboot.controller;

import com.jobRestApi.springboot.service.Impl.JobServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JobRestApiController {

    public static final Logger logger = LoggerFactory.getLogger(JobRestApiController.class);

    @Autowired
    JobServiceImpl jobService;

    @RequestMapping(value = "/job/{idUser}", method = RequestMethod.GET)
    public ResponseEntity<?> getJob(@PathVariable("idUser") long idUser) {
        String job = jobService.getBestJob(jobService.getProfileFromIdUser(idUser));
        System.out.println(job);
        if (job.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<String>(job, HttpStatus.OK);
    }

}


