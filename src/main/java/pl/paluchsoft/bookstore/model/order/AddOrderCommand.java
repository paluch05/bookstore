package pl.paluchsoft.bookstore.model.order;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import pl.paluchsoft.bookstore.model.Recipient;

import java.util.List;

@Builder
@Value
public class AddOrderCommand {
    @Singular
    List<OrderItem> items;
    Recipient recipient;
}
