package dk.ek.libary.catalog.dto;

import java.util.List;

public class WorkDTO {
    public record WorkDto(Long id, String title, String workType, String details, String author, List<EditionDTO.EditionDto> editions, String subjects) {}
}
