package dk.ek.libary.catalog.dto;

public class PublisherDTO {
    public record PublisherDto(Long id, String name, String address, String contactInfo) {}
}
