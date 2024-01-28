package com.example.springboot.models;

public record SuccessModel(boolean success, String message) implements BaseModel {
    public SuccessModel(boolean success) {
        this(success, success? "success" : "failure");
    }
}
