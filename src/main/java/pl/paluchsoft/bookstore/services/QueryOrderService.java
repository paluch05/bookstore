package pl.paluchsoft.bookstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.paluchsoft.bookstore.database.IBookJpaRepository;
import pl.paluchsoft.bookstore.database.IOrderJpaRepository;
import pl.paluchsoft.bookstore.model.book.Book;
import pl.paluchsoft.bookstore.model.order.Order;
import pl.paluchsoft.bookstore.model.order.OrderItem;
import pl.paluchsoft.bookstore.model.order.RichOrder;
import pl.paluchsoft.bookstore.model.order.RichOrderItem;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QueryOrderService implements IQueryOrder{
    private final IOrderJpaRepository orderRepository;
    private final IBookJpaRepository catalogRepository;

    @Override
    @Transactional
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
        return new RichOrder(
            order.getId(),
            order.getStatus(),
            order.getItems(),
            order.getRecipient(),
            order.getCreatedAt()
        );
    }
}
