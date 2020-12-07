package com.shopping.userservice.service;

import com.shopping.userservice.model.User;
import com.shopping.userservice.model.UserWrapper;
import com.shopping.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserBS implements UserBSI {

    Logger logger = LoggerFactory.getLogger(UserBS.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public int createUser(User user) throws Exception {
        if(user != null) {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setMobileNumber(user.getMobileNumber());
            newUser.setRole(user.getRole());
            newUser.setPassword(user.getPassword());
            userRepository.save(newUser);
            logger.info("------------User created with id : "+ newUser.getId());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUser(User user) throws Exception {
        if(user != null) {
            User userToUpdate =  getUserById(user.getId());
            if(userToUpdate != null) {
                userToUpdate.setName(user.getName());
                userToUpdate.setEmail(user.getEmail());
                userToUpdate.setMobileNumber(user.getMobileNumber());
                userToUpdate.setRole(user.getRole());
                userToUpdate.setPassword(user.getPassword());
                userRepository.save(userToUpdate);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int removeUser(Long userId) throws Exception {
        if (userId != null) {
            User userToDelete = getUserById(userId);
            if (userToDelete != null) {
                userRepository.delete(userToDelete);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public User getUserById(Long userId) throws Exception {
        User user = null;
        if(userId != null) {
           Optional<User> opt = userRepository.findById(userId);
           if(opt.isPresent()) {
               user = opt.get();
           }
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        if(email != null) {
            Optional<User> opt = userRepository.findAll()
                    .stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst();
            if(opt.isPresent()) {
                return opt.get();
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public boolean validateUser(String email, String password) throws Exception {
        User user = getUserByEmail(email);
        return (user != null && user.getPassword().equals(password));
    }
}
