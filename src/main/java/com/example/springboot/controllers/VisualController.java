package com.example.springboot.controllers;

import com.example.springboot.models.auth.UserModel;
import com.example.springboot.models.books.BookTableModel;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@SuppressWarnings("unused")
public class VisualController {

    @GetMapping("/")
    public String index(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        return "HomePage";
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
    public String homePage(@CookieValue(name = "token", defaultValue = "") String token, Model model) throws IOException {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        model.addAttribute("books", Utils.readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class));
        return "HomePage";
    }
}
