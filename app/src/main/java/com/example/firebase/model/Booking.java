package com.example.firebase.model;

import java.util.Date;

public class Booking {
    private long bookingId;
    private long userId; // Foreign key to User
    private long petId; // Foreign key to Pet
    private long serviceId; // Foreign key to Service
    private Date startDate;
    private Date endDate;
    private String status;



    // Default constructor (required for Firebase)
    public Booking() {
    }

    // Parameterized constructor
    public Booking(long bookingId, long userId, long petId, long serviceId, Date startDate, Date endDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.petId = petId;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getter and setter methods


    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
