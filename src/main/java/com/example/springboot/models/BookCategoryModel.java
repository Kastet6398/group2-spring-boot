package com.example.springboot.models;

public record BookCategoryModel(String name, int id) implements BaseModel {
    public BookCategoryModel(String name) {
        this(name, -1);
    }
    @Override
    public int id() {
        return id;
    }
}
