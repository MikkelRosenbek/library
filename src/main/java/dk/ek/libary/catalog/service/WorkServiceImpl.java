package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.WorkDTO;
import dk.ek.libary.catalog.dto.WorkMapper;
import dk.ek.libary.catalog.exception.BadRequestException;
import dk.ek.libary.catalog.exception.NotFoundException;
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
        Work saved = workRepository.save(work);
        return workMapper.toDto(saved);
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
        throw new NotFoundException("Work not found with id: " + id);
    }

    @Override
    public WorkDTO.WorkDto updateWork(Long id, WorkDTO.WorkDto dto) {
        Optional<Work> opt = workRepository.findById(id);
        if (opt.isPresent()) {
            Work existing = opt.get();
            existing.setTitle(dto.title());
            existing.setDetails(dto.details());

            if (dto.workType() != null) {
                try {


                    existing.setWorkType(WorkType.valueOf(dto.workType().toUpperCase()));
                } catch (IllegalStateException e) {
                    throw new BadRequestException("Invalid worktype: " + dto.workType());
                }
            } else {
                existing.setWorkType(null);
            }

            if (dto.authors() != null) {
                existing.getAuthors().clear();
                for (var aDto : dto.authors()) {
                    existing.addAuthor(workMapper.toEntity(dto).getAuthors().iterator().next());
                }
            }
            if (dto.subjects() != null) {
                existing.getSubjects().clear();
                for (var sDto : dto.subjects()) {
                    existing.addSubject(workMapper.toEntity(dto).getSubjects().iterator().next());
                }
            }

            Work saved = workRepository.save(existing);
            return workMapper.toDto(saved);
        } else {
            throw new NotFoundException("Work not found with id: " + id);
        }
    }

    @Override
    public void deleteWork(Long id) {
        if (workRepository.existsById(id)) {
            workRepository.deleteById(id);
        } else {
            throw new NotFoundException("Work not found with id " + id);
        }
    }

    @Override
    public List<WorkDTO.WorkDto> searchWorks(String title) {
        if (title == null || title.isBlank()) {
            throw new BadRequestException("Query parameter 'title' must not be empty");
        }
        List<Work> works = workRepository.findByTitleContaining(title);
        List<WorkDTO.WorkDto> out = new ArrayList<>();
        for (Work w : works) {
            out.add(workMapper.toDto(w));
        }
        return out;
    }
}
