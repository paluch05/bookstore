package pl.paluchsoft.bookstore.model.order;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.paluchsoft.bookstore.model.BaseEntity;
import pl.paluchsoft.bookstore.model.recipient.Recipient;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity {

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<OrderItem> items;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Recipient recipient;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void updateStatus(final OrderStatus newStatus) {
        this.status = status.updateStatus(newStatus);
    }
}
