package com.example.springboot.models.books;

import com.example.springboot.models.BaseModel;

public class BookCategoryModel implements BaseModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookCategoryModel() {

    }
    private String name;
        private int id;

        public BookCategoryModel(String name) {
            this(name, -1);
        }

        public BookCategoryModel(String name, int id) {
            this.name = name;
            this.id = id;
        }

    }


