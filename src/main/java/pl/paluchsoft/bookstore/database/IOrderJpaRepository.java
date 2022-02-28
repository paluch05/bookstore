package pl.paluchsoft.bookstore.database;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.paluchsoft.bookstore.model.order.Order;

public interface IOrderJpaRepository extends JpaRepository<Order, Long> {
}
