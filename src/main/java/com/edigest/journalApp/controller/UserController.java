package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import com.edigest.journalApp.service.UserService;
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

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

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

    /*@GetMapping("id/{myId}")
    public ResponseEntity<User> getEntryById(@PathVariable ObjectId myId) {
        Optional<User> userEntryById = userService.getUserById(myId);
        if(userEntryById.isPresent()){
            return new ResponseEntity<>(userEntryById.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserById(@PathVariable String username) {
        User userByUsername = userService.findUserByUsername(username);
        if (userByUsername!=null) {
            return new ResponseEntity<>(userByUsername, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }*/
}
