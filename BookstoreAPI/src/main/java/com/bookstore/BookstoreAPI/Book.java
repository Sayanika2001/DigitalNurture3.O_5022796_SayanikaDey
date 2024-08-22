package com.bookstore.BookstoreAPI;
import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String isbn;

    public Book() {
    }

    public void setPublisher(String publisher) {
    }

    public void setPublicationYear(int publicationYear) {
    }

    public String getPublisher() {
        return null;
    }

    public int getPublicationYear() {
        return 0;
    }
}