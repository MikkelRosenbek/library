package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.model.Work;
import dk.ek.libary.catalog.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public Work createWork(Work work) {
        work.setId(null);
        return workRepository.save(work);
    }

    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    public Work getWorkById(Long id) {
        Optional<Work> work = workRepository.findById(id);
        if (work.isPresent()) {
            return work.get();
        }
        throw new RuntimeException("Work not found with id: " + id);
    }

    public Work updateWork(Long id, Work uodatedWork) {
        Work work = this.getWorkById(id);
        work.setTitle(uodatedWork.getTitle());
        work.setWorkType(uodatedWork.getWorkType());
        work.setDetails(uodatedWork.getDetails());
        work.setAuthors(uodatedWork.getAuthors());
        work.setSubjects(uodatedWork.getSubjects());
        return workRepository.save(work);
    }


    public void deleteWork(Long id) {
        Work work = this.getWorkById(id);
        if (work == null) {
            throw new RuntimeException("Work not found with id: " + id);
        }
        workRepository.delete(work);
    }

    public List<Work> searchWorks(String title) {
        if (title == null || title.isEmpty()) {
            throw new RuntimeException("No works found with title " + title);
        }
        return workRepository.findByTitleContaining(title);
    }
}
