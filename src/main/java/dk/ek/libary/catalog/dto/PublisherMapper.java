package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.Publisher;

public class PublisherMapper {

    public static PublisherDTO.PublisherDto toDto(Publisher publisher) {
        return new PublisherDTO.PublisherDto(publisher.getId(), publisher.getName(), publisher.getAddress(), publisher.getContactInfo());
    }

    public static Publisher toEntity (PublisherDTO.PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDto.id());
        publisher.setName(publisherDto.name());
        publisher.setAddress(publisherDto.address());
        publisher.setContactInfo(publisherDto.contactInfo());
        return publisher;
    }

}
