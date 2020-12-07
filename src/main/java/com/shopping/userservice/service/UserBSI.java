package com.shopping.userservice.service;

import com.shopping.userservice.model.User;
import com.shopping.userservice.model.UserWrapper;

import java.util.List;

public interface UserBSI {

    public int createUser(User user) throws Exception;

    public int updateUser(User user) throws Exception;

    public int removeUser(Long userId) throws Exception;

    public User getUserById(Long userId) throws Exception;

    public User getUserByEmail(String email) throws Exception;

    public List<User> getAllUsers() throws Exception;

    public boolean validateUser(String email, String password) throws Exception;

}
