package com.example.springboot.models;

public record UserModel(String name, String email, String password) implements BaseModel {
}
