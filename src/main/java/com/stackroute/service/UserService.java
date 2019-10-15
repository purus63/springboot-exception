package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserService {

    public User saveUser(User user) throws UserAlreadyExistsException;
    public List<User> getAllUsers();  // returns all user objects stored in the database as a list of user objects

}
