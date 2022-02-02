package pl.paluchsoft.bookstore.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class UploadFile {
    String id;
    byte[] file;
    String contentType;
    String filename;
    LocalDateTime createdAt;
}
