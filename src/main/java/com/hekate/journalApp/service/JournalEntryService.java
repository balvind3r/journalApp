package com.hekate.journalApp.service;

import com.hekate.journalApp.entity.JournalEntry;
import com.hekate.journalApp.entity.UserEntry;
import com.hekate.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public ResponseEntity<JournalEntry> saveEntry(JournalEntry journalEntry, String userName){
        try{
            ZonedDateTime CurrentTime = ZonedDateTime.now();
            journalEntry.setCreatedDate(CurrentTime);
            journalEntry.setUpdatedDate(CurrentTime);
            UserEntry userEntry = userService.findByUserName(userName);
    //        System.out.println(userEntry);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            userEntry.getJournalEntries().add(saved);
//            userEntry.setUserName(null);
            userService.saveEntry(userEntry);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Error e){
//            throw new RuntimeException("A runtime error has occurred", e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public ResponseEntity<JournalEntry> updateById(JournalEntry newEntry, ObjectId id, String userName){
        JournalEntry oldEntry = findById(id).orElse(null);

        if(oldEntry == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oldEntry.setContent(newEntry.getContent() == null || newEntry.getContent().isEmpty() ? oldEntry.getContent() : newEntry.getContent());
        oldEntry.setTitle(newEntry.getTitle() == null || newEntry.getTitle().isEmpty() ? oldEntry.getTitle() : newEntry.getTitle());
        oldEntry.setUpdatedDate(ZonedDateTime.now());
        journalEntryRepository.save(oldEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void deleteById(ObjectId id, String userName){
        UserEntry userEntry = userService.findByUserName(userName);
        userEntry.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(userEntry);
        journalEntryRepository.deleteById(id);
    }

    public static class ZonedDateTimeReadConverter {

    }
}
