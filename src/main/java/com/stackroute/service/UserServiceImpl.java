package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {  // userservice implementation class implements userservice interface and overrides both the methods

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;               // at runtime spring will provide this service a userRepository object via constructor dependency injection
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {

        if(userRepository.existsById(user.getId()))
        {
            throw new UserAlreadyExistsException("User Already Exists");
        }
        User saveUser = userRepository.save(user);// this save method comes from the jpa repository interface that our user repository interface extends
        if(saveUser==null)
            throw new UserAlreadyExistsException("User Already Exists");
        return saveUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
