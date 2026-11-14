package ro.uvt.services;

import org.springframework.stereotype.Service;
import ro.uvt.models.Book;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BooksService {
    private final Map<Integer, Book> books = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public List<Book> getAll() { return new ArrayList<>(books.values()); }
    public Optional<Book> getById(int id) { return Optional.ofNullable(books.get(id)); }

    public Book create(Book b) {
        int id = nextId.getAndIncrement();
        Book saved = new Book(id, b.title(), b.author());
        books.put(id, saved);
        return saved;
    }

    public Optional<Book> update(int id, Book b) {
        if (!books.containsKey(id)) return Optional.empty();
        Book updated = new Book(id, b.title(), b.author());
        books.put(id, updated);
        return Optional.of(updated);
    }

    public boolean delete(int id) { return books.remove(id) != null; }
}