package pl.paluchsoft.bookstore.model.order;

import lombok.*;
import pl.paluchsoft.bookstore.model.recipient.Recipient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private OrderStatus status = OrderStatus.NEW;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    // transient - don't serialize object, don't save this obj in db, it's temporary
    private transient Recipient recipient;

    private LocalDateTime createdAt;

}
