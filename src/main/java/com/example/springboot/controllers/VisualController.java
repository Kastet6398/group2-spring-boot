package com.example.springboot.controllers;

import com.example.springboot.models.UserModel;
import com.example.springboot.utils.Utils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("unused")
public class VisualController {

    @GetMapping("/")
    public String index(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/sign-up")
    public String signUp(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        return "SignUp";
    }
    @GetMapping("/login")
    public String login(@CookieValue(name = "token", defaultValue = "") String token, Model model){
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/logout")
    public String login(HttpServletResponse response){
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
        return "redirect:/";
    }
    @GetMapping("/home")
    public String homePage(@CookieValue(name = "token", defaultValue = "") String token, Model model){
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        return "HomePage";
    }
}
