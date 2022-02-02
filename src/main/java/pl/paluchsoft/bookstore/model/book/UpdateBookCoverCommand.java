package pl.paluchsoft.bookstore.model.book;

import lombok.Value;

@Value
public class UpdateBookCoverCommand {
    Long id;
    byte[] file;
    String contentType;
    String fileName;
}
