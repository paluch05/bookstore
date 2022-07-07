package pl.paluchsoft.bookstore.model.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pl.paluchsoft.bookstore.model.Author;
import pl.paluchsoft.bookstore.model.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "authors")
@RequiredArgsConstructor
@Entity
public class Book extends BaseEntity {
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable
    @JsonIgnoreProperties("books")
    private Set<Author> authors = new HashSet<>();
    private Integer year;
    private BigDecimal price;

    private Long coverId;

    private Long available;

    public Book(String title, Integer year, BigDecimal price, Long available) {
        this.title = title;
        this.year = year;
        this.price = price;
        this.available = available;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }
    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public void removeAllAuthors(Set<Author> authors) {
        authors.forEach(author -> author.getBooks().remove(this));
        authors.clear();
    }
}
