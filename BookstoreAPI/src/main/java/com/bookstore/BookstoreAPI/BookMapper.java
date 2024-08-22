package com.bookstore.BookstoreAPI;

@Mapper
public interface BookMapper {
    BookDTO bookToBookDTO(Book book);

    default BookDTO mapBookToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setPrice(book.getPrice());
        return dto;
    }

    default Book mapDTOToBook(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setPublicationYear(dto.getPublicationYear());
        book.setPrice(dto.getPrice());
        return book;
    }
}
