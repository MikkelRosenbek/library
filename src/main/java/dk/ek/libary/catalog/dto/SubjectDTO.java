package dk.ek.libary.catalog.dto;

import java.util.List;

public record SubjectDTO(Long id, String name, List<AuthorDto> authors) {
}
