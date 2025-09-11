package dk.ek.libary.catalog.controller;

import dk.ek.libary.catalog.dto.WorkDTO;
import dk.ek.libary.catalog.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public ResponseEntity<List<WorkDTO.WorkDto>> getAllWorks() {
        try {
            return ResponseEntity.ok(workService.getAllWorks());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDTO.WorkDto> getWorkById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(workService.getWorkById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<WorkDTO.WorkDto> addWork(@RequestBody WorkDTO.WorkDto workDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(workService.createWork(workDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkDTO.WorkDto> updateWork(@PathVariable Long id, @RequestBody WorkDTO.WorkDto workDto) {
        try {
            return ResponseEntity.ok(workService.updateWork(id, workDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        try {
            workService.deleteWork(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkDTO.WorkDto>> searchWorks(@RequestParam String title) {
        try {
            return ResponseEntity.ok(workService.searchWorks(title));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}