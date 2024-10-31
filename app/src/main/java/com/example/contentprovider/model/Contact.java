package com.example.contentprovider.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String Phone;
    private String name;

    public Contact(String phone, String name) {
        Phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tên: "+name+"\nPhone: "+this.Phone;
    }
}
