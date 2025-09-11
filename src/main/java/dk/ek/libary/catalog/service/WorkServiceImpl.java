package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.WorkDTO;
import dk.ek.libary.catalog.dto.WorkMapper;
import dk.ek.libary.catalog.model.Work;
import dk.ek.libary.catalog.model.WorkType;
import dk.ek.libary.catalog.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final WorkMapper workMapper;


    public WorkServiceImpl(WorkRepository workRepository, WorkMapper workMapper) {
        this.workRepository = workRepository;
        this.workMapper = workMapper;
    }


    @Override
    public WorkDTO.WorkDto createWork(WorkDTO.WorkDto dto) {
        Work work = workMapper.toEntity(dto);
        work.setId(null);
        return workMapper.toDto(workRepository.save(work));
    }

    @Override
    public List<WorkDTO.WorkDto> getAllWorks() {
        List<Work> works = workRepository.findAll();
        List<WorkDTO.WorkDto> out = new ArrayList<>();
        for (Work w : works) {
            out.add(workMapper.toDto(w));
        }
        return out;
    }

    @Override
    public WorkDTO.WorkDto getWorkById(Long id) {
        Optional<Work> opt = workRepository.findById(id);
        if (opt.isPresent()) {
            return workMapper.toDto(opt.get());
        }
        throw new RuntimeException("Work not found with id: " + id);
    }

    @Override
    public WorkDTO.WorkDto updateWork(Long id, WorkDTO.WorkDto dto) {
        Work existing = workRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Work not found with id: " +id));

        // Primitve fields
        existing.setTitle(dto.title());
        if (dto.workType() != null) {
            existing.setWorkType(WorkType.valueOf(dto.workType().toUpperCase()));
        } else {
            existing.setWorkType(null);
        }
        existing.setDetails(dto.details());

        if (dto.authors() != null) {
            existing.getAuthors().clear();
            for (var aDto : dto.authors()) {
                existing.addAuthor(workMapper.toEntity(dto).getAuthors().iterator().next());
            }
        }

        return workMapper.toDto(workRepository.save(existing));
    }

    @Override
    public void deleteWork(Long id) {
        if (workRepository.existsById(id)) {
            workRepository.deleteById(id);
        } else {
            throw new RuntimeException("Work not found with id " + id);
        }
    }

    @Override
    public List<WorkDTO.WorkDto> searchWorks(String title) {
        if (title == null || title.isEmpty()) {
            throw new RuntimeException("No works found with title " + title);
        }
        List<Work> works = workRepository.findByTitleContaining(title);
        List<WorkDTO.WorkDto> out = new ArrayList<>();
        for (Work w : works) {
            out.add(workMapper.toDto(w));
        }
        return out;
    }
}
