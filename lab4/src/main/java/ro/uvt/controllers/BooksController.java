package ro.uvt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.commands.BookCommands.*;
import ro.uvt.models.Book;
import ro.uvt.services.BooksService;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService service;

    public BooksController(BooksService service) { this.service = service; }

    @GetMapping
    public List<Book> getAll() {
        return new GetAllBooksCommand(service).execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable int id) {
        return new GetBookCommand(service, id).execute()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return new CreateBookCommand(service, book).execute();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable int id, @RequestBody Book book) {
        return new UpdateBookCommand(service, id, book).execute()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return new DeleteBookCommand(service, id).execute()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}