package dk.ek.libary.catalog.repository;

import dk.ek.libary.catalog.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
