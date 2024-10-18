package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;



    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

}
