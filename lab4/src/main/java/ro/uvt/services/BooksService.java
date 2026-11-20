package ro.uvt.services;

import org.springframework.stereotype.Service;
import ro.uvt.models.Book;
import ro.uvt.persistence.BooksRepository;
import java.util.*;

@Service
public class BooksService {
    private final BooksRepository repository;

    public BooksService(BooksRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() { return repository.findAll(); }

    public Optional<Book> getById(int id) { return repository.findById(id); }

    public Book create(Book b) {
        return repository.save(new Book(null, b.getTitle(), b.getAuthor()));
    }

    public Optional<Book> update(int id, Book b) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(b.getTitle());
            existing.setAuthor(b.getAuthor());
            return repository.save(existing);
        });
    }

    public boolean delete(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}