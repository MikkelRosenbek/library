package dk.ek.libary.catalog.controller;

import dk.ek.libary.catalog.dto.AuthorDto;
import dk.ek.libary.catalog.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        try {
            return ResponseEntity.ok(authorService.getAllAuthors());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto.AuthorDto> getAuthorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(authorService.getAuthorById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<AuthorDto.AuthorDto> createAuthor(@RequestBody AuthorDto.AuthorDto authorDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(authorDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto.AuthorDto> updateAuthor(@PathVariable Long id,
                                                            @RequestBody AuthorDto.AuthorDto authorDto) {
        try {
            return ResponseEntity.ok(authorService.updateAuthor(id, authorDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
