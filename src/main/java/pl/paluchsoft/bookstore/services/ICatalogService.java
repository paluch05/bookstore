package pl.paluchsoft.bookstore.services;

import pl.paluchsoft.bookstore.model.book.UpdateBookCoverCommand;
import pl.paluchsoft.bookstore.model.book.*;

import java.util.List;
import java.util.Optional;

public interface ICatalogService {

    List<Book> findAll();

    List<Book> findByTitle(String title);

    Optional<Book> findOneByTitle(String title);

    List<Book> findByAuthor(String author);

    Optional<Book> findById(Long id);

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    Book addBook(CreateBookCommand createBookCommand);

    UpdateBookResponse updateBook(UpdateBookCommand updateBookCommand);

    void deleteBookById(Long id);

    void updateBookCover(UpdateBookCoverCommand command);

    public void removeBookCover(Long id);
}
