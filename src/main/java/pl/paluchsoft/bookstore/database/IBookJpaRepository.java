package pl.paluchsoft.bookstore.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.paluchsoft.bookstore.model.book.Book;

public interface IBookJpaRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN FETCH b.authors")
    List<Book> findAllEager();

    List<Book> findByTitleContainsIgnoreCase(String title);

    Optional<Book> findById(Long id);

    Optional<Book> findFirstByTitleContainsIgnoreCase(String title);

//    List<Book> findByAuthors_firstNameContainsIgnoreCaseOrAuthors_lastNameContainsIgnoreCase(String firstName, String lastName);

    @Query(
        "SELECT b from Book b JOIN b.authors a " +
            " WHERE " +
            " lower(a.firstName) LIKE lower(concat('%', :name, '%')) " +
            " OR " +
            " lower(a.lastName) LIKE lower(concat('%', :name, '%')) ")
    List<Book> findByAuthor(@Param("name") String name);

}
