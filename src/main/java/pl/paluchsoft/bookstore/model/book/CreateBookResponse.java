package pl.paluchsoft.bookstore.model.book;

import lombok.Value;

import java.util.List;

import static java.util.Collections.emptyList;

@Value
public class CreateBookResponse {
    String message;
    boolean success;
    List<String> errors;

    public static CreateBookResponse SUCCESS = new CreateBookResponse("Book successfully created", true, emptyList());
}
