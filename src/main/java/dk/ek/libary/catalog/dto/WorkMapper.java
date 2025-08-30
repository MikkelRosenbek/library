package dk.ek.libary.catalog.dto;

import dk.ek.libary.catalog.model.Edition;
import dk.ek.libary.catalog.model.Work;
import dk.ek.libary.catalog.model.WorkType;

import java.util.ArrayList;
import java.util.List;

public class WorkMapper {
    public static WorkDTO.WorkDto toDto(Work work) {

        List<EditionDTO.EditionDto> editions = new ArrayList<>();

        for (var edition:work.getEditions()) {
            editions.add(EditionMapper.toDto(edition));
        }
        return new WorkDTO.WorkDto(work.getId(), work.getTitle(), work.getWorkType().name(), work.getDetails(), work.getAuthor(), editions, work.getSubjects());
    }

    public static  Work toEntity(WorkDTO.WorkDto workDto) {
        Work work = new Work();
        work.setId(workDto.id());
        work.setTitle(workDto.title());
        work.setWorkType(WorkType.valueOf(workDto.workType()));
        work.setDetails(workDto.details());
        work.setAuthor(workDto.author());
        work.setSubjects(workDto.subjects());

        List<Edition> editions = new ArrayList<>();
        for (var edDto : workDto.editions()) {
            Edition edition = EditionMapper.toEntity(edDto);
            edition.setWork(work);
            editions.add(edition);
        }
        work.setEditions(editions);

        return work;

    }


}
