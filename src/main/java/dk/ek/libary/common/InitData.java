package dk.ek.libary.common;

import dk.ek.libary.catalog.model.Edition;
import dk.ek.libary.catalog.model.Publisher;
import dk.ek.libary.catalog.model.Work;
import dk.ek.libary.catalog.model.WorkType;
import dk.ek.libary.catalog.repository.EditionRepository;
import dk.ek.libary.catalog.repository.PublisherRepository;
import dk.ek.libary.catalog.repository.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    private final WorkRepository workRepository;
    private final EditionRepository editionRepository;
    private final PublisherRepository publisherRepository;

    public InitData(WorkRepository workRepository, EditionRepository editionRepository, PublisherRepository publisherRepository) {
        this.workRepository = workRepository;
        this.editionRepository = editionRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // --- Works ---
        Work work1 = new Work(null, "The Great Gatsby", WorkType.NOVEL, "A classic novel set in the Jazz Age", "F. Scott Fitzgerald", "American literature, 1920s");
        Work work2 = new Work(null, "To Kill a Mockingbird", WorkType.NOVEL, "A novel about racial injustice and childhood", "Harper Lee", "Civil rights, American South");
        Work work3 = new Work(null, "The Odyssey", WorkType.EPIC, "An ancient Greek epic poem", "Homer", "Mythology, Adventure");
        Work work4 = new Work(null, "A Brief History of Time", WorkType.NONFICTION, "A book on cosmology", "Stephen Hawking", "Science, Universe");
        Work work5 = new Work(null, "Hamlet", WorkType.PLAY, "A Shakespearean tragedy", "William Shakespeare", "Drama, Revenge");

        workRepository.saveAll(List.of(work1, work2, work3, work4, work5));

        // --- Publishers ---
        Publisher publisher1 = new Publisher(null, "Scribner", "New York, NY", "info@scribner.com");
        Publisher publisher2 = new Publisher(null, "J.B. Lippincott & Co.", "Philadelphia, PA", "info@lippincott.com");
        Publisher publisher3 = new Publisher(null, "Penguin Classics", "London, UK", "info@penguinclassics.com");
        Publisher publisher4 = new Publisher(null, "Bantam Books", "New York, NY", "info@bantambooks.com");
        Publisher publisher5 = new Publisher(null, "Methuen & Co.", "London, UK", "info@methuen.co.uk");

        publisher1 = publisherRepository.save(publisher1);
        publisher2 = publisherRepository.save(publisher2);
        publisher3 = publisherRepository.save(publisher3);
        publisher4 = publisherRepository.save(publisher4);
        publisher5 = publisherRepository.save(publisher5);

        // --- Editions ---
        Edition edition1 = new Edition(null, "1st Edition", 1925, "Hardcover");
        edition1.setPublisher(publisher1);
        work1.addEdition(edition1);

        Edition edition2 = new Edition(null, "1st Edition", 1960, "Hardcover");
        edition2.setPublisher(publisher2);
        work2.addEdition(edition2);

        Edition edition3 = new Edition(null, "Penguin Classics Edition", 1999, "Paperback");
        edition3.setPublisher(publisher3);
        work3.addEdition(edition3);

        Edition edition4 = new Edition(null, "Bantam Books Edition", 1988, "Paperback");
        edition4.setPublisher(publisher4);
        work4.addEdition(edition4);

        Edition edition5 = new Edition(null, "Methuen Edition", 1603, "Hardcover");
        edition5.setPublisher(publisher5);
        work5.addEdition(edition5);

        editionRepository.saveAll(List.of(edition1, edition2, edition3, edition4, edition5));
    }
}
