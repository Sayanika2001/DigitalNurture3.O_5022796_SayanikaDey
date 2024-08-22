package com.bookstore.BookstoreAPI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



public class createBook {
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        createBook bookService = null;
        ResponseEntity<Book> newBook = bookService.createBook(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Book-Id", newBook.getBody().toString());
        headers.add("X-Book-Title", String.valueOf(newBook.getHeaders()));

        return newBook;
    }
}