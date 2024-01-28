package com.example.springboot.controllers;

import com.example.springboot.models.*;
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
        String greeting = "Hello";

        if (user != null) {
            greeting += STR.", \{user.firstName()} \{user.lastName()}";
        }

        greeting += "!";

        return new MessageModel(greeting);
    }

    @PostMapping("/sign-up")
    public BaseModel signUp(HttpServletResponse response, @RequestBody UserModel user) throws IOException {
        String token = Utils.register(user);
        if (token == null) {
            return new SuccessModel(false, "registration failed");
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(100000);
        response.addCookie(cookie);

        return new SuccessModel(true, "registered successfully");
    }

    @PostMapping("/login")
    public BaseModel login(HttpServletResponse response, @RequestBody LoginUserModel user) throws IOException {
        System.out.println(user);
        String token = Utils.login(user);
        if (token == null) {
            return new SuccessModel(false, "logging in failed");
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(100000);
        response.addCookie(cookie);

        return new SuccessModel(true, "logged in successfully");
    }

    @GetMapping("/logout")
    public BaseModel logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

        return new SuccessModel(true, "logged out successfully");
    }
}
