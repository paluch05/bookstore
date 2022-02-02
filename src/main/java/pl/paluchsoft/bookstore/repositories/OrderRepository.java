package pl.paluchsoft.bookstore.repositories;

import pl.paluchsoft.bookstore.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order save(Order order);
}
