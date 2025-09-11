package dk.ek.libary.catalog.dto;


import java.util.List;

public class WorkDTO {
    public record WorkDto(Long id,
                          String title,
                          String workType,
                          String details,
                          List<AuthorDTO.AuthorDto> authors,
                          List<EditionDTO.EditionDto> editions,
                          List<SubjectDTO.SubjectDto> subjects
    ) {}
}
