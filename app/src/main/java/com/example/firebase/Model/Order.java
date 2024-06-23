package com.example.firebase.Model;

import java.util.Date;

public class Order {
    private long orderId;
    private Date date;
    private boolean status;
    private long petId;
    private long serviceId;
    private long userId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Order(long orderId, Date date, boolean status, long petId, long serviceId, long userId) {
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.petId = petId;
        this.serviceId = serviceId;
        this.userId = userId;
    }

    public Order() {

    }

}
