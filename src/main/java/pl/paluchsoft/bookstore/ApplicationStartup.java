package pl.paluchsoft.bookstore;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.paluchsoft.bookstore.controllers.CatalogController;
import pl.paluchsoft.bookstore.controllers.OrderController;
import pl.paluchsoft.bookstore.database.IAuthorJpaRepository;
import pl.paluchsoft.bookstore.model.Author;
import pl.paluchsoft.bookstore.model.book.CreateBookCommand;
import pl.paluchsoft.bookstore.model.book.Book;
import pl.paluchsoft.bookstore.model.order.CreateOrderCommand;
import pl.paluchsoft.bookstore.model.order.OrderItemCommand;
import pl.paluchsoft.bookstore.model.recipient.RecipientCommand;
import pl.paluchsoft.bookstore.services.IQueryOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogController catalogController;
    private final OrderController orderController;
    private final IAuthorJpaRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        addBooksData();
        placeOrder();
    }

    private void placeOrder() {
        Book effectiveJava = catalogController.findOneByTitle("Effective Java").orElseThrow(() -> new IllegalStateException("Cannot find a book."));
        Book javaPuzzlers = catalogController.findOneByTitle("Java Puzzlers").orElseThrow(() -> new IllegalStateException("Cannot find a book."));
        RecipientCommand recipient = RecipientCommand
                .builder()
                .name("Jan Kowalski")
                .phone("1234567890")
                .street("Dębowa")
                .city("Gdańsk")
                .zipCode("80-872")
                .email("jankowalski@gmail.com")
                .build();

        List<OrderItemCommand> items = new ArrayList<>();
        items.add(new OrderItemCommand((long) 1,5));
        items.add(new OrderItemCommand((long) 2,15));


        CreateOrderCommand addOrderCommand = CreateOrderCommand
                .builder()
                .recipientCommand(recipient)
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
            new BigDecimal("59.99")
        );

        CreateBookCommand javaPuzzlers = new CreateBookCommand(
            "Java Puzzlers",
            Set.of(joshua.getId(), neal.getId()),
            2017,
            new BigDecimal("99.99")
        );
        catalogController.addBook(effectiveJava);
        catalogController.addBook(javaPuzzlers);
    }

    private void addOrdersData() {}
}
