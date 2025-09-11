package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject getAuthorById(Long id);
    Subject createSubject(Subject subject);
    Subject updateSubject(Long id, Subject subject);
    void deleteSubject(Long id);
    List<Subject> searchSubject(String subject);
}
