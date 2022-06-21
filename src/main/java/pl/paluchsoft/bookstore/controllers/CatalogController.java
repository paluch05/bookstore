package pl.paluchsoft.bookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.paluchsoft.bookstore.model.book.UpdateBookCoverCommand;
import pl.paluchsoft.bookstore.model.book.*;
import pl.paluchsoft.bookstore.services.ICatalogService;
import pl.paluchsoft.bookstore.services.IUploadService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final ICatalogService catalogService;
    private final IUploadService uploadService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAll() {
        return catalogService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable Long id) {
        return catalogService.findById(id);
    }

    @GetMapping("/books/{title}")
    public List<Book> findByTitle(@PathVariable String title) {
        return catalogService.findByTitle(title);
    }

    @GetMapping("/title/{title}")
    public Optional<Book> findOneByTitle(@PathVariable String title) {
        return catalogService.findOneByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> findByAuthor(@PathVariable String author) {
        return catalogService.findByAuthor(author);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addBook(@Valid @RequestBody CreateBookCommand createBookCommand) {
        Book book = catalogService.addBook(createBookCommand);
        return ResponseEntity.ok().build();
//        return ResponseEntity.created(new CreatedURI("/" + book.getId().toString()).uri()).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UpdateBookResponse updateBook(@PathVariable Long id, @RequestBody UpdateBookCommand updateBookCommand) {
        UpdateBookResponse response = catalogService.updateBook(updateBookCommand.toUpdateBookCommand(id));
        if (!response.isSuccess()) {
            String message = String.join(", ", response.getErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Long id) {
        catalogService.deleteBookById(id);
    }

    @PutMapping(value = "/{id}/cover", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBookCover(@PathVariable Long id, @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("Got file: " + file.getOriginalFilename());
        catalogService.updateBookCover(new UpdateBookCoverCommand(
                id, file.getBytes(), file.getContentType(), file.getOriginalFilename()
        ));
    }

    @DeleteMapping("/{id}/cover")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookCover(@PathVariable Long id) {
        catalogService.removeBookCover(id);
    }
}

