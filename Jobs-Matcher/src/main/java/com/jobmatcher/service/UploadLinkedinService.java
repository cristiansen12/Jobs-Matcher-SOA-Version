package com.jobmatcher.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by gevlad on 15-Jan-17.
 */
public interface UploadLinkedinService {

    String PATH = "uploadLinkedin";

    String save(MultipartFile file);
}
