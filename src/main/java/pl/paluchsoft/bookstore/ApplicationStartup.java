//package pl.paluchsoft.bookstore;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import pl.paluchsoft.bookstore.controllers.CatalogController;
//import pl.paluchsoft.bookstore.controllers.OrderController;
//import pl.paluchsoft.bookstore.model.book.CreateBookCommand;
//import pl.paluchsoft.bookstore.model.book.Book;
//import pl.paluchsoft.bookstore.model.book.UpdateBookCommand;
//import pl.paluchsoft.bookstore.model.book.UpdateBookResponse;
//import pl.paluchsoft.bookstore.model.order.CreateOrderCommand;
//import pl.paluchsoft.bookstore.model.order.OrderItem;
//import pl.paluchsoft.bookstore.model.order.OrderItemCommand;
//import pl.paluchsoft.bookstore.model.recipient.Recipient;
//import pl.paluchsoft.bookstore.model.recipient.RecipientCommand;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class ApplicationStartup implements CommandLineRunner {
//
//    Logger log = LoggerFactory.getLogger(ApplicationStartup.class);
//    private final CatalogController catalogController;
//    private final OrderController orderController;
//    private final String title;
//    private final Long limit;
//    private final String author;
//    private final Long id;
//
//    public ApplicationStartup(CatalogController catalogController,
//                              OrderController orderController,
//                              @Value("${bookstore.catalog.query}") String title,
//                              @Value("${bookstore.catalog.author}") String author,
//                              @Value("${bookstore.catalog.limit}") Long limit,
//                              @Value("${bookstore.catalog.id}") Long id) {
//        this.catalogController = catalogController;
//        this.orderController = orderController;
//        this.title = title;
//        this.limit = limit;
//        this.author = author;
//        this.id = id;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        addBooksData();
////        searchCatalog();
////        placeOrder();
//    }
//
//    private void placeOrder() {
//        Book panTadeusz = catalogController.findOneByTitle("Pan Tadeusz").orElseThrow(() -> new IllegalStateException("Cannot find a book."));
//        Book chlopi = catalogController.findOneByTitle("Chłopi").orElseThrow(() -> new IllegalStateException("Cannot find a book."));
//        RecipientCommand recipient = RecipientCommand
//                .builder()
//                .name("Jan Kowalski")
//                .phone("1234567890")
//                .street("Dębowa")
//                .city("Gdańsk")
//                .zipCode("80-872")
//                .email("jankowalski@gmail.com")
//                .build();
//
//        List<OrderItemCommand> items = new ArrayList<>();
//        items.add(new OrderItemCommand((long) 1,5));
//        items.add(new OrderItemCommand((long) 2,15));
//
//
//        CreateOrderCommand addOrderCommand = CreateOrderCommand
//                .builder()
//                .recipientCommand(recipient)
//                .items(items)
//                .build();
//        ResponseEntity<Object> response = orderController.addOrder(addOrderCommand);
//        System.out.println("Created order with id: " + response.getStatusCodeValue());
//
//        orderController.findAll()
//                .forEach(order -> {
//                    System.out.println("Got order with total price: " + order.totalPrice() + " details: " + order);
//                });
//    }
//
//    private void searchCatalog() {
//        findByTitle();
//        findByAuthor();
//        findAll();
//        findAndUpdate();
//        findOneByTitleAndAuthor();
//        deleteById(id);
//        findAll();
//    }
//
//    private void findAndUpdate() {
//        log.info("Updating book");
//        catalogController.findOneByTitleAndAuthor("Pan", "Mickiewicz")
//                .ifPresent(book -> {
//                    UpdateBookCommand command = UpdateBookCommand
//                            .builder()
//                            .id(book.getId())
//                            .title("Pan Tadeusz, czyli Ostatni Zajazd na Litwie")
//                            .author(book.getAuthor())
//                            .year(book.getYear())
//                            .build();
//                    UpdateBookResponse response = catalogController.updateBook(command.getId(), command);
//                    System.out.println("Updating book result: " + response.isSuccess());
//                });
//    }
//
//    private void findOneByTitleAndAuthor() {
//        Optional<Book> findOneByTitleAndAuthor = catalogController.findOneByTitleAndAuthor(title, author);
//        System.out.println(findOneByTitleAndAuthor.get());
//    }
//
//    private void findAll() {
//        List<Book> findAll = catalogController.findAll();
//        findAll.forEach(System.out::println);
//    }
//
//    private void findByAuthor() {
//        List<Book> booksByAuthor = catalogController.findByAuthor(author);
//        booksByAuthor.forEach(System.out::println);
//    }
//
//    private void findByTitle() {
//        List<Book> books = catalogController.findByTitle(title);
//        books.stream().limit(limit).forEach(System.out::println);
//    }
//
//    private void deleteById(Long id) {
//        catalogController.deleteBookById(id);
//    }
//
//
//    private void addBooksData() {
//        catalogController.addBook(new CreateBookCommand("Pan Tadeusz", "Adam Mickiewicz", 1834, new BigDecimal("19.99")));
//        catalogController.addBook(new CreateBookCommand("Ogniem i Mieczem", "Henryk Sienkiewicz", 1884, new BigDecimal("39.99")));
//        catalogController.addBook(new CreateBookCommand("Chłopi", "Władysław Reymont", 1904, new BigDecimal("29.99")));
//        catalogController.addBook(new CreateBookCommand("Pan Wołodyjowski", "Henryk Sienkiewicz", 1888, new BigDecimal("14.99")));
//    }
//
//    private void addOrdersData() {}
//}
