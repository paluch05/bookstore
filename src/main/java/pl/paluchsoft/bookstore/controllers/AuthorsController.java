package pl.paluchsoft.bookstore.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.paluchsoft.bookstore.model.Author;
import pl.paluchsoft.bookstore.services.IAuthorService;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorsController {

    private final IAuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }
}
