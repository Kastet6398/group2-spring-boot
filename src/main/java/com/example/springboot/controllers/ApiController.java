package com.example.springboot.controllers;

import com.example.springboot.models.*;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
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
        configureCookie(response, cookie);

        return new SuccessModel(true, "registered successfully");
    }

    @PostMapping("/create-book")
    public BaseModel createBook(@CookieValue(name = "token", defaultValue = "") String token, @RequestBody BookModel book) {
        UserModel user = Utils.getUser(token);

        if (user == null) {
            return new SuccessModel(false, "please login first");
        }

        try {
            if (Utils.createBook(book)) {
                return new SuccessModel(true, "created successfully");
            } else {
                return new SuccessModel(false, "creation failed");
            }
        } catch (Exception e) {
            return new SuccessModel(false, "creation failed");
        }
    }

    @PostMapping("/list-books")
    public BookTableModel listBooks(@RequestParam(name = "categories", required = false) ArrayList<Integer> categories, @RequestParam(name = "search", required = false) String search) throws IOException {
        List<BookModel> filteredBooks = ((BookTableModel) Utils.readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class)).books();
        if (categories != null && !categories.isEmpty()) {
            filteredBooks = filteredBooks.stream()
                    .filter(x -> x.categoryId().stream().anyMatch(categories::contains))
                    .toList();
        }

        if (search != null && !search.isEmpty()) {
            filteredBooks = filteredBooks.stream().filter(x -> x.name().contains(search)).toList();
        }

        return new BookTableModel(new ArrayList<>(filteredBooks));
    }

    @PostMapping("/create-category")
    public BaseModel createBookCategory(@CookieValue(name = "token", defaultValue = "") String token, @RequestBody BookCategoryModel category) {
        UserModel user = Utils.getUser(token);

        if (user == null) {
            return new SuccessModel(false, "please login first");
        }

        try {
            if (Utils.createBookCategory(category)) {
                return new SuccessModel(true, "created successfully");
            } else {
                return new SuccessModel(false, "creation failed");
            }
        } catch (Exception e) {
            return new SuccessModel(false, "creation failed");
        }
    }

    @PostMapping("/login")
    public BaseModel login(HttpServletResponse response, @RequestBody LoginUserModel user) throws IOException {
        System.out.println(user);
        String token = Utils.login(user);

        if (token == null) {
            return new SuccessModel(false, "logging in failed");
        }

        Cookie cookie = new Cookie("token", token);
        configureCookie(response, cookie);

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

    private void configureCookie(HttpServletResponse response, Cookie cookie) {
        cookie.setPath("/");
        cookie.setMaxAge(100000);
        response.addCookie(cookie);
    }
}
