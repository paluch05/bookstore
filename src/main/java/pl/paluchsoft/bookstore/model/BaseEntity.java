package pl.paluchsoft.bookstore.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = "uuid")
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String uuid = UUID.randomUUID().toString();

    @Version
    private long version;
}
