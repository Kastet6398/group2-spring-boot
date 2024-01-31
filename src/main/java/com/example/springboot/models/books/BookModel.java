package com.example.springboot.models.books;

import com.example.springboot.models.SingleObjectModel;


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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String urlOfContent;
    private String author;
    private String coverSheet;
    private String category;
    private int releaseYear;
    private int pagesAmount;
    private String genre;
    private int id;
    private int publisher;
    private String description;

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookModel(String name, String urlOfContent, String author,
                     String coverSheet, String category,
                     int pagesAmount, int releaseYear, String genre, int publisher,String description) {
            this(name, urlOfContent, author, coverSheet, category, pagesAmount, releaseYear, genre, publisher,description, -1);
        }

        public BookModel(String name, String urlOfContent, String author,
                         String coverSheet, String category,
                         int pagesAmount, int releaseYear, String genre, int publisher, String description, int id) {
            this.name = name;
            this.urlOfContent = urlOfContent;
            this.author = author;
            this.coverSheet = coverSheet;
            this.category = category;
            this.pagesAmount = pagesAmount;
            this.releaseYear = releaseYear;
            this.genre = genre;
            this.id = id;
            this.publisher = publisher;
            this.description = description;
        }
    public BookModel(String name, String urlOfContent, String author,
                     String coverSheet, String category,
                     int pagesAmount, int releaseYear, String genre,String description) {
        this(name, urlOfContent, author, coverSheet, category, pagesAmount, releaseYear, genre, -1,description);
    }
    @Override
    public int getId() {
        return id;
    }
}
