package pl.paluchsoft.bookstore.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class UpdateBookCommand {
    Long id;
    String title;
    String author;
    Integer year;
    BigDecimal price;

    public Book updateFields(Book book) {
        if (title != null) {
            book.setTitle(title);
        }
        if (author != null) {
            book.setAuthor(author);
        }
        if (year != null) {
            book.setYear(year);
        }
        if (price != null) {
            book.setPrice(price);
        }
        return book;
    }

    public UpdateBookCommand toUpdateBookCommand(Long id) {
        return new UpdateBookCommand(id, title, author, year, price);
    }
}
