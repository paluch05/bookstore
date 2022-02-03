package pl.paluchsoft.bookstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.paluchsoft.bookstore.model.book.Book;
import pl.paluchsoft.bookstore.model.order.Order;
import pl.paluchsoft.bookstore.model.order.OrderItem;
import pl.paluchsoft.bookstore.model.order.RichOrder;
import pl.paluchsoft.bookstore.model.order.RichOrderItem;
import pl.paluchsoft.bookstore.repositories.CatalogRepository;
import pl.paluchsoft.bookstore.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QueryOrderService implements IQueryOrder{
    private final OrderRepository orderRepository;
    private final CatalogRepository catalogRepository;

    @Override
    public List<RichOrder> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::toRichOrder)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RichOrder> findById(Long id) {
        return orderRepository.findById(id).map(this::toRichOrder);
    }

    @Override
    public void removeById(Long id) {
        orderRepository.deleteById(id);
    }

    private RichOrder toRichOrder(Order order) {
        List<RichOrderItem> richItems = toRichItems(order.getItems());
        return new RichOrder(
                order.getId(), order.getStatus(), richItems, order.getRecipient(), order.getCreatedAt()
        );
    }

    private List<RichOrderItem> toRichItems(List<OrderItem> items) {
        return items.stream()
                .map(item -> {
                    Book book = catalogRepository
                            .findById(item.getBookId())
                            .orElseThrow(() -> new IllegalStateException("Unable to find book with id: " + item.getBookId()));
                    return new RichOrderItem(book, item.getQuantity());
                })
                .collect(Collectors.toList());
    }
}
