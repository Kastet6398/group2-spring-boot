package com.example.springboot.models;

@SuppressWarnings("unused")
public record LoginUserModel(String username, String password) implements BaseModel {
}
