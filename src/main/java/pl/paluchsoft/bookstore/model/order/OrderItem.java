package pl.paluchsoft.bookstore.model.order;

import lombok.*;
import pl.paluchsoft.bookstore.model.BaseEntity;
import pl.paluchsoft.bookstore.model.book.Book;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;

}
