package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.JournalEntryService;
import com.edigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
    @RequestMapping("/journal")
    public class  JournalEntryController {
        @Autowired
        private JournalEntryService journalEntryService;
        @Autowired
        private UserService userService;

        @GetMapping
        public ResponseEntity<?> getAll() {
            List<JournalEntry> allJournalEntries = journalEntryService.getAllJournalEntries();
            if(allJournalEntries != null && !allJournalEntries.isEmpty()) {
                return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        /*@PostMapping
        public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
            entry.setDate(LocalDateTime.now());
            try {
                journalEntryService.saveJournalEntry(entry);
                return new ResponseEntity<>(entry, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }*/
        @PostMapping("{username}")
        public ResponseEntity<JournalEntry> createEntryOfUser(@PathVariable String username, @RequestBody JournalEntry entry) {

            try {
                //User user = userService.findUserByUsername(username);
                journalEntryService.saveJournalEntry(entry,username);
                return new ResponseEntity<>(entry, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


        }
        @GetMapping("id/{myId}")
        public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId) {
            Optional<JournalEntry> journalEntryById = journalEntryService.getJournalEntryById(myId);
            if(journalEntryById.isPresent()){
                return new ResponseEntity<>(journalEntryById.get(), HttpStatus.OK);
                }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping({"/{username}"})
        public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
            User userByUsername = userService.findUserByUsername(username);
            List<JournalEntry> journalEntries = userByUsername.getJournalEntries();
            if(journalEntries != null && !journalEntries.isEmpty()){
                return new ResponseEntity<>(journalEntries,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @DeleteMapping("id/{username}/{myId}")
        public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId,@PathVariable String username ) {
            journalEntryService.deleteJournalEntryById(myId,username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       /* @PutMapping("id/{myId}")
        public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry updatedEntry) {
            JournalEntry old_journalEntry = journalEntryService.getJournalEntryById(myId).orElse(null);

            if (old_journalEntry != null) {
                //old_journalEntry.setDate(updatedEntry.getDate());
                old_journalEntry.setTitle(updatedEntry.getTitle()!=null && !updatedEntry.getTitle().equals("")?updatedEntry.getTitle():old_journalEntry.getTitle());
                old_journalEntry.setContent(updatedEntry.getContent()!=null && !updatedEntry.getContent().equals("")?updatedEntry.getContent():old_journalEntry.getContent());
                journalEntryService.saveJournalEntry(old_journalEntry);
                return new ResponseEntity<>(old_journalEntry,HttpStatus.OK);
            }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        @PutMapping("/id/{username}/{myId}")
        public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,
                                                         @PathVariable String username,
                                                         @RequestBody JournalEntry updatedEntry) {
            User user = userService.findUserByUsername(username);
            JournalEntry old_journalEntry = journalEntryService.getJournalEntryById(myId).orElse(null);

            if (old_journalEntry != null) {
                //old_journalEntry.setDate(updatedEntry.getDate());
                old_journalEntry.setTitle(updatedEntry.getTitle()!=null && !updatedEntry.getTitle().equals("")?updatedEntry.getTitle():old_journalEntry.getTitle());
                old_journalEntry.setContent(updatedEntry.getContent()!=null && !updatedEntry.getContent().equals("")?updatedEntry.getContent():old_journalEntry.getContent());
                journalEntryService.saveJournalEntry(old_journalEntry);
                return new ResponseEntity<>(old_journalEntry,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }



