package pl.paluchsoft.bookstore.database;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.paluchsoft.bookstore.model.UploadFile;

public interface IUploadJpaRepository extends JpaRepository<UploadFile, Long> {
}
