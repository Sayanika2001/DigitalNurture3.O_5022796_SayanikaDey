package com.bookstore.BookstoreAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookControllerIntegrationTest {




    public void testGetAllBooks() {
        // Perform GET request

        // Verify response
        ErrorResponse response = null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Book> books = (List<Book>) response.getBody();
        assertNotNull(books);
       }}