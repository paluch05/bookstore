package pl.paluchsoft.bookstore.services;

import pl.paluchsoft.bookstore.model.SaveUploadFileCommand;
import pl.paluchsoft.bookstore.model.UploadFile;

import java.util.Optional;

public interface IUploadService {
    UploadFile saveFile(SaveUploadFileCommand saveUploadFileCommand);

    Optional<UploadFile> getById(Long id);

    void deleteById(Long id);
}
