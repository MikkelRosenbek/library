package dk.ek.libary.catalog.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectMapper {
    public SubjectDTO toDto(SubjectDTO subject) {
        List<AuthorDto> authors = new ArrayList<>();

        for (var author : subject.getAuthors()) {
            authors.add(toDto(author));
        }
        return new SubjectDTO(subject.getId(), subject.getName(), subject.getAuthors(), authors);
    }
}
