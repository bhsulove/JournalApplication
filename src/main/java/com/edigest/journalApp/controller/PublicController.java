package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserDetailsServiceImpl;
import com.edigest.journalApp.service.UserService;
import com.edigest.journalApp.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;;




    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt_token = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt_token,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while generating token: ",e);
            return new ResponseEntity<>("Incorrect username or password ",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

}
