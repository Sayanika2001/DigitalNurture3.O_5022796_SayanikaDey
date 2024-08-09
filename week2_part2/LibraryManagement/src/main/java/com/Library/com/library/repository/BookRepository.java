
package com.library.repository;
import org.springframework.stereotype.Repository;
import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public class BookRepository {
    

    public void save() {
        System.out.println("Saving book to the repository...");
    }
}

    

public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom query methods if needed...
}