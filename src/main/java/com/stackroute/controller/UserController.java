package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class UserController {   // @RestController annotation is a responsible for returning the data by directly writing the data into http response as JSON

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("user")  //resource name is user
    public ResponseEntity<?> saveUser(@RequestBody User user){     // handler method to handle post request to save a user
        ResponseEntity responseEntity;
        try{
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);        // in the try block we created a responseEntity object (the first parameter is a string message to be sent back as response and the second parameter is the http status code)
        }
        catch(UserAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;          // we returned a responseEntity from saveUser method in spring REST a respoonseEntity represents a complete http response including response body, status code and headers.
    }

    @GetMapping("user")// handler method to retrieve all users
    public ResponseEntity<?> getAllUsers(){
        ResponseEntity responseEntity;
        try{
            userService.getAllUsers();
            responseEntity = new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
        }
        catch(Exception ex1){
            responseEntity = new ResponseEntity<String>(ex1.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
