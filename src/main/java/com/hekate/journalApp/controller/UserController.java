package com.hekate.journalApp.controller;

import com.hekate.journalApp.entity.UserEntry;
import com.hekate.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntry> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserEntry> getById(@PathVariable String userName){
        UserEntry userEntry = userService.findByUserName(userName);
        if(userEntry != null){
            return new ResponseEntity<UserEntry>(userEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public String createUser(@RequestBody UserEntry userEntry){
        try {
            userService.saveEntry(userEntry);
            return ("true");
        }
        catch (Exception e){
            e.printStackTrace();
            return ("false");
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<UserEntry> updateById(@PathVariable String userName, @RequestBody UserEntry userEntry){
        return userService.updateById(userName, userEntry);
    }

//    @DeleteMapping
//    public void deleteById(@RequestBody ObjectId myid){
//        userService.deleteById(myid);
//    }
}
