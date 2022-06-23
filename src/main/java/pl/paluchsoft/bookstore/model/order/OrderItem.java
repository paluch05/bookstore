package pl.paluchsoft.bookstore.model.order;

import lombok.*;
import pl.paluchsoft.bookstore.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {
    private Long bookId;
    private int quantity;

}
