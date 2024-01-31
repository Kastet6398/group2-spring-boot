package com.example.springboot.models.books;

import com.example.springboot.models.BaseModel;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class BookCategoryTableModel implements BaseModel {
    public ArrayList<BookCategoryModel> getCategories() {
        return categories;
    }
    public BookCategoryTableModel() {

    }

    public void setCategories(ArrayList<BookCategoryModel> categories) {
        this.categories = categories;
    }

    private ArrayList<BookCategoryModel> categories;

        public BookCategoryTableModel(ArrayList<BookCategoryModel> categories) {
            this.categories = categories;
        }

        public static void createFile() throws IOException {
            Utils.writeJson(Constants.BOOK_CATEGORY_TABLE_FILE, new BookCategoryTableModel(new ArrayList<>()));
        }


    }
