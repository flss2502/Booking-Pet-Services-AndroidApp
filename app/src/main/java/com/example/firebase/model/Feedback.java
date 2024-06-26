package com.example.firebase.model;

import java.util.Date;

public class Feedback {
    private long userId;
    private String description;
    private String url;
    private Date time;

    // Default constructor (required for Firebase)
    public Feedback() {
    }

    // Parameterized constructor
    public Feedback(long userId, String description, String url, Date time) {
        this.userId = userId;
        this.description = description;
        this.url = url;
        this.time = time;
    }

    // Getter and setter methods
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
