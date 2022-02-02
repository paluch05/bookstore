package pl.paluchsoft.bookstore.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.model.SaveUploadFileCommand;
import pl.paluchsoft.bookstore.model.UploadFile;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UploadService implements IUploadService {
    private final Map<String, UploadFile> storage = new ConcurrentHashMap<>();

    @Override
    public UploadFile saveFile(SaveUploadFileCommand saveUploadFileCommand) {
        String newId = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
        UploadFile uploadFile = new UploadFile(
                newId, saveUploadFileCommand.getFile(), saveUploadFileCommand.getContentType(), saveUploadFileCommand.getFilename(), LocalDateTime.now());
        storage.put(uploadFile.getId(), uploadFile);
        System.out.println("Upload saved: " + uploadFile.getFilename() + " with id: " + newId);
        return uploadFile;
    }

    @Override
    public Optional<UploadFile> getById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(String id) {
        storage.remove(id);
    }

}
