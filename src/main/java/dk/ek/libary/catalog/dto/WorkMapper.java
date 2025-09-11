package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkMapper {

    private final AuthorMapper authorMapper;
    private final SubjectMapper subjectMapper;

    public WorkMapper (AuthorMapper authorMapper, SubjectMapper subjectMapper) {
        this.authorMapper = authorMapper;
        this.subjectMapper = subjectMapper;
    }

    public WorkDTO.WorkDto toDto(Work work) {

        // Editions
        List<EditionDTO.EditionDto> editions = new ArrayList<>();
        for (Edition edition : work.getEditions()) {
            editions.add(EditionMapper.toDto(edition));
        }

        // Authors
        List<AuthorDTO.AuthorDto> authors = new ArrayList<>();
        for (Author a : work.getAuthors()) {
            authors.add(authorMapper.toDto(a));
        }

        // Subjects
        List<SubjectDTO.SubjectDto> subjects = new ArrayList<>();
        for (Subject s : work.getSubjects()) {
            subjects.add(subjectMapper.toDto(s));
        }

        return new WorkDTO.WorkDto(
                work.getId(),
                work.getTitle(),
                work.getWorkType() != null ? work.getWorkType().name() : null,
                work.getDetails(),
                authors,
                editions,
                subjects
                );
    }

    public Work toEntity(WorkDTO.WorkDto dto) {
        Work work = new Work();
        work.setId(dto.id());
        work.setTitle(dto.title());

        if (dto.workType() != null) {
            work.setWorkType(WorkType.valueOf(dto.workType()));
        }
        work.setDetails(dto.details());


        // Editions
        List<Edition> editions = new ArrayList<>();
        if (dto.editions() != null) {
            for (var edDto : dto.editions()) {
                Edition edition = EditionMapper.toEntity(edDto);
                edition.setWork(work);
                editions.add(edition);
            }
        }
        work.setEditions(editions);

        // Authors
        if (dto.authors() != null) {
            for (var aDto : dto.authors()) {
                work.addAuthor(authorMapper.toEntity(aDto));
            }
        }


        //Subjects
        if (dto.subjects() != null) {
            for (var sDto : dto.subjects()) {
                work.addSubject(subjectMapper.toEntity(sDto));
            }
        }

        return work;
    }


}
