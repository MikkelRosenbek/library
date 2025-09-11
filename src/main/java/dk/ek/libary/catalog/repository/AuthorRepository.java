package dk.ek.libary.catalog.repository;

import dk.ek.libary.catalog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
