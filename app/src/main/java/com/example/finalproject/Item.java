package com.example.finalproject;

public class Item {
    private String Name,Status,image,uid,photoURL,phoneNumber;
    private int Price;

    public Item() {
    }

    public Item(String name, String status, String image, String uid, String photoURL, String phoneNumber, int price) {
        Name = name;
        Status = status;
        this.image = image;
        this.uid = uid;
        this.photoURL = photoURL;
        this.phoneNumber = phoneNumber;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
