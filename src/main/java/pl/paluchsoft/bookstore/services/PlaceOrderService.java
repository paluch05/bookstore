package pl.paluchsoft.bookstore.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.paluchsoft.bookstore.database.IBookJpaRepository;
import pl.paluchsoft.bookstore.database.IOrderJpaRepository;
import pl.paluchsoft.bookstore.model.book.Book;
import pl.paluchsoft.bookstore.model.order.Order;
import pl.paluchsoft.bookstore.model.order.OrderItem;
import pl.paluchsoft.bookstore.model.order.OrderItemCommand;
import pl.paluchsoft.bookstore.model.order.OrderStatus;
import pl.paluchsoft.bookstore.model.order.PlaceOrderCommand;
import pl.paluchsoft.bookstore.model.order.PlaceOrderResponse;

@Service
@AllArgsConstructor
@Transactional
public class PlaceOrderService implements IPlaceOrder{
    private final IOrderJpaRepository repository;

    private final IBookJpaRepository bookJpaRepository;

    @Override
    public PlaceOrderResponse placeOrderResponse(PlaceOrderCommand placeOrderCommand) {
        Set<OrderItem> items = placeOrderCommand
            .getItems()
            .stream()
            .map(this::toOrderItem)
            .collect(Collectors.toSet());

        Order order = Order
                .builder()
                .recipient(placeOrderCommand.getRecipient())
                .items(items)
                .build();
        Order save = repository.save(order);
        bookJpaRepository.saveAll(updateBooks(items));
        return PlaceOrderResponse.success(save.getId());
    }

    private Set<Book> updateBooks(final Set<OrderItem> items) {
        return items.stream()
            .map(item -> {
                Book book = item.getBook();
                book.setAvailable(book.getAvailable() - item.getQuantity());
                return book;
            }).collect(Collectors.toSet());
    }

    private OrderItem toOrderItem(OrderItemCommand command) {
        Book book = bookJpaRepository.getOne(command.getBookId());
        int quantity = command.getQuantity();
        if (book.getAvailable() >= quantity) {
            return new OrderItem(book, quantity);
        }
        throw new IllegalArgumentException(
            "Too many copies of book " + book.getTitle() + " requested " + quantity
                + " of " + book.getAvailable() + " available");
    }

    @Override
    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        repository.findById(id)
                .ifPresent(order -> {
                    order.updateStatus(status);
                    repository.save(order);
                });
    }
}
