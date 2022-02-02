package pl.paluchsoft.bookstore.services;

import pl.paluchsoft.bookstore.model.order.AddOrderCommand;
import pl.paluchsoft.bookstore.model.order.AddOrderResponse;
import pl.paluchsoft.bookstore.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> findAll();

    Optional<Order> findById(long id);

    AddOrderResponse addOrder(AddOrderCommand addOrderCommand);

    void deleteOrderById(Long id);
}
