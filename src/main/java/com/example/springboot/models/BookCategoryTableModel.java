package com.example.springboot.models;

import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public record BookCategoryTableModel(ArrayList<BookCategoryModel> categories) implements BaseModel {
    public static void createFile() throws IOException {
        Utils.writeJson(Constants.BOOK_CATEGORY_TABLE_FILE, new BookCategoryTableModel(new ArrayList<>()));
    }
}
