package com.example.firebase.model;

import java.util.Date;

public class Feedback {

    private long feedbackId;
    private long userId;
    private String description;
    private double rating;

    private Date time;

    // Default constructor (required for Firebase)
    public Feedback() {
    }

    public Feedback(long feedbackId, long userId, String description, double rating, Date time) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.description = description;
        this.rating = rating;
        this.time = time;
    }

    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
