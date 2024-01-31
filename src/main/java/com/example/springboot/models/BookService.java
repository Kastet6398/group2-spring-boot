package com.example.springboot.models;

import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    public List<Book> getAllBooks() {
        return Arrays.asList(new Book(), new Book(), new Book());
    }

    public Book getBookById(String id) {
        // Логика получения информации о книге по ее идентификатору (заглушка)
        return new Book();
    }
}
