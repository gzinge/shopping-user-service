package com.shopping.userservice.api;

import com.shopping.userservice.model.User;
import com.shopping.userservice.model.UserLoginDetails;
import com.shopping.userservice.model.UserWrapper;
import com.shopping.userservice.service.UserBSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserBSI userBS;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user) throws Exception{
        try {
            int i = userBS.createUser(user);
        }catch (Exception ex) {
            logger.error("Exception occurred while creating user", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("User Created Successfully", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateUser(User user) throws Exception{
        try {
            int i = userBS.updateUser(user);
        }catch (Exception ex) {
            logger.error("Exception occurred while updating user", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("User Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/userId/{userId}")
    public ResponseEntity removeUser(@PathVariable("userId") Long userId) throws Exception{
        try {
            int i = userBS.removeUser(userId);
        }catch (Exception ex) {
            logger.error("Exception occurred while removing user", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("User Removed Successfully", HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity getUserById(@PathVariable("userId") Long userId) throws Exception{
        User user = null;
        try {
            user = userBS.getUserById(userId);
        }catch (Exception ex) {
            logger.error("Exception occurred while getting user for id: "+ userId, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable("email") String email) throws Exception{
        User user = null;
        try {
            user = userBS.getUserByEmail(email);
        }catch (Exception ex) {
            logger.error("Exception occurred while getting user for email: "+ email, ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @PostMapping("/validateUser")
    public ResponseEntity validateUser(@RequestBody UserLoginDetails user) throws Exception{
        boolean isValidUser = false;
        try {
            isValidUser = userBS.validateUser(user.getEmail(), user.getPassword());
        }catch (Exception ex) {
            logger.error("Exception occurred while validating user for email: "+ user.getEmail(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(isValidUser, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers() throws Exception {
        UserWrapper wrapper = new UserWrapper();
        try {
            wrapper.setUserList(userBS.getAllUsers());
        }catch (Exception ex) {
            logger.error("Exception occurred while getting all users ", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(wrapper, HttpStatus.OK);
    }

}
