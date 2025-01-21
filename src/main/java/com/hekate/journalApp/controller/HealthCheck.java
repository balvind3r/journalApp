package com.hekate.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HealthCheck {
    @GetMapping
    public String getHealth(){
        return ("All systems normal");
    }
}
