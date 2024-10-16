package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    @Transactional
    public void  saveJournalEntry(JournalEntry journalEntry,String username) {
        User user = userService.findUserByUsername(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry save = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(save);

        userService.saveUser(user);
    }
    public void  saveJournalEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
   public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
   }
   public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
   }
  /* public JournalEntry getJournalEntryByUsername(String username) {
        String user = String.valueOf(userService.findUserByUsername(username));
       JournalEntry journalByUsername = journalEntryRepository.findJournalByUsername(user);
       return  journalByUsername;
   }*/
   public void deleteJournalEntryById(ObjectId id,String username){
       User user = userService.findUserByUsername(username);
       user.getJournalEntries().removeIf(x->x.getId().equals(id));
       userService.saveUser(user);
       journalEntryRepository.deleteById(id);
   }
}
