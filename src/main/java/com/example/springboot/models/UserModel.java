package com.example.springboot.models;

import org.apache.catalina.User;

import java.util.HashMap;
import java.util.Map;

public record UserModel(String firstName, String lastName, String username, String email, String password) implements BaseModel {

    private static final Map<String, User> users = new HashMap<>();

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return users.get(username);
    }
    public static boolean userExists(String username) {
        return users.containsKey(username);
    }
}
