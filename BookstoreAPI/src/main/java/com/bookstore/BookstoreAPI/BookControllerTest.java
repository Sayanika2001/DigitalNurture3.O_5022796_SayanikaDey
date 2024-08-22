package com.bookstore.BookstoreAPI;


import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;

public class BookControllerTest {
    private BookService bookService;

    public void testGetAllBooks() throws Exception {
        // Mock book service
        List<Book> books = Arrays.asList(new Book(), new Book());


        // Perform GET request
        MvcResult result = perform(get("/api/books"));


        // Verify response
        String responseBody = result.getModelAndView().getModel().toString();
        assertEquals(books, responseBody);
    }



    private MvcResult perform(Path path) {
        return null;
    }


    class SpringRunner {
    }

    private class BookService {
        public Object getAllBooks() {
            return null;
        }
    }

    private class MvcResult {
        private ModelAndView modelAndView;

        public ModelAndView getModelAndView() {
            return modelAndView;
        }
    }

    // Other test methods...
}