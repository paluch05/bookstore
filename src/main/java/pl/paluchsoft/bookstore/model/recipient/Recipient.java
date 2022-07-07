package pl.paluchsoft.bookstore.model.recipient;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;
import pl.paluchsoft.bookstore.model.BaseEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipient extends BaseEntity {
    private String name;
    private String phone;
    private String street;
    private String city;
    private String zipCode;
    private String email;

    public Recipient toRecipient() {
        return new Recipient(name, phone, street, city, zipCode, email);
    }
}
