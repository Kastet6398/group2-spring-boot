package com.example.springboot.models;

public record UserModel(String firstName, String lastName, String username, String email, String password) implements BaseModel {
}
