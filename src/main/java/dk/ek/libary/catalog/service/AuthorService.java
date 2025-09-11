package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.AuthorDTO;
import java.util.List;

public interface AuthorService {
    List<AuthorDTO.AuthorDto> getAllAuthors();
    AuthorDTO.AuthorDto getAuthorById(Long id);
    AuthorDTO.AuthorDto createAuthor(AuthorDTO.AuthorDto authorDto);
    AuthorDTO.AuthorDto updateAuthor(Long id, AuthorDTO.AuthorDto authorDto);
    void deleteAuthor(Long id);
}
