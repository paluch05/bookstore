package pl.paluchsoft.bookstore.services;

import pl.paluchsoft.bookstore.model.order.RichOrder;

import java.util.List;
import java.util.Optional;

public interface IQueryOrder {
    List<RichOrder> findAll();

    Optional<RichOrder> findById(Long id);

    void removeById(Long id);
}
