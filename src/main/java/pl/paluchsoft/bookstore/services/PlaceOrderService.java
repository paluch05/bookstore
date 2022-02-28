package pl.paluchsoft.bookstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.database.IOrderJpaRepository;
import pl.paluchsoft.bookstore.model.order.Order;
import pl.paluchsoft.bookstore.model.order.OrderStatus;
import pl.paluchsoft.bookstore.model.order.PlaceOrderCommand;
import pl.paluchsoft.bookstore.model.order.PlaceOrderResponse;

@Service
@AllArgsConstructor
public class PlaceOrderService implements IPlaceOrder{
    private final IOrderJpaRepository repository;

    @Override
    public PlaceOrderResponse placeOrderResponse(PlaceOrderCommand placeOrderCommand) {
        Order order = Order
                .builder()
                .recipient(placeOrderCommand.getRecipient())
                .items(placeOrderCommand.getItems())
                .build();
        Order save = repository.save(order);
        return PlaceOrderResponse.success(order.getId());
    }

    @Override
    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {

        repository.findById(id)
                .ifPresent(order -> {
                    order.setStatus(status);
                    repository.save(order);
                });
    }
}
