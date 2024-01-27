package com.example.springboot.controllers;

import com.example.springboot.models.BaseModel;
import com.example.springboot.models.MessageModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/")
    public BaseModel index() {
        return new MessageModel("hello");
    }
}
