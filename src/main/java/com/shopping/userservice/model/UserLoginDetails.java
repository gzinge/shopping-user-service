package com.shopping.userservice.model;

public class UserLoginDetails {

    private String email;
    private String password;

    public UserLoginDetails() {
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
}
