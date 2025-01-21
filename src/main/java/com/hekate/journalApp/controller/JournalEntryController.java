package com.hekate.journalApp.controller;

import com.hekate.journalApp.entity.JournalEntry;
import com.hekate.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
        return journalEntryService.getAllEntries();
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myid){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@PathVariable String userName, @RequestBody JournalEntry journalEntry){
        try {
            System.out.println(userName);
            return journalEntryService.saveEntry(journalEntry, userName);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/id/{userName}/{myid}")
    public ResponseEntity<JournalEntry> updateById(@RequestBody JournalEntry newEntry, @PathVariable ObjectId myid, @PathVariable String userName){
        return journalEntryService.updateById(newEntry, myid, userName);
    }

    @DeleteMapping("/id/{userName}/{myid}")
    public void deleteById(@PathVariable ObjectId myid, @PathVariable String userName){
        journalEntryService.deleteById(myid, userName);
    }
}
