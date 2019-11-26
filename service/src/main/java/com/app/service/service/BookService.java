package com.app.service.service;


import com.app.service.dto.BookDto;
import com.app.audit.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    BookDto updateBook(BookDto bookDto, int id);
    void deleteBook(int id);
    BookDto getBook(int id);
    Page<Book> findPaginated(Pageable pageable);
}
