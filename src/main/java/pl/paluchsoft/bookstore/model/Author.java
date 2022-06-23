package pl.paluchsoft.bookstore.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.paluchsoft.bookstore.model.book.Book;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@ToString(exclude = "books")
public class Author {
  @Id
  @GeneratedValue
  private Long id;

  private String firstName;

  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors")
  @JsonIgnoreProperties("authors")
  private Set<Book> books = new HashSet<>();

  @CreatedDate
  private LocalDateTime createdAt;

  public Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void addBook(Book book) {
    books.add(book);
    book.getAuthors().add(this);
  }
  public void removeBook(Book book) {
    books.remove(book);
    book.getAuthors().remove(this);
  }

  public void removeAllBooks(Set<Book> books) {
    books.forEach(book -> book.getAuthors().remove(this));
    books.clear();
  }

}
