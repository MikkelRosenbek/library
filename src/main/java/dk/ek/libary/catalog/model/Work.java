package dk.ek.libary.catalog.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private WorkType workType;
    private String details;

    @ManyToMany
    private Author author;

    @ManyToMany
    private Subject subjects;

    @OneToMany(mappedBy = "work", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Edition> editions = new ArrayList<>();


    public Work() {
    }

    public Work(Long id, String title, WorkType workType, String details, Author author, Subject subjects) {
        this.id = id;
        this.title = title;
        this.workType = workType;
        this.details = details;
        this.author = author;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author authors) {
        this.author = authors;
    }

    public Subject getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject subjects) {
        this.subjects = subjects;
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
        edition.setWork(this);
    }

    public void removeEdition(Edition edition) {
        editions.remove(edition);
        edition.setWork(null);
    }
}
