package com.edigest.journalApp.controller;

import com.edigest.journalApp.api.response.QuoteResponse;
import com.edigest.journalApp.api.response.WeatherResponse;
import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import com.edigest.journalApp.service.QuoteService;
import com.edigest.journalApp.service.UserService;
import com.edigest.journalApp.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private QuoteService quoteService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDB = userService.findUserByUsername(username);
        if(userInDB!=null){
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
            userService.saveNewUser(userInDB);
            return new ResponseEntity<>(userInDB,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepository.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> weather() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("Fetching weather for New York for user: {}", username);
        WeatherResponse newYork = weatherService.getWeather("New York");
        String greeting="";

        if(newYork!=null){
            greeting="Hi "+username+", Weather: "+newYork.getCurrent().getFeelslike();
            return new ResponseEntity<>(greeting,HttpStatus.OK);
        }
        return new ResponseEntity<>("Hi "+username,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/quotes")
    public ResponseEntity<?> quotes(){
        SecurityContextHolder.getContext().getAuthentication();
        QuoteResponse quoteResponse = quoteService.getQuote("success");

        if(quoteResponse!=null){
           return new ResponseEntity<>(quoteResponse.getQuote(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
