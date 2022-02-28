package pl.paluchsoft.bookstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.database.IBookJpaRepository;
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

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findOneByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().contains(title))
                .findFirst();
    }

    public List<Book> findByAuthor(String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getAuthor().contains(author))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().contains(title))
                .filter(book -> book.getAuthor().contains(author))
                .findFirst();
    }

    @Override
    public Book addBook(CreateBookCommand createBookCommand) {
        Book book = createBookCommand.createBook();
        return repository.save(book);
    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookCommand updateBookCommand) {
        return repository
                .findById(updateBookCommand.getId())
                .map(book -> {
                    Book updatedBook = updateBookCommand.updateFields(book);
                    repository.save(updatedBook);
                    return UpdateBookResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateBookResponse(false,
                        Arrays.asList("Book with id: " + updateBookCommand.getId() + "not found.")));
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
