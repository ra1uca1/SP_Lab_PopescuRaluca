package ro.uvt.models;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;

    protected Book() {}

    public Book(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
}