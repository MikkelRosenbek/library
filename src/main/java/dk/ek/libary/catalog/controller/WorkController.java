package dk.ek.libary.catalog.controller;

import dk.ek.libary.catalog.model.Work;
import dk.ek.libary.catalog.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public ResponseEntity<List<Work>> getAllWorks() {
        try {
            List<Work> works = workService.getAllWorks();
            return ResponseEntity.ok(works);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long id) {
        try {
            Work work = workService.getWorkById(id);
            return ResponseEntity.ok(work);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Work> addWork(@RequestBody Work work) {
        try {
            Work newWork = workService.createWork(work);
            return ResponseEntity.ok(newWork);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(work);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> updateWork(@PathVariable Long id, @RequestBody Work updatedWork) {
        try {
            workService.updateWork(id, updatedWork);
            return ResponseEntity.ok(updatedWork);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Work> deleteWork(@PathVariable Long id) {
        try {
            workService.deleteWork(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Work>> searchWorks (@RequestParam String title) {
        try {
            List<Work> works = workService.searchWorks(title);
            return ResponseEntity.ok(works);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
