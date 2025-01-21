package com.hekate.journalApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.*;

public interface ActionRepository extends MongoRepository<Action, String> {

}