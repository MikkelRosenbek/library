package dk.ek.libary.common;

import dk.ek.libary.catalog.model.*;
import dk.ek.libary.catalog.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    private final WorkRepository workRepository;
    private final EditionRepository editionRepository;
    private final PublisherRepository publisherRepository;

    public InitData(WorkRepository workRepository,
                    EditionRepository editionRepository,
                    PublisherRepository publisherRepository) {
        this.workRepository = workRepository;
        this.editionRepository = editionRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Work work1 = new Work(null, "The Great Gatsby", WorkType.NOVEL, "A classic novel set in the Jazz Age");
        Author fitzgerald = new Author(null, "F. Scott Fitzgerald");
        Subject americanLit = new Subject(null, "American Literature");
        Subject twenties = new Subject(null, "1920s");
        work1.addAuthor(fitzgerald);
        work1.addSubject(americanLit);
        work1.addSubject(twenties);

        Work work2 = new Work(null, "To Kill a Mockingbird", WorkType.NOVEL, "A novel about racial injustice and childhood");
        Author lee = new Author(null, "Harper Lee");
        Subject civilRights = new Subject(null, "Civil Rights");
        Subject south = new Subject(null, "American South");
        work2.addAuthor(lee);
        work2.addSubject(civilRights);
        work2.addSubject(south);

        Work work3 = new Work(null, "The Odyssey", WorkType.EPIC, "An ancient Greek epic poem");
        Author homer = new Author(null, "Homer");
        Subject mythology = new Subject(null, "Mythology");
        Subject adventure = new Subject(null, "Adventure");
        work3.addAuthor(homer);
        work3.addSubject(mythology);
        work3.addSubject(adventure);

        Work work4 = new Work(null, "A Brief History of Time", WorkType.NONFICTION, "A book on cosmology");
        Author hawking = new Author(null, "Stephen Hawking");
        Subject science = new Subject(null, "Science");
        Subject universe = new Subject(null, "Universe");
        work4.addAuthor(hawking);
        work4.addSubject(science);
        work4.addSubject(universe);

        Work work5 = new Work(null, "Hamlet", WorkType.PLAY, "A Shakespearean tragedy");
        Author shakespeare = new Author(null, "William Shakespeare");
        Subject drama = new Subject(null, "Drama");
        Subject revenge = new Subject(null, "Revenge");
        work5.addAuthor(shakespeare);
        work5.addSubject(drama);
        work5.addSubject(revenge);

        workRepository.saveAll(List.of(work1, work2, work3, work4, work5));

        // --- Publishers ---
        Publisher publisher1 = new Publisher(null, "Scribner", "New York, NY", "info@scribner.com");
        Publisher publisher2 = new Publisher(null, "J.B. Lippincott & Co.", "Philadelphia, PA", "info@lippincott.com");
        Publisher publisher3 = new Publisher(null, "Penguin Classics", "London, UK", "info@penguinclassics.com");
        Publisher publisher4 = new Publisher(null, "Bantam Books", "New York, NY", "info@bantambooks.com");
        Publisher publisher5 = new Publisher(null, "Methuen & Co.", "London, UK", "info@methuen.co.uk");

        publisherRepository.saveAll(List.of(publisher1, publisher2, publisher3, publisher4, publisher5));

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