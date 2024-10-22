package com.edigest.journalApp.service;


import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveUser(User user) {
        userRepository.save(user);
    }

    //@Transactional
    public boolean saveNewUser(User user) {
        try {
            log.info("log check");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            user.setDate(LocalDateTime.now());
            log.info("save user: {}", user);
            userRepository.save(user);
            log.info("save user success");
            return true;
        }catch (Exception e){
            log.info("Some Message");
            log.warn("Warning message");
            log.error("Error Creating User : {}" ,user.getUsername());
            log.debug("Debug");
            return false;
        }

    }
    public void saveNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN","USER"));
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
