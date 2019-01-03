package com.foodx.nsh.model;

/**
 * Created by ThisIsNSH on 1/5/2019.
 */

public class Hotel {

    String name;
    String image;
    String contact;
    String address;

    public Hotel(String name, String image, String contact, String address) {
        this.name = name;
        this.image = image;
        this.contact = contact;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
