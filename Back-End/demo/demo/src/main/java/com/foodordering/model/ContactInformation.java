package com.foodordering.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable

public class ContactInformation {
    private String phone;
    private String email;

    // Getters and Setters
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
