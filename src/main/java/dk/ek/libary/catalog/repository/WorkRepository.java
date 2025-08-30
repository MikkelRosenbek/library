package dk.ek.libary.catalog.repository;

import dk.ek.libary.catalog.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository <Work, Long> {
    List<Work> findByTitleContaining(String title);
}
