package dk.ek.libary.catalog.service;

import dk.ek.libary.catalog.dto.WorkDTO;
import java.util.List;

public interface WorkService {

    List<WorkDTO.WorkDto> getAllWorks();
    WorkDTO.WorkDto getWorkById(Long id);
    WorkDTO.WorkDto createWork(WorkDTO.WorkDto workDto);
    WorkDTO.WorkDto updateWork(Long id, WorkDTO.WorkDto workDto);
    void deleteWork(Long id);
    List<WorkDTO.WorkDto> searchWorks(String title);
}