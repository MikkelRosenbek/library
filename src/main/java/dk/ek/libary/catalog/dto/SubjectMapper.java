package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectMapper {
    public SubjectDTO.SubjectDto toDto(Subject subject) {
        if (subject == null) return null;
        return new SubjectDTO.SubjectDto(subject.getId(), subject.getName());
    }

    public Subject toEntity(SubjectDTO.SubjectDto dto) {
        if (dto == null) return null;
        Subject s = new Subject();
        s.setId(dto.id());
        s.setName(dto.name());
        return s;
    }


}
