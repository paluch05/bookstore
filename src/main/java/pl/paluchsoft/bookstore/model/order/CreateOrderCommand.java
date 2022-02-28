package pl.paluchsoft.bookstore.model.order;

import lombok.Builder;
import lombok.Data;
import pl.paluchsoft.bookstore.model.recipient.RecipientCommand;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CreateOrderCommand {
    List<OrderItemCommand> items;
    RecipientCommand recipientCommand;

    public PlaceOrderCommand placeOrderCommand() {
        List<OrderItem> orderItems = items
                .stream()
                .map(item -> new OrderItem(item.bookId, item.quantity))
                .collect(Collectors.toList());
        return new PlaceOrderCommand(orderItems, recipientCommand.toRecipient());
    }
}
