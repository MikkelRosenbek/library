package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
    List<Author> searchAuthor(String name);
}
