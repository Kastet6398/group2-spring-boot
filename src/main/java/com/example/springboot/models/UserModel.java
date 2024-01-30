package com.example.springboot.models;

public record UserModel(String firstName, String lastName, String username, String email, String password, int id) implements SingleObjectModel {
    @SuppressWarnings("unused")
    public UserModel(String firstName, String lastName, String username, String email, String password) {
        this(firstName, lastName, username, email, password, -1);
    }
    @Override
    public int id() {
        return id;
    }
}
