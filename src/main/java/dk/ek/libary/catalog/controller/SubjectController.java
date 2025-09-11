package dk.ek.libary.catalog.controller;

import dk.ek.libary.catalog.dto.SubjectDTO;
import dk.ek.libary.catalog.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO.SubjectDto>> getAllSubjects() {
        try {
            return ResponseEntity.ok(subjectService.getAllSubjects());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO.SubjectDto> getSubjectById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(subjectService.getSubjectById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<SubjectDTO.SubjectDto> createSubject(@RequestBody SubjectDTO.SubjectDto subjectDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subjectDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO.SubjectDto> updateSubject(@PathVariable Long id,
                                                               @RequestBody SubjectDTO.SubjectDto subjectDto) {
        try {
            return ResponseEntity.ok(subjectService.updateSubject(id, subjectDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}