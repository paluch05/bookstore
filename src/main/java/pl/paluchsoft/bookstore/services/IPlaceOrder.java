package pl.paluchsoft.bookstore.services;

import pl.paluchsoft.bookstore.model.order.OrderStatus;
import pl.paluchsoft.bookstore.model.order.PlaceOrderCommand;
import pl.paluchsoft.bookstore.model.order.PlaceOrderResponse;

public interface IPlaceOrder {
    PlaceOrderResponse placeOrderResponse(PlaceOrderCommand placeOrderCommand);

    void deleteOrderById(Long id);

    void updateOrderStatus(Long id, OrderStatus status);
}
