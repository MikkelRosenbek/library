package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.WorkDTO;
import dk.ek.libary.catalog.dto.WorkMapper;
import dk.ek.libary.catalog.model.Work;
import dk.ek.libary.catalog.model.WorkType;
import dk.ek.libary.catalog.repository.AuthorRepository;
import dk.ek.libary.catalog.repository.SubjectRepository;
import dk.ek.libary.catalog.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;

    public WorkServiceImpl(WorkRepository workRepository, WorkMapper workMapper,
                           AuthorRepository authorRepository, SubjectRepository subjectRepository) {
        this.workRepository = workRepository;
        this.workMapper = workMapper;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
    }


    @Override
    public WorkDTO.WorkDto createWork(WorkDTO.WorkDto workDto) {
        Work work = workMapper.toEntity(workDto);
        work.setId(null);
        return workMapper.toDto(workRepository.save(work));
    }

    @Override
    public List<WorkDTO.WorkDto> getAllWorks() {
        List<Work> works = workRepository.findAll();
        List<WorkDTO.WorkDto> workDtos = new ArrayList<>();
        for (var work : works) {
            workDtos.add(workMapper.toDto(work));
        }
        return workDtos;
    }

    @Override
    public WorkDTO.WorkDto getWorkById(Long id) {
        Optional<Work> workOpt = workRepository.findById(id);
        if (workOpt.isPresent()) {
            return workMapper.toDto(workOpt.get());
        }
        throw new RuntimeException("Work not found with id: " + id);
    }

    @Override
    public WorkDTO.WorkDto updateWork(Long id, WorkDTO.WorkDto workDto) {
        Optional<Work> existingWork = workRepository.findById(id);
        if (existingWork.isPresent()) {
            Work updatedWork = existingWork.get();

            updatedWork.setTitle(workDto.title());
            updatedWork.setWorkType(WorkType.valueOf(workDto.workType().toUpperCase()));
            updatedWork.setDetails(workDto.details());
            updatedWork.setAuthor(workDto.author());
            updatedWork.setSubjects(workDto.subjects());

            return workMapper.toDto(workRepository.save(updatedWork));
        }
        throw new RuntimeException("Work not found with id: " + id);
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
        List<WorkDTO.WorkDto> workDtos = new ArrayList<>();
        for (var work : works) {
            workDtos.add(workMapper.toDto(work));
        }
        return workDtos;
    }


    @Override
    public WorkDTO.WorkDto createWork(WorkDTO.WorkDto workDto) {
        Work work = workMapper.toEntity(workDto);
        work.setId(null);
        return workMapper.toDto(workRepository.save(work));
    }




}
