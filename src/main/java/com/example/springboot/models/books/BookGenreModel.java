package com.example.springboot.models.books;

import com.example.springboot.models.BaseModel;

public class BookGenreModel implements BaseModel {
    public String getName() {
        return name;
    }
    public BookGenreModel() {

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

    private String name;
        private int id;

        public BookGenreModel(String name) {
            this(name, -1);
        }

        public BookGenreModel(String name, int id) {
            this.name = name;
            this.id = id;
        }

    }
