package com.app.service.service;

import com.app.audit.entities.Author;
import com.app.audit.repository.AuthorRepository;
import com.app.service.dto.AuthorDto;
import com.app.service.exception.NotFoundEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;
    ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper){
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        return modelMapper.map(authorRepository.save(author), AuthorDto.class);
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto, int id) {
        authorRepository.findById(id).orElseThrow(() ->new NotFoundEntityException("404", "NOT FOUND", HttpStatus.NOT_FOUND));
        authorDto.setId(id);
        Author tmpAuthor = modelMapper.map(authorDto, Author.class);
        return modelMapper.map(authorRepository.save(tmpAuthor), AuthorDto.class);

    }

    @Override
    @Transactional
    public void deleteAuthor(int id) {
        authorRepository.findById(id).orElseThrow(() ->new NotFoundEntityException("404", "NOT FOUND", HttpStatus.NOT_FOUND));
        authorRepository.delete(new Author(id));
//         authorRepository.deleteAuthorById(id);
    }

    @Override
    public AuthorDto getAuthor(int id) {

        return modelMapper.map(authorRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(id)), AuthorDto.class);
    }

    @Override
    public Page<Author> findPaginated(Pageable pageable) {

        return authorRepository.findAll(pageable);
    }
}
