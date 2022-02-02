package pl.paluchsoft.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class SaveUploadFileCommand {
    String filename;
    byte[] file;
    String contentType;

}
