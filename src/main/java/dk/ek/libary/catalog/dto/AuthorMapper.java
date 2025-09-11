package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.Author;
import org.springframework.stereotype.Component;


@Component
public class AuthorMapper {

    public AuthorDTO.AuthorDto toDto(Author author) {
        if (author == null) return null;
        return new AuthorDTO.AuthorDto(author.getId(), author.getName());
    }

    public Author toEntity(AuthorDTO.AuthorDto dto) {
        if (dto == null) return null;
        Author a = new Author();
        a.setId(dto.id());
        a.setName(dto.name());
        return a;
    }



}
