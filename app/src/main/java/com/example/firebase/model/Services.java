package com.example.firebase.model;

public class Services {
    private long serviceId;
    private String serviceName;
    private String description;
    private double price;
    private String imageService;

    // Constructors
    public Services() {
        // Default constructor required by Firebase
    }



    public Services(long serviceId, String serviceName, String description, double price, String imageService) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
        this.imageService = imageService;
    }

    public String getImageService() {
        return imageService;
    }

    public void setImageService(String imageService) {
        this.imageService = imageService;
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
