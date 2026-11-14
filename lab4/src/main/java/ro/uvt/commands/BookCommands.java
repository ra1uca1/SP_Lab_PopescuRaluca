package ro.uvt.commands;

import ro.uvt.models.Book;
import ro.uvt.services.BooksService;
import java.util.*;

public class BookCommands {

    public record GetAllBooksCommand(BooksService service) implements Command<List<Book>> {
        public List<Book> execute() { return service.getAll(); }
    }

    public record GetBookCommand(BooksService service, int id) implements Command<Optional<Book>> {
        public Optional<Book> execute() { return service.getById(id); }
    }

    public record CreateBookCommand(BooksService service, Book book) implements Command<Book> {
        public Book execute() { return service.create(book); }
    }

    public record UpdateBookCommand(BooksService service, int id, Book book) implements Command<Optional<Book>> {
        public Optional<Book> execute() { return service.update(id, book); }
    }

    public record DeleteBookCommand(BooksService service, int id) implements Command<Boolean> {
        public Boolean execute() { return service.delete(id); }
    }
}