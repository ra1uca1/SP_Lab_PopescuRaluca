package ro.uvt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book extends Section {
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    protected Book() {}

    public Book(String title) { super(title); }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public List<Author> getAuthors() { return authors; }

    public void addContent(Element e) { add(e); }

    public void addContentFirst(Element e) { children.add(0, (BaseElement) e); }

    public void setTitle(String title) { this.title = title; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (BaseElement e : children) {
            e.accept(visitor);
        }
    }
}