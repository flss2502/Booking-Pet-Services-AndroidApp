package com.example.firebase.model;

public class Pet {
    private long petId;
    private long userId;
    private String petName;
    private String petType;

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Pet(long petId, long userId, String petName, String petType) {
        this.petId = petId;
        this.userId = userId;
        this.petName = petName;
        this.petType = petType;
    }

    public Pet() {

    }
}
