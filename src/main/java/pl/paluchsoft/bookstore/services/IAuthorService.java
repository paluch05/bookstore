package pl.paluchsoft.bookstore.services;

import java.util.List;

import pl.paluchsoft.bookstore.model.Author;

public interface IAuthorService {
    List<Author> findAll();

}
