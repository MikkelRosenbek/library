package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.AuthorDTO;
import dk.ek.libary.catalog.dto.AuthorMapper;
import dk.ek.libary.catalog.model.Author;
import dk.ek.libary.catalog.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public AuthorDTO.AuthorDto createAuthor(AuthorDTO.AuthorDto authorDto) {
        Author author = authorMapper.toEntity(authorDto);
        author.setId(null);
        Author saved = authorRepository.save(author);
        return authorMapper.toDto(saved);
    }

    @Override
    public List<AuthorDTO.AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO.AuthorDto> out = new ArrayList<>();
        for (Author a : authors) {
            out.add(authorMapper.toDto(a));
            }
        return out;
    }

    @Override
    public AuthorDTO.AuthorDto getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return authorMapper.toDto(author.get());
        }
        throw new RuntimeException("Author not found with id: " + id);
    }

    @Override
    public AuthorDTO.AuthorDto updateAuthor(Long id, AuthorDTO.AuthorDto authorDto) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        existing.setName(authorDto.name());
        Author saved = authorRepository.save(existing);
        return authorMapper.toDto(saved);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Author not found with id: " + id);
        }
    }


}
