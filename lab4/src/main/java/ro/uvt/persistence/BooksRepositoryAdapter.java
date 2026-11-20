package ro.uvt.persistence;

import org.springframework.stereotype.Component;
import ro.uvt.models.Book;
import java.util.List;
import java.util.Optional;

@Component
public class BooksRepositoryAdapter implements CrudRepository<Book, Integer> {
    private final BooksRepository jpaRepository;

    public BooksRepositoryAdapter(BooksRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Book> findAll() { return jpaRepository.findAll(); }

    @Override
    public Optional<Book> findById(Integer id) { return jpaRepository.findById(id); }

    @Override
    public Book save(Book entity) { return jpaRepository.save(entity); }

    @Override
    public boolean existsById(Integer id) { return jpaRepository.existsById(id); }

    @Override
    public void deleteById(Integer id) { jpaRepository.deleteById(id); }
}