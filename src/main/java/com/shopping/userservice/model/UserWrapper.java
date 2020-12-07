package com.shopping.userservice.model;

import java.util.List;

public class UserWrapper {
    private List<User> userList;

    public UserWrapper() {
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
