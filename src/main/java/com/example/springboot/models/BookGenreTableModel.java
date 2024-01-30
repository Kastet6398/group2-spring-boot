package com.example.springboot.models;

import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public record BookGenreTableModel(ArrayList<BookGenreModel> genres) implements BaseModel {
    public static void createFile() throws IOException {
        Utils.writeJson(Constants.BOOK_GENRE_TABLE_FILE, new BookGenreTableModel(new ArrayList<>()));
    }
}
