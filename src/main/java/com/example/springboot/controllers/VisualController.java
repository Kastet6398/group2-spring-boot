package com.example.springboot.controllers;

import com.example.springboot.models.auth.UserModel;
import com.example.springboot.models.books.BookModel;
import com.example.springboot.models.books.BookTableModel;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@SuppressWarnings("unused")
public class VisualController {

    @GetMapping("/")
    public String index(@RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "author", required = false) String author,
                        @RequestParam(name = "genres", required = false) String genre,
                        @CookieValue(name = "token", defaultValue = "") String token, Model model) throws IOException {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        List<BookModel> books = ((BookTableModel)Utils.readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class)).getBooks();

        Set<String> authors = new HashSet<>();

        for (BookModel book : books) {
            authors.add(book.getAuthor());
        }
        Set<String> genres = new HashSet<>();

        for (BookModel book : books) {
            genres.add(book.getGenre());
        }

        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("selectedAuthor", author);
        model.addAttribute("selectedGenre", genre);
        model.addAttribute("search", search);
        return "HomePage";
    }
    @GetMapping("/sign-up")
    public String signUp(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        return "SignUp";
    }
    @GetMapping("/create-book")
    public String createBook(@CookieValue(name = "token", defaultValue = "") String token, Model model) {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        if (user == null) {
            return "redirect:/";
        }
        return "createBook";
    }
    @GetMapping("/change-book/{id}")
    public String changeBook(@PathVariable int id, @CookieValue(name = "token", defaultValue = "") String token, Model model) {
        UserModel user = Utils.getUser(token);
        BookModel book = Utils.getBook(id);
        if (user == null || book == null || book.getPublisher() != Objects.requireNonNull(user).getId()) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("book", book);
        return "editBook";
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
}
