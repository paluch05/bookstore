package pl.paluchsoft.bookstore.model.recipient;

import lombok.Data;

@Data
public class RecipientCommand {
    String name;
    String phone;
    String street;
    String city;
    String zipCode;
    String email;

    public Recipient toRecipient() {
        return new Recipient(name, phone, street, city, zipCode, email);
    }
}
