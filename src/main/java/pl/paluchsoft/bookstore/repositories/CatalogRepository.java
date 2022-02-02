package pl.paluchsoft.bookstore.repositories;

import pl.paluchsoft.bookstore.model.book.Book;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository {
    List<Book> findAll();

    Book save(Book book);

    Optional<Book> findById(Long Id);

    void deleteBookById(Long id);
}
