package com.example.firebase.Model;

public class Services {
    private long serviceId;
    private String serviceName;
    private String description;
    private double price;
    private String serviceImageUrl;

    // Constructors
    public Services() {
        // Default constructor required by Firebase
    }



    public Services(long serviceId, String serviceImageUrl, String serviceName, String description, double price) {
        this.serviceId = serviceId;
        this.serviceImageUrl = serviceImageUrl;
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
    }

    public String getServiceImageUrl() {
        return serviceImageUrl;
    }

    public void setServiceImageUrl(String serviceImageUrl) {
        this.serviceImageUrl = serviceImageUrl;
    }

    // Getters and Setters
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
}
