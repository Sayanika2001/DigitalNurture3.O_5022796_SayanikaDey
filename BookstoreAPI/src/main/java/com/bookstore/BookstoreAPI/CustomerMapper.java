package com.bookstore.BookstoreAPI;


@Mapper
public interface CustomerMapper {
    default Book mapDTOToBook(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setPublicationYear(dto.getPublicationYear());
        book.setPrice(dto.getPrice());
        return book;

}}