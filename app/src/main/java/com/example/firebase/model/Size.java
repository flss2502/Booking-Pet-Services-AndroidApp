package com.example.firebase.model;

public class Size {
    private long sizeId;
    private String sizeName;
    private String description;

    public Size(long sizeId, String sizeName, String description) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.description = description;
    }

    public Size() {

    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
