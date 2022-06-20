package pl.paluchsoft.bookstore.model.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
public class CreateBookCommand {
    @NotEmpty(message = "Title cannot be empty")
    @NotBlank(message = "Title cannot be blank")
    String title;
    @NotEmpty(message = "Author cannot be empty")
    Set<Long> authors;

    @NotNull(message = "Year cannot be blank")
    Integer year;
    @NotNull(message = "Price cannot be blank")
    @DecimalMin(value = "0.00", message = "Price must be positive")
    BigDecimal price;

    public Book createBook() {
        return new Book(title, year, price);
    }

    public CreateBookCommand toCreateBookCommand() {
        return new CreateBookCommand(title, authors, year, price);
    }
}
