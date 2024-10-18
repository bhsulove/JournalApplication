package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.JournalEntryService;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAllUser();
        if (allUsers != null && !allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   /* @GetMapping
    public ResponseEntity<?> getAllJournals() {
        List<JournalEntry> allJournalEntries = journalEntryService.getAllJournalEntries();
        if (allJournalEntries != null && !allJournalEntries.isEmpty()) {
            return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
    /*@GetMapping
    public ResponseEntity<?> getAllJournals() {
        List<JournalEntry> allJournalEntries = journalEntryService.getAllJournalEntries();
        if(allJournalEntries != null && !allJournalEntries.isEmpty()) {
            return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
