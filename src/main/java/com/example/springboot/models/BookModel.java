package com.example.springboot.models;

import java.util.ArrayList;

public record BookModel(String name, ArrayList<String> urls) implements BaseModel {
}
