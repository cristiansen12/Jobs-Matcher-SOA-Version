package com.jobRestApi.springboot.dto;

public class Job {

    private String company;
    private String title;
    private String description;


    public Job() { }

    public Job( String company, String title, String description){

        this.company = company;
        this.title = title;
        this.description = description;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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


    @Override
    public String toString() {
        return "Job [title=" + title + ", company=" + company
                + ", description=" + description + "]";
    }

}