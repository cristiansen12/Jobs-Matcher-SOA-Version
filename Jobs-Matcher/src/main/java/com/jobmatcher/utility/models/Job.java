package com.jobmatcher.utility.models;

public class Job {
    private String id;
    private String title;
    private String description;
    private String company;

    public Job() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Job other = (Job) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Job [id=" + id + ", title=" + title + ", company=" + company
                + ", description=" + description + "]";
    }
}
