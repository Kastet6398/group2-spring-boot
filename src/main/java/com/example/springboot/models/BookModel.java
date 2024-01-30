package com.example.springboot.models;

import java.util.ArrayList;

public record BookModel(String name, String urlOfContent, ArrayList<Integer> categoryId, int releaseYear, int pagesAmount, int id) implements SingleObjectModel {
    @SuppressWarnings("unused")
    public BookModel(String name, String urlOfContent, ArrayList<Integer> categoryId, int pagesAmount, int releaseYear) {
        this(name, urlOfContent, categoryId, pagesAmount, releaseYear, -1);
    }
    @Override
    public int id() {
        return id;
    }
}
