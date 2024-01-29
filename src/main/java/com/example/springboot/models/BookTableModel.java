package com.example.springboot.models;

import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public record BookTableModel(ArrayList<BookModel> books) implements BaseModel {
    public static void createFile() throws IOException {
        Utils.writeJson(Constants.BOOK_TABLE_FILE, new BookTableModel(new ArrayList<>()));
    }
}
