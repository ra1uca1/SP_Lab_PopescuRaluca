package ro.uvt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.commands.BookCommands.*;
import ro.uvt.commands.CommandExecutor;
import ro.uvt.models.Author;
import ro.uvt.models.Book;
import ro.uvt.models.Image;
import ro.uvt.models.Paragraph;
import ro.uvt.models.Section;
import ro.uvt.models.Table;
import ro.uvt.services.BooksService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService service;
    private final CommandExecutor executor;

    public BooksController(BooksService service, CommandExecutor executor) {
        this.service = service;
        this.executor = executor;
    }

    @GetMapping
    public List<Book> getAll() {
        return executor.executeSync(new GetAllBooksCommand(service));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable int id) {
        return executor.executeSync(new GetBookCommand(service, id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ASINCRON: raspunde imediat 202 + id-ul task-ului
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody Book book) {
        String taskId = executor.submitAsync(new CreateBookCommand(service, book));
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of("taskId", taskId, "statusUrl", "/books/status/" + taskId));
    }

    // endpoint de test: creeaza o carte completa (autor, capitol, paragraf, imagine, tabel)
    @PostMapping("/sample")
    public Book createSample() {
        Book book = new Book("Noapte buna, copii!");
        book.addAuthor(new Author("Radu Pavel", "Gheo"));

        Section c1 = new Section("Capitolul 1");
        c1.add(new Paragraph("Moto capitol"));
        c1.add(new Image("imagine1"));
        c1.add(new Table("Tabel 1"));

        book.addContent(new Paragraph("Multumesc celor care ..."));
        book.addContent(c1);

        return service.create(book);
    }

    // polling: clientul intreaba daca s-a terminat
    @GetMapping("/status/{taskId}")
    public ResponseEntity<Map<String, Object>> status(@PathVariable String taskId) {
        String status = executor.getStatus(taskId);
        if (status.equals("NOT_FOUND")) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> body = new java.util.HashMap<>();
        body.put("status", status);
        if (status.equals("DONE")) {
            body.put("result", executor.getResult(taskId));
        }
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable int id, @RequestBody Book book) {
        return executor.executeSync(new UpdateBookCommand(service, id, book))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return executor.executeSync(new DeleteBookCommand(service, id))
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}