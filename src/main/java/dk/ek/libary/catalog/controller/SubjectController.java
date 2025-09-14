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
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO.SubjectDto> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectDTO.SubjectDto> createSubject(@RequestBody SubjectDTO.SubjectDto subjectDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subjectDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO.SubjectDto> updateSubject(@PathVariable Long id,
                                                                   @RequestBody SubjectDTO.SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.updateSubject(id, subjectDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
            subjectService.deleteSubject(id);
            return ResponseEntity.noContent().build();
    }
}