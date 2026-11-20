package ro.uvt.observer;

import org.springframework.stereotype.Component;
import ro.uvt.models.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BooksSubject implements Subject {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    private Book lastBook;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(lastBook);
        }
    }

    // schimba starea subiectului si anunta observatorii
    public void add(Book book) {
        this.lastBook = book;
        notifyObservers();
    }
}