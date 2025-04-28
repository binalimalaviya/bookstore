package com.example.bookstore;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String description;
    private int coverImage;

    public Book(int id, String title, String author, String publisher, String publicationDate, String description, int coverImage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.description = description;
        this.coverImage = coverImage;
    }

    public Book(int id, String title, String author, int img) {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public int getCoverImage() {
        return coverImage;
    }
}
