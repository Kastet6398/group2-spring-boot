package com.example.springboot.models;

public record BookGenreModel(String name, int id) implements BaseModel {
    @SuppressWarnings("unused")
    public BookGenreModel(String name) {
        this(name, -1);
    }
    @Override
    public int id() {
        return id;
    }
}
