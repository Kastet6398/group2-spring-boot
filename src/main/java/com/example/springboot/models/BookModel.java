package com.example.springboot.models;

import java.util.ArrayList;

public record BookModel(String name, String urlOfContent, String author, String coverSheet, ArrayList<Integer> categoryId, int releaseYear, int pagesAmount, int id, int genre) implements SingleObjectModel {
    @SuppressWarnings("unused")
    public BookModel(String name, String urlOfContent, String author, String coverSheet, ArrayList<Integer> categoryId, int pagesAmount, int releaseYear, int genre) {
        this(name, urlOfContent, author, coverSheet, categoryId, pagesAmount, releaseYear, genre, -1);
    }
    @Override
    public int id() {
        return id;
    }
}
