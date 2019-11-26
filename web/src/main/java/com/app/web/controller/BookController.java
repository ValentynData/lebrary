package com.app.web.controller;


import com.app.audit.entities.Book;
import com.app.service.dto.BookDto;
import com.app.service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/library/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteBook(@PathVariable int id) throws Exception {
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable int id) {
        return new ResponseEntity<>(bookService.updateBook(bookDto, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable int id) {
        BookDto bookDto = bookService.getBook(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Book>> getBookList(@NotNull Pageable pageable ){
        return new ResponseEntity<>(bookService.findPaginated(pageable), HttpStatus.OK);
    }

}
