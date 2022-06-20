package pl.paluchsoft.bookstore.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.paluchsoft.bookstore.model.book.Book;

public interface IBookJpaRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthors_firstNameContainsIgnoreCaseOrAuthors_lastNameContainsIgnoreCase(String firstName, String lastName);

    @Query(
        "SELECT b from Book b JOIN b.authors a " +
            " WHERE " +
            " lower(a.firstName) LIKE lower(concat('%', :name, '%')) " +
            " OR " +
            " lower(a.lastName) LIKE lower(concat('%', :name, '%')) ")
    List<Book> findByAuthor(@Param("name") String name);

}
