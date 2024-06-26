package com.example.firebase.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private long userId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Role role;


    // Constructor
    public User(long userId, String fullName, String email, String password, String phone, String address, Role role) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public User() {

    }

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Parcelable implementation
    protected User(Parcel in) {
        userId = in.readLong();
        fullName = in.readString();
        email = in.readString();
        password = in.readString();
        phone = in.readString();
        address = in.readString();
        role = Role.valueOf(in.readString()); // Assuming Role enum name is written to Parcel
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(userId);
        dest.writeString(fullName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(role.name()); // Write enum name to Parcel
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
