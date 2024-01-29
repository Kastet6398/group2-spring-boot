package com.example.springboot.models;

import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public record UserModel(String firstName, String lastName, String username, String email, String password, int id) implements SingleObjectModel {
    public UserModel(String firstName, String lastName, String username, String email, String password) {
        this(firstName, lastName, username, email, password, -1);
    }
    @Override
    public int id() {
        return id;
    }
}
