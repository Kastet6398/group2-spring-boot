package com.example.springboot.models.books;

import com.example.springboot.models.SingleObjectModel;

import java.util.ArrayList;


public class BookModel implements SingleObjectModel {
    public String getName() {
        return name;
    }
    public BookModel() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlOfContent() {
        return urlOfContent;
    }

    public void setUrlOfContent(String urlOfContent) {
        this.urlOfContent = urlOfContent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverSheet() {
        return coverSheet;
    }

    public void setCoverSheet(String coverSheet) {
        this.coverSheet = coverSheet;
    }

    public ArrayList<Integer> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ArrayList<Integer> categoryId) {
        this.categoryId = categoryId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getPagesAmount() {
        return pagesAmount;
    }

    public void setPagesAmount(int pagesAmount) {
        this.pagesAmount = pagesAmount;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
        private String urlOfContent;
        private String author;
        private String coverSheet;
        private ArrayList<Integer> categoryId;
        private int releaseYear;
        private int pagesAmount;
        private int genre;
        private int id;
        private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookModel(String name, String urlOfContent, String author,
                     String coverSheet, ArrayList<Integer> categoryId,
                     int pagesAmount, int releaseYear, int genre, String description) {
            this(name, urlOfContent, author, coverSheet, categoryId, pagesAmount, releaseYear, genre, description, -1);
        }

        public BookModel(String name, String urlOfContent, String author,
                         String coverSheet, ArrayList<Integer> categoryId,
                         int pagesAmount, int releaseYear, int genre, String description, int id) {
            this.name = name;
            this.urlOfContent = urlOfContent;
            this.author = author;
            this.coverSheet = coverSheet;
            this.categoryId = categoryId;
            this.pagesAmount = pagesAmount;
            this.releaseYear = releaseYear;
            this.genre = genre;
            this.id = id;
            this.description = description;
        }

    @Override
    public int getId() {
        return id;
    }
}
