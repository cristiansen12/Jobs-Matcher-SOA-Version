package com.jobRestApi.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication(scanBasePackages={"com.jobRestApi.springboot"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class SpringRestJobApi {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestJobApi.class, args);
    }
}
