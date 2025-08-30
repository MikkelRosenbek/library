package dk.ek.libary.catalog.repository;

import dk.ek.libary.catalog.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
