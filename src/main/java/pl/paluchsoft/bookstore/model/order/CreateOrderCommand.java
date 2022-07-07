package pl.paluchsoft.bookstore.model.order;

import lombok.Builder;
import lombok.Data;
import pl.paluchsoft.bookstore.model.recipient.Recipient;
import java.util.List;

@Data
@Builder
public class CreateOrderCommand {
    List<OrderItemCommand> items;
    Recipient recipient;

//    public PlaceOrderCommand placeOrderCommand() {
//        List<OrderItem> orderItems = items
//                .stream()
//                .map(item -> new OrderItem(item.bookId, item.quantity))
//                .collect(Collectors.toList());
//        return new PlaceOrderCommand(orderItems, recipient.toRecipient());
//    }
}
