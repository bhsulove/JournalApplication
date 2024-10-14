package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//controller ---> service ---> repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}
