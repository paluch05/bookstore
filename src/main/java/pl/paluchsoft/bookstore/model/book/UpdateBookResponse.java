package pl.paluchsoft.bookstore.model.book;

import lombok.Value;

import java.util.List;

import static java.util.Collections.*;

@Value
public class UpdateBookResponse {
    public static UpdateBookResponse SUCCESS = new UpdateBookResponse(true, emptyList());
    boolean success;
    List<String> errors;
}
