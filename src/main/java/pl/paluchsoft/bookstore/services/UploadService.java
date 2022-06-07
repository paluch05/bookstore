package pl.paluchsoft.bookstore.services;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.database.IUploadJpaRepository;
import pl.paluchsoft.bookstore.model.SaveUploadFileCommand;
import pl.paluchsoft.bookstore.model.UploadFile;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class UploadService implements IUploadService {
    private final IUploadJpaRepository repository;

    @Override
    public UploadFile saveFile(SaveUploadFileCommand saveUploadFileCommand) {
        UploadFile uploadFile = new UploadFile(
            saveUploadFileCommand.getFilename(),
            saveUploadFileCommand.getContentType(),
            saveUploadFileCommand.getFile()
        );
        repository.save(uploadFile);
        System.out.println("Upload saved: " + uploadFile.getFilename() + " with id: " + uploadFile.getId());
        return uploadFile;
    }

    @Override
    public Optional<UploadFile> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
