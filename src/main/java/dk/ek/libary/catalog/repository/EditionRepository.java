package dk.ek.libary.catalog.repository;

import dk.ek.libary.catalog.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditionRepository extends JpaRepository<Edition, Long> {
}
