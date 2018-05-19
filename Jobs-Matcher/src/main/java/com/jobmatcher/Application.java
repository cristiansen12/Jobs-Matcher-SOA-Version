package com.jobmatcher;

import com.jobmatcher.service.UploadCVService;
import com.jobmatcher.service.UploadLinkedinService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    public static void main(String[] argus) {

        SpringApplication.run(Application.class, argus);

    }
    @Bean
    CommandLineRunner init() {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UploadCVService.PATH));

            Files.createDirectory(Paths.get(UploadCVService.PATH));
            FileSystemUtils.deleteRecursively(new File(UploadLinkedinService.PATH));

            Files.createDirectory(Paths.get(UploadLinkedinService.PATH));
        };
    }
}
