package pl.paluchsoft.bookstore.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import pl.paluchsoft.bookstore.model.recipient.Recipient;

import java.util.List;

@Builder
@Value
@AllArgsConstructor
public class PlaceOrderCommand {
    @Singular
    List<OrderItemCommand> items;
    Recipient recipient;
}
