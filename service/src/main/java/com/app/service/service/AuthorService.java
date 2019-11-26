package com.app.service.service;

import com.app.service.dto.AuthorDto;
import com.app.audit.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto updateAuthor(AuthorDto authorDto, int id);
    void deleteAuthor(int id);
    AuthorDto getAuthor(int id);
    Page<Author> findPaginated(Pageable pageable );


}
