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
        return ResponseEntity.ok(workService.getAllWorks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDTO.WorkDto> getWorkById(@PathVariable Long id) {
        return ResponseEntity.ok(workService.getWorkById(id));
    }

    @PostMapping
    public ResponseEntity<WorkDTO.WorkDto> addWork(@RequestBody WorkDTO.WorkDto workDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workService.createWork(workDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkDTO.WorkDto> updateWork(@PathVariable Long id, @RequestBody WorkDTO.WorkDto workDto) {
        return ResponseEntity.ok(workService.updateWork(id, workDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        workService.deleteWork(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkDTO.WorkDto>> searchWorks(@RequestParam String title) {
        return ResponseEntity.ok(workService.searchWorks(title));
    }
}