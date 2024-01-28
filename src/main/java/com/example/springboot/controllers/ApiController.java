package com.example.springboot.controllers;

import com.example.springboot.models.BaseModel;
import com.example.springboot.models.MessageModel;
import com.example.springboot.models.SuccessModel;
import com.example.springboot.models.UserModel;
import com.example.springboot.utils.Utils;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/")
    public BaseModel index(@CookieValue(name = "token", defaultValue = "") String token) {
        UserModel user = Utils.getUser(token);
        System.out.println(user);
        return new MessageModel("hello");
    }

    @PostMapping("/register")
    public BaseModel register(HttpServletResponse response, @RequestBody UserModel user) throws IOException {
        System.out.println(user);
        Cookie cookie = new Cookie("token", Utils.register(user));
        cookie.setPath("/");
        cookie.setMaxAge(100000);
        response.addCookie(cookie);

        return new SuccessModel(true, "registered successfully");
    }

    @PostMapping("/login")
    public BaseModel login(HttpServletResponse response, @RequestBody UserModel user) throws IOException {
        System.out.println(user);
        Cookie cookie = new Cookie("token", Utils.register(user));
        cookie.setPath("/");
        cookie.setMaxAge(100000);
        response.addCookie(cookie);

        return new SuccessModel(true, "logged in successfully");
    }
}
