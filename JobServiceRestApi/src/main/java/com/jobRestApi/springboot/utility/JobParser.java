package com.jobRestApi.springboot.utility;

public class JobParser {
    private String id;
    private String title;
    private String description;
    private String company;


    public JobParser() {
        this.id = null;
        this.title = null;
        this.description = null;
        this.company = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
