package pl.paluchsoft.bookstore.model.recipient;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Recipient {
    String name;
    String phone;
    String street;
    String city;
    String zipCode;
    String email;
}
