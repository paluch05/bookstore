package pl.paluchsoft.bookstore.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class UpdateBookCommand {
    Long id;
    String title;
    Set<Long> authors;
    Integer year;
    BigDecimal price;

    public UpdateBookCommand toUpdateBookCommand(Long id) {
        return new UpdateBookCommand(id, title, authors, year, price);
    }
}
