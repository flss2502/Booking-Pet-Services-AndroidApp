package com.example.firebase.model;

public class Pet {
    private long petId;
    private long userId;
    private String petName;
    private String petType;

    private int age;
    private String gender;
    private long sizeId;

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

    public Pet(long petId, long userId, String petName, String petType, int age, String gender, long sizeId) {
        this.petId = petId;
        this.userId = userId;
        this.petName = petName;
        this.petType = petType;
        this.age = age;
        this.gender = gender;
        this.sizeId = sizeId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    public Pet() {

    }
}
