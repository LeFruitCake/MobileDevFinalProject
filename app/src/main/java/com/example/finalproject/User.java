package com.example.finalproject;

public class User {
    String fullName;
    String photoURL;
    String uid;
    String phoneNumber;

    public User() {
    }

    public User(String fullName, String photoURL, String uid, String phoneNumber) {
        this.fullName = fullName;
        this.photoURL = photoURL;
        this.uid = uid;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
