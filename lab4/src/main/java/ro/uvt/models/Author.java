package ro.uvt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    protected Author() {}

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public List<Book> getBooks() { return books; }

    public void print() {
        System.out.println("Author: " + name + " " + surname);
    }
}