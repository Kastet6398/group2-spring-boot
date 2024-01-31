package com.example.springboot.controllers;

import com.example.springboot.models.*;
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
            int res = Utils.createBook(book);
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
    public BookTableModel listBooks(@RequestParam(name = "genres", required = false) ArrayList<Integer> genres, @RequestParam(name = "categories", required = false) ArrayList<Integer> categories, @RequestParam(name = "search", required = false) String search, @RequestParam(name = "author", required = false) String author) throws IOException {
        List<BookModel> filteredBooks = ((BookTableModel) Utils.readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class)).getBooks();
        if (categories != null && !categories.isEmpty()) {
            filteredBooks = filteredBooks.stream()
                    .filter(x -> x.getCategoryId().stream().anyMatch(categories::contains))
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

    @GetMapping("/list-categories")
    public BookCategoryTableModel listCategories() throws IOException {
        return (BookCategoryTableModel) Utils.readJson(Constants.BOOK_CATEGORY_TABLE_FILE, BookCategoryTableModel.class);
    }

    @GetMapping("/list-genres")
    public BookGenreTableModel listGenres() throws IOException {
        return (BookGenreTableModel) Utils.readJson(Constants.BOOK_GENRE_TABLE_FILE, BookGenreTableModel.class);
    }

    @PostMapping("/create-category")
    public BaseModel createBookCategory(@CookieValue(name = "token", defaultValue = "") String token, @RequestBody BookCategoryModel category) {
        UserModel user = Utils.getUser(token);

        if (user == null) {
            return new SuccessModel(false, "please login first");
        }

        try {
            int res = Utils.createBookCategory(category);
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

    @PostMapping("/create-genre")
    public BaseModel createBookGenre(@CookieValue(name = "token", defaultValue = "") String token, @RequestBody BookGenreModel category) {
        UserModel user = Utils.getUser(token);

        if (user == null) {
            return new SuccessModel(false, "please login first");
        }

        try {
            int res = Utils.createBookGenre(category);
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
