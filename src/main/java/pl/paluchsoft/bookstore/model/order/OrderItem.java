package pl.paluchsoft.bookstore.model.order;

import lombok.Value;
import pl.paluchsoft.bookstore.model.book.Book;

@Value
public class OrderItem {
    Long bookId;
    int quantity;
}
