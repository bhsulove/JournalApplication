package com.edigest.journalApp.service;


import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        user.setDate(LocalDateTime.now());
        userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId myId) {
        return userRepository.findById(myId);
    }


    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
