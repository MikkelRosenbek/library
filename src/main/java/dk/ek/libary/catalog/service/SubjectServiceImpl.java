package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.AuthorDTO;
import dk.ek.libary.catalog.dto.SubjectDTO;
import dk.ek.libary.catalog.dto.SubjectMapper;
import dk.ek.libary.catalog.exception.NotFoundException;
import dk.ek.libary.catalog.model.Author;
import dk.ek.libary.catalog.model.Subject;
import dk.ek.libary.catalog.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }


    @Override
    public SubjectDTO.SubjectDto createSubject(SubjectDTO.SubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        subject.setId(null);
        Subject saved = subjectRepository.save(subject);
        return subjectMapper.toDto(saved);
    }

    @Override
    public List<SubjectDTO.SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO.SubjectDto> out = new ArrayList<>();
        for (Subject s : subjects) {
            out.add(subjectMapper.toDto(s));
        }
        return out;
    }

    @Override
    public SubjectDTO.SubjectDto getSubjectById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return subjectMapper.toDto(subject.get());
        }
        throw new NotFoundException("Subject not found with id: " + id);
    }

    @Override
    public SubjectDTO.SubjectDto updateSubject(Long id, SubjectDTO.SubjectDto subjectDto) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return subjectMapper.toDto(subject.get());
        } else {
            throw new NotFoundException("Subject not found with id: " + id);
        }
    }

    @Override
    public void deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        } else {
            throw new NotFoundException("Subject not found with id: " + id);
        }
    }
}
