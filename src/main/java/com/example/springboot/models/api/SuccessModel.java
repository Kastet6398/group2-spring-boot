package com.example.springboot.models.api;

import com.example.springboot.models.BaseModel;

public record SuccessModel(boolean success, String message) implements BaseModel {
    @SuppressWarnings("unused")
    public SuccessModel(boolean success) {
        this(success, success? "success" : "failure");
    }
}
