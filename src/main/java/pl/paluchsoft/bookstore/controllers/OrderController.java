package pl.paluchsoft.bookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.paluchsoft.bookstore.services.IOrderService;
import pl.paluchsoft.bookstore.model.order.AddOrderCommand;
import pl.paluchsoft.bookstore.model.order.AddOrderResponse;
import pl.paluchsoft.bookstore.model.order.Order;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    public List<Order> findAll() {
       return orderService.findAll();
    }

    public Optional<Order> findById(long id) {
        return orderService.findById(id);
    }

    public AddOrderResponse addOrder(AddOrderCommand addOrderCommand) {
        return orderService.addOrder(addOrderCommand);
    }

    void deleteOrderById(Long id) {
        orderService.deleteOrderById(id);
    }
}
