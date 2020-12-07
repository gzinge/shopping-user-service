package com.shopping.userservice.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="common_seq")
    @SequenceGenerator(name="common_seq", sequenceName="common_sequence", allocationSize=20)
    private Long id;
    @NotNull()
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String mobileNumber;
    @Column(nullable = false)
    private String password;
    private String role;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
