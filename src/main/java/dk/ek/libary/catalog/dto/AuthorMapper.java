package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.Author;
import dk.ek.libary.catalog.model.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper {

    public AuthorDto toDto(Author author) {
        if (author == null) return null;
        return new AuthorDto(author.getId(), author.getName());
    }

    public Author toEntity(AuthorDto dto) {
        if (dto == null) return null;
        Author a = new Author();
        a.setId(dto.id());
        a.setName(dto.name());
        return a;
    }



}
