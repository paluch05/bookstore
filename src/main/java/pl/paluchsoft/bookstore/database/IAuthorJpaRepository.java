package pl.paluchsoft.bookstore.database;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.paluchsoft.bookstore.model.Author;

public interface IAuthorJpaRepository extends JpaRepository<Author, Long> {
}
