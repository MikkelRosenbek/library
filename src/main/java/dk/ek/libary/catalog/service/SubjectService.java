package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.SubjectDTO;
import dk.ek.libary.catalog.model.Subject;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO.SubjectDto> getAllSubjects();
    SubjectDTO.SubjectDto getSubjectById(Long id);
    SubjectDTO.SubjectDto createSubject(SubjectDTO.SubjectDto subjectDto);
    SubjectDTO.SubjectDto updateSubject(Long id, SubjectDTO.SubjectDto subjectDto);
    void deleteSubject(Long id);
}
