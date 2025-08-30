package dk.ek.libary.common;

import dk.ek.libary.catalog.model.Work;
import dk.ek.libary.catalog.model.WorkType;
import dk.ek.libary.catalog.repository.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final WorkRepository workRepository;

    public InitData(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Add example works
        Work work1 = new Work(null, "The Great Gatsby", WorkType.NOVEL, "A classic novel set in the Jazz Age", "F. Scott Fitzgerald", "American literature, 1920s");
        Work work2 = new Work(null, "To Kill a Mockingbird", WorkType.NOVEL, "A novel about racial injustice and childhood", "Harper Lee", "Civil rights, American South");
        Work work3 = new Work(null, "The Odyssey", WorkType.EPIC, "An ancient Greek epic poem", "Homer", "Mythology, Adventure");
        Work work4 = new Work(null, "A Brief History of Time", WorkType.NONFICTION, "A book on cosmology", "Stephen Hawking", "Science, Universe");
        Work work5 = new Work(null, "Hamlet", WorkType.PLAY, "A Shakespearean tragedy", "William Shakespeare", "Drama, Revenge");

        workRepository.save(work1);
        workRepository.save(work2);
        workRepository.save(work3);
        workRepository.save(work4);
        workRepository.save(work5);
    }
}
