package dk.ek.libary.catalog.controller;

import dk.ek.libary.catalog.dto.AuthorDTO;
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

    @GetMapping
    public ResponseEntity<List<AuthorDTO.AuthorDto>> getAllAuthors() {
            return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO.AuthorDto> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO.AuthorDto> createAuthor(@RequestBody AuthorDTO.AuthorDto authorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(authorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO.AuthorDto> updateAuthor(@PathVariable Long id,
                                                            @RequestBody AuthorDTO.AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
    }


}
