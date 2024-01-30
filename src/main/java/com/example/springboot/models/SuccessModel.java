package com.example.springboot.models;

public record SuccessModel(boolean success, String message) implements BaseModel {
    @SuppressWarnings("unused")
    public SuccessModel(boolean success) {
        this(success, success? "success" : "failure");
    }
}
