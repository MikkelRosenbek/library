package dk.ek.libary.catalog.dto;

public class EditionDTO {
    public record EditionDto(Long id, String editionNumber, int year, String format, PublisherDTO.PublisherDto publisher) {}
}
