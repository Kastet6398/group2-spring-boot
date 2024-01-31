package com.example.springboot.controllers;

import com.example.springboot.models.*;
import com.example.springboot.models.api.IdModel;
import com.example.springboot.models.api.MessageModel;
import com.example.springboot.models.api.SuccessModel;
import com.example.springboot.models.auth.LoginUserModel;
import com.example.springboot.models.auth.UserModel;
import com.example.springboot.models.books.*;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.StringTemplate.STR;

@RestController
@RequestMapping("/api")
@SuppressWarnings("unused")
public class ApiController {

    @GetMapping("/")
    public BaseModel index(@CookieValue(name = "token", defaultValue = "") String token) {
        UserModel user = Utils.getUser(token);
        String greeting = "Hello";

        if (user != null) {
            greeting += STR.", \{user.getFirstName()} \{user.getLastName()}";
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
            int res = Utils.createBook(book, user.getId());
            if (res != -1) {
                return new IdModel(res);
            } else {
                return new SuccessModel(false, "creation failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new SuccessModel(false, "creation failed");
        }
    }

    @GetMapping("/list-books")
    public BookTableModel listBooks(@RequestParam(name = "genres", required = false) ArrayList<String> genres, @RequestParam(name = "categories", required = false) ArrayList<String> categories, @RequestParam(name = "search", required = false) String search, @RequestParam(name = "author", required = false) String author) throws IOException {
        List<BookModel> filteredBooks = ((BookTableModel) Utils.readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class)).getBooks();
        if (categories != null && !categories.isEmpty()) {
            filteredBooks = filteredBooks.stream()
                    .filter(x -> categories.contains(x.getCategory()))
                    .toList();
        }
        if (genres != null && !genres.isEmpty()) {
            filteredBooks = filteredBooks.stream()
                    .filter(x -> genres.contains(x.getGenre()))
                    .toList();
        }
        if (search != null && !search.isEmpty()) {
            filteredBooks = filteredBooks.stream().filter(x -> x.getName().contains(search)).toList();
        }
        if (author != null && !author.isEmpty()) {
            filteredBooks = filteredBooks.stream().filter(x -> Objects.equals(x.getAuthor(), author)).toList();
        }

        return new BookTableModel(new ArrayList<>(filteredBooks));
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

    @GetMapping("/book/{id}")
    public BookModel getBook(@PathVariable int id) {
        return Utils.getBook(id);
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
    @GetMapping("/delete-book/{id}")
    public BaseModel deleteBook(@PathVariable int id ,@CookieValue(name = "token", defaultValue = "") String token) {
        UserModel user = Utils.getUser(token);

        if (user == null) {
            return new SuccessModel(false, "please login first");
        }

        try {
            boolean res = Utils.deleteBook(id, user.getId());
           return new SuccessModel(res, res? "deletion success":"deletion failed");
       }catch (Exception e){
           e.printStackTrace();
           return new SuccessModel(false, "deletion failed");
       }
    }

    @PostMapping("/change-book/{id}")
    public BaseModel changeBook(@PathVariable int id ,@CookieValue(name = "token", defaultValue = "") String token,@RequestBody BookModel book) {
        UserModel user = Utils.getUser(token);

        if (user == null) {
            return new SuccessModel(false, "please login first");
        }

        try {
            boolean res = Utils.updateBook(book, id, user.getId());
            return new SuccessModel(res, res? "changing success":"changing failed");
        }catch (Exception e){
            e.printStackTrace();
            return new SuccessModel(false, "changing failed");
        }
    }
}
