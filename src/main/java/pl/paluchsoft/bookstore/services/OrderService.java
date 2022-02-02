package pl.paluchsoft.bookstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.model.order.AddOrderCommand;
import pl.paluchsoft.bookstore.model.order.AddOrderResponse;
import pl.paluchsoft.bookstore.model.order.Order;
import pl.paluchsoft.bookstore.repositories.MemoryOrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final MemoryOrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Order> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public AddOrderResponse addOrder(AddOrderCommand addOrderCommand) {
        Order order = Order.builder()
                        .items(addOrderCommand.getItems())
                        .recipient(addOrderCommand.getRecipient())
                        .build();
        Order savedOrder = repository.save(order);
        return AddOrderResponse.success(savedOrder.getId());
    }

    @Override
    public void deleteOrderById(Long id) {
    }


}
