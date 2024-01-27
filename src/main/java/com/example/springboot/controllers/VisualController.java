package com.example.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisualController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
