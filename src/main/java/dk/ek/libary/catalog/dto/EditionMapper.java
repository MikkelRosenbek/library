package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.Edition;

public class EditionMapper {

    public static EditionDTO.EditionDto toDto(Edition edition) {
        return new EditionDTO.EditionDto(edition.getId(), edition.getEditionNumber(), edition.getYear(), edition.getFormat(), PublisherMapper.toDto(edition.getPublisher()));
    }

    public static Edition toEntity(EditionDTO.EditionDto editionDto) {
        Edition edition = new Edition();
        edition.setId(editionDto.id());
        edition.setEditionNumber(editionDto.editionNumber());
        edition.setYear(editionDto.year());
        edition.setFormat(editionDto.format());
        edition.setPublisher(PublisherMapper.toEntity(editionDto.publisher()));
        return edition;
    }
}
