package com.hekate.journalApp.repository;

import com.hekate.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String userName);
}
