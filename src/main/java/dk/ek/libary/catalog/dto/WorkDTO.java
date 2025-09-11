package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.Author;
import dk.ek.libary.catalog.model.Subject;

import java.util.List;

public class WorkDTO {
    public record WorkDto(Long id, String title, String workType, String details, Author author, List<EditionDTO.EditionDto> editions, Subject subjects) {}
}
