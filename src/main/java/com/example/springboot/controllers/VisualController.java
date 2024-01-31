package com.example.springboot.controllers;

import com.example.springboot.models.auth.UserModel;
import com.example.springboot.models.books.BookGenreTableModel;
import com.example.springboot.models.books.BookModel;
import com.example.springboot.models.books.BookTableModel;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@SuppressWarnings("unused")
public class VisualController {

    @GetMapping("/")
    public String index(@RequestParam(name = "author", required = false) String author,
                        @RequestParam(name = "genre", required = false, defaultValue = "-1") int genreId, @CookieValue(name = "token", defaultValue = "") String token, Model model) throws IOException {
        UserModel user = Utils.getUser(token);
        model.addAttribute("user", user);
        List<BookModel> books = ((BookTableModel)Utils.readJson(Constants.BOOK_TABLE_FILE, BookTableModel.class)).getBooks();

        if (author != null && !author.isEmpty())
            books = books.stream().filter(x -> x.getAuthor().equals(author)).toList();

        if (genreId != -1)
            books = books.stream().filter(x -> x.getGenre() == genreId).toList();

        model.addAttribute("books", books);
        Set<String> authors = new HashSet<>();

        for (BookModel book : books) {
            authors.add(book.getAuthor());
        }

        model.addAttribute("authors", authors);
        model.addAttribute("genres", ((BookGenreTableModel)Utils.readJson(Constants.BOOK_GENRE_TABLE_FILE, BookGenreTableModel.class)).getGenres());
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
}
