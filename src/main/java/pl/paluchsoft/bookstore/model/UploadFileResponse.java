package pl.paluchsoft.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@AllArgsConstructor
public class UploadFileResponse {
    Long id;
    String contentType;
    String filename;
    LocalDateTime createdAt;
}
