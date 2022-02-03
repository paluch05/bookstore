package pl.paluchsoft.bookstore.model.order;

import lombok.Value;
import pl.paluchsoft.bookstore.model.recipient.Recipient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class RichOrder {
    Long id;
    OrderStatus status;
    List<RichOrderItem> items;
    Recipient recipient;
    LocalDateTime createdAt;

    BigDecimal totalPrice() {
        return items.stream()
                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
