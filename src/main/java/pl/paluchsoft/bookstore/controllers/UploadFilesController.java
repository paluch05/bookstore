package pl.paluchsoft.bookstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paluchsoft.bookstore.model.UploadFileResponse;
import pl.paluchsoft.bookstore.services.IUploadService;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/uploads")
public class UploadFilesController {
    private final IUploadService uploadService;

    @GetMapping("/{id}")
    public ResponseEntity<UploadFileResponse> getUpload(@PathVariable String id) {
        return uploadService.getById(id)
                .map(uploadFile -> {
                    UploadFileResponse response = new UploadFileResponse(
                        id, uploadFile.getContentType(), uploadFile.getFilename(), uploadFile.getCreatedAt());
                return ResponseEntity.ok(response);
                }).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> getUploadFile(@PathVariable String id) {
        return uploadService.getById(id)
                .map(uploadFile -> {
                    String contentDisposition = "attachment; filename=\"" + uploadFile.getFilename() + "\"";
                    Resource resource = new ByteArrayResource(uploadFile.getFile());
                    return ResponseEntity
                            .ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                            .contentType(MediaType.parseMediaType(uploadFile.getContentType()))
                            .body(resource);
                }).
                orElse(ResponseEntity.notFound().build());
    }
}
