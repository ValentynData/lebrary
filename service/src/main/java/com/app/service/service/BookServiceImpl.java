package com.app.service.service;

import com.app.audit.entities.Author;
import com.app.audit.entities.Book;
import com.app.audit.repository.BookRepository;
import com.app.service.dto.AuthorDto;
import com.app.service.dto.BookDto;
import com.app.service.exception.NotFoundEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    ModelMapper modelMapper;
    AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorService authorService){
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {

        AuthorDto author = authorService.getAuthor(bookDto.getAuthorId());

        Book book = modelMapper.map(bookDto, Book.class);
        book.setAuthor(modelMapper.map(author, Author.class));
        return modelMapper.map(bookRepository.save(book), BookDto.class);
        
    }

    @Override
    public BookDto updateBook(BookDto bookDto, int id) {

        Book tmpBook = bookRepository.findById(id).orElseThrow(() ->new NotFoundEntityException(id));
        bookDto.setId(id);
        tmpBook = modelMapper.map(bookDto, Book.class);
        return modelMapper.map(bookRepository.save(tmpBook), BookDto.class);
    }

    @Override
    public void deleteBook(int id) {

        bookRepository.findById(id).orElseThrow(() ->new NotFoundEntityException("404", "NOT FOUND", HttpStatus.NOT_FOUND));
        bookRepository.delete(new Book(id));
    }

    @Override
    public BookDto getBook(int id) {
        return modelMapper.map(bookRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(id)), BookDto.class);
    }

    @Override
    public Page<Book> findPaginated(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }
}
