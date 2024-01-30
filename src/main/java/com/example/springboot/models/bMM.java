package com.example.springboot.models;

import com.example.springboot.utils.Constants;
import com.example.springboot.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class bMM {

    public static class bM implements SingleObjectModel {
        private final String name;
        private final String urlOfContent;
        private final String author;
        private final String coverSheet;
        private final ArrayList<Integer> categoryId;
        private final int releaseYear;
        private final int pagesAmount;
        private final int id;

        public bM(String name, String urlOfContent, String author,
                  String coverSheet, ArrayList<Integer> categoryId,
                  int pagesAmount, int releaseYear) {
            this(name, urlOfContent, author, coverSheet, categoryId, pagesAmount, releaseYear, -1);
        }

        public bM(String name, String urlOfContent, String author,
                  String coverSheet, ArrayList<Integer> categoryId,
                  int pagesAmount, int releaseYear, int id) {
            this.name = name;
            this.urlOfContent = urlOfContent;
            this.author = author;
            this.coverSheet = coverSheet;
            this.categoryId = categoryId;
            this.pagesAmount = pagesAmount;
            this.releaseYear = releaseYear;
            this.id = id;
        }

        public int id() {
            return id;
        }

        public String name() {
            return name;
        }

        public String urlOfContent() {
            return urlOfContent;
        }

        public String author() {
            return author;
        }

        public String coverSheet() {
            return coverSheet;
        }

        public ArrayList<Integer> categoryId() {
            return categoryId;
        }

        public int releaseYear() {
            return releaseYear;
        }

        public int pagesAmount() {
            return pagesAmount;
        }
    }

    public static class BookGenreModel implements BaseModel {
        private String name;
        private int id;

        public BookGenreModel(String name) {
            this(name, -1);
        }

        public BookGenreModel(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int id() {
            return id;
        }

        public String name() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class BookGenreTableModel implements BaseModel {
        private ArrayList<BookGenreModel> genres;

        public BookGenreTableModel(ArrayList<BookGenreModel> genres) {
            this.genres = genres;
        }

        public static void createFile() throws IOException {
            Utils.writeJson(Constants.BOOK_GENRE_TABLE_FILE, new BookGenreTableModel(new ArrayList<>()));
        }

        public ArrayList<BookGenreModel> genres() {
            return genres;
        }

        public void setGenres(ArrayList<BookGenreModel> genres) {
            this.genres = genres;
        }
    }

    public static class BookCategoryTableModel implements BaseModel {
        private ArrayList<BookCategoryModel> categories;

        public BookCategoryTableModel(ArrayList<BookCategoryModel> categories) {
            this.categories = categories;
        }

        public static void createFile() throws IOException {
            Utils.writeJson(Constants.BOOK_CATEGORY_TABLE_FILE, new BookCategoryTableModel(new ArrayList<>()));
        }

        public ArrayList<BookCategoryModel> categories() {
            return categories;
        }

        public void setCategories(ArrayList<BookCategoryModel> categories) {
            this.categories = categories;
        }
    }

    public static class BookCategoryModel implements BaseModel {
        private String name;
        private int id;

        public BookCategoryModel(String name) {
            this(name, -1);
        }

        public BookCategoryModel(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int id() {
            return id;
        }

        public String name() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
