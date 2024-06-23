package com.example.firebase.Model;

public class Tracking {
    private long trackingId;
    private String activity;
    private boolean status;
    private long petId;

    public Tracking(long trackingId, String activity, boolean status, long petId) {
        this.trackingId = trackingId;
        this.activity = activity;
        this.status = status;
        this.petId = petId;
    }

    public long getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(long trackingId) {
        this.trackingId = trackingId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public Tracking() {

    }
}
