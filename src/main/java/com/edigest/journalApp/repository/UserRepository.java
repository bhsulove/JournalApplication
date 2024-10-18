package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//controller ---> service ---> repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    // Additional custom queries can be added here
    // List<User> findByRole(String role);
}
