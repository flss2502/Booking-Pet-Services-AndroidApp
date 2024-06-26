package com.example.firebase.model;

import java.util.Date;

public class Room {
    private long id;
    private long petId;
    private Date startDate;
    private Date endDate;
    private String roomName;

    // Default constructor (required for Firebase)
    public Room() {
    }

    // Parameterized constructor
    public Room(long id, long petId, Date startDate, Date endDate, String roomName) {
        this.id = id;
        this.petId = petId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomName = roomName;
    }

    // Getter and setter methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
