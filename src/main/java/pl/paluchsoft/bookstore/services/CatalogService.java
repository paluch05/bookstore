package pl.paluchsoft.bookstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.database.IAuthorJpaRepository;
import pl.paluchsoft.bookstore.database.IBookJpaRepository;
import pl.paluchsoft.bookstore.model.Author;
import pl.paluchsoft.bookstore.model.SaveUploadFileCommand;
import pl.paluchsoft.bookstore.model.book.UpdateBookCoverCommand;
import pl.paluchsoft.bookstore.model.UploadFile;
import pl.paluchsoft.bookstore.model.book.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogService implements ICatalogService {

    private final IBookJpaRepository repository;
    private final IUploadService uploadService;
    private final IAuthorJpaRepository authorRepository;

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Book> findByTitle(final String title) {
        return repository.findByTitleContainsIgnoreCase(title);
    }

    @Override
    public Optional<Book> findOneByTitle(String title) {
        return repository
            .findAll()
            .stream()
            .filter(book -> book.getTitle().contains(title))
            .findFirst();
    }

    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Book addBook(CreateBookCommand createBookCommand) {
        Book book = toCreateBookCommand(createBookCommand);
        return repository.save(book);
    }

    private Book toCreateBookCommand(CreateBookCommand command) {
        Book book = new Book(command.getTitle(), command.getYear(), command.getPrice());
        Set<Author> authors = fetchAuthorsByIds(command.getAuthors());
        book.setAuthors(authors);
        return book;
    }

    private Set<Author> fetchAuthorsByIds(Set<Long> authors) {
        return authors
            .stream()
            .map(authorId -> authorRepository
                .findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Unable to find author with id: " + authorId)))
            .collect(Collectors.toSet());
    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookCommand updateBookCommand) {
        return repository
                .findById(updateBookCommand.getId())
                .map(book -> {
                    Book updatedBook = updateFields(updateBookCommand, book);
                    repository.save(updatedBook);
                    return UpdateBookResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateBookResponse(false,
                        Arrays.asList("Book with id: " + updateBookCommand.getId() + "not found.")));
    }

    private Book updateFields(UpdateBookCommand updateCommand, Book book) {
        if (updateCommand.getTitle() != null) {
            book.setTitle(updateCommand.getTitle());
        }
        if (updateCommand.getAuthors() != null && !updateCommand.getAuthors().isEmpty()) {
            book.setAuthors(fetchAuthorsByIds(updateCommand.getAuthors()));
        }
        if (updateCommand.getYear() != null) {
            book.setYear(updateCommand.getYear());
        }
        if (updateCommand.getPrice() != null) {
            book.setPrice(updateCommand.getPrice());
        }
        return book;
    }

    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateBookCover(UpdateBookCoverCommand command) {
        repository.findById(command.getId())
                .ifPresent(book -> {
                    UploadFile savedUpload = uploadService.saveFile(new SaveUploadFileCommand(command.getFileName(), command.getFile(), command.getContentType()));
                    book.setCoverId(savedUpload.getId());
                    repository.save(book);
                });
    }

    @Override
    public void removeBookCover(Long id) {
        repository.findById(id)
                .ifPresent(book -> {
                    if(book.getCoverId() != null) {
                        uploadService.deleteById(book.getCoverId());
                        book.setCoverId(null);
                        repository.save(book);
                    }});
    }
}
