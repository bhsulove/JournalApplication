package com.edigest.journalApp.service;


import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void saveUser(User user) {
        //user.setDate(LocalDateTime.now());
        userRepository.save(user);
    }
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(ObjectId myId) {
        return userRepository.findById(myId);
    }
    public void deleteUserById(ObjectId id){
        userRepository.deleteById(id);
    }
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
