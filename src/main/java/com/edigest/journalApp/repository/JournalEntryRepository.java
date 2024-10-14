package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//controller ---> service ---> repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    //JournalEntry findJournalByUsername(String username);

}
