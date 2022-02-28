package pl.paluchsoft.bookstore.database;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.paluchsoft.bookstore.model.book.Book;

public interface IBookJpaRepository extends JpaRepository<Book, Long> {

}
