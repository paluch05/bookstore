package pl.paluchsoft.bookstore.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.paluchsoft.bookstore.database.IAuthorJpaRepository;
import pl.paluchsoft.bookstore.model.Author;
import pl.paluchsoft.bookstore.model.book.Book;
import pl.paluchsoft.bookstore.model.book.CreateBookCommand;
import pl.paluchsoft.bookstore.model.order.OrderItemCommand;
import pl.paluchsoft.bookstore.model.order.PlaceOrderCommand;
import pl.paluchsoft.bookstore.model.recipient.Recipient;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final CatalogController catalogController;
    private final OrderController orderController;
    private final IAuthorJpaRepository authorRepository;

    @PostMapping("/data")
    @Transactional
    public void initialize() {
        addBooksData();
        placeOrder();
    }

    private void placeOrder() {
        Book effectiveJava = catalogController.findOneByTitle("Effective Java")
            .orElseThrow(() -> new IllegalStateException("Cannot find a book."));
        Book javaPuzzlers = catalogController.findOneByTitle("Java Puzzlers")
            .orElseThrow(() -> new IllegalStateException("Cannot find a book."));

        Recipient recipient = Recipient
            .builder()
            .name("Jan Kowalski")
            .phone("1234567890")
            .street("Dębowa")
            .city("Gdańsk")
            .zipCode("80-872")
            .email("jankowalski@gmail.com")
            .build();

        List<OrderItemCommand> items = new ArrayList<>();
        items.add(new OrderItemCommand((long) 3,5));
        items.add(new OrderItemCommand((long) 4,15));


        PlaceOrderCommand addOrderCommand = PlaceOrderCommand
            .builder()
            .recipient(recipient)
            .items(items)
            .build();
        ResponseEntity<Object> response = orderController.addOrder(addOrderCommand);
        System.out.println("Created order with id: " + response.getStatusCodeValue());

        orderController.findAll()
            .forEach(order -> {
                System.out.println("Got order with total price: " + order.totalPrice() + " details: " + order);
            });
    }

    private void addBooksData() {
        Author joshua = new Author("Joshua", "Bloch");
        Author neal = new Author("Neal", "Gafter");

        authorRepository.save(joshua);
        authorRepository.save(neal);

        CreateBookCommand effectiveJava = new CreateBookCommand(
            "Effective Java",
            Set.of(joshua.getId()),
            2005,
            new BigDecimal("59.99"),
            50L
        );

        CreateBookCommand javaPuzzlers = new CreateBookCommand(
            "Java Puzzlers",
            Set.of(joshua.getId(), neal.getId()),
            2017,
            new BigDecimal("99.99"),
            50L
        );
        catalogController.addBook(effectiveJava);
        catalogController.addBook(javaPuzzlers);
    }

    private void addOrdersData() {}
}
