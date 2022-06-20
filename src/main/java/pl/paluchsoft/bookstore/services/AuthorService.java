package pl.paluchsoft.bookstore.services;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.database.IAuthorJpaRepository;
import pl.paluchsoft.bookstore.model.Author;

@Service
@AllArgsConstructor
public class AuthorService implements IAuthorService {
    private final IAuthorJpaRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
