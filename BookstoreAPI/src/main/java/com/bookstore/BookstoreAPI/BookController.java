package com.bookstore.BookstoreAPI;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    // ... (previous code remains the same)

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Book getBook(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Book> getBooksByFilter(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        List<Book> filteredBooks = books;
        if (title != null && !title.isEmpty()) {
            filteredBooks = filteredBooks.stream()
                    .filter(book -> book.getTitle().contains(title))
                    .collect(Collectors.toList());
        }
        if (author != null && !author.isEmpty()) {
            filteredBooks = filteredBooks.stream()
                    .filter(book -> book.getAuthor().contains(author))
                    .collect(Collectors.toList());
        }
        return filteredBooks;
    }
}