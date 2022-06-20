package pl.paluchsoft.bookstore.model.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pl.paluchsoft.bookstore.model.Author;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "authors")
@RequiredArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    @JsonIgnoreProperties("books")
    private Set<Author> authors;
    private Integer year;
    private BigDecimal price;

    private Long coverId;

    public Book(String title, Integer year, BigDecimal price) {
        this.title = title;
        this.year = year;
        this.price = price;
    }
}
