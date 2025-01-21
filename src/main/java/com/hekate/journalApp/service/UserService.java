package com.hekate.journalApp.service;

import com.hekate.journalApp.entity.JournalEntry;
import com.hekate.journalApp.entity.UserEntry;
import com.hekate.journalApp.repository.JournalEntryRepository;
import com.hekate.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(UserEntry userEntry){
        userRepository.save(userEntry);
    }

    public List<UserEntry> getAllUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<UserEntry> updateById(String userName, UserEntry userEntry){
        UserEntry oldEntry = findByUserName(userName);
        if(oldEntry == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldEntry.setPassword(userEntry.getPassword());
        oldEntry.setUserName(userEntry.getUserName());
        userRepository.save(oldEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    public void deleteById(ObjectId id){
//        userRepository.deleteById(id);
//    }

    public UserEntry findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
