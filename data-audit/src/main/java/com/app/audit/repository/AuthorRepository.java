package com.app.audit.repository;
import com.app.audit.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    void deleteAuthorById(int id);
}
