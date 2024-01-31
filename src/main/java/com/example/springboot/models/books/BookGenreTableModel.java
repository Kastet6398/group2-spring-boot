package com.example.springboot.models.books;

import com.example.springboot.models.BaseModel;
import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class BookGenreTableModel implements BaseModel {
    public ArrayList<BookGenreModel> getGenres() {
        return genres;
    }
    public BookGenreTableModel() {

    }

    public void setGenres(ArrayList<BookGenreModel> genres) {
        this.genres = genres;
    }

    private ArrayList<BookGenreModel> genres;

        public BookGenreTableModel(ArrayList<BookGenreModel> genres) {
            this.genres = genres;
        }

        public static void createFile() throws IOException {
            Utils.writeJson(Constants.BOOK_GENRE_TABLE_FILE, new BookGenreTableModel(new ArrayList<>()));
        }

    }
