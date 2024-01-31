package com.example.springboot.models.books;

import com.example.springboot.models.BaseModel;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class BookTableModel implements BaseModel {
    public void setBooks(ArrayList<BookModel> books) {
        this.books = books;
    }
    public BookTableModel() {

    }

    private ArrayList<BookModel> books;

    public BookTableModel(ArrayList<BookModel> books) {
        this.books = books;
    }

    public static void createFile() throws IOException {
        Utils.writeJson(Constants.BOOK_TABLE_FILE, new BookTableModel(new ArrayList<>()));
    }

    public ArrayList<BookModel> getBooks() {
        return books;
    }
}
