package pl.paluchsoft.bookstore.model.order;

import lombok.Data;

@Data
public class OrderItemCommand {
    Long bookId;
    int quantity;

    public OrderItemCommand(Long bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
