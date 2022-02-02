//package pl.paluchsoft.bookstore;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import pl.paluchsoft.bookstore.services.CatalogService;
//import pl.paluchsoft.bookstore.services.OrderService;
//import pl.paluchsoft.bookstore.controllers.CatalogController;
//import pl.paluchsoft.bookstore.controllers.OrderController;
//import pl.paluchsoft.bookstore.model.Recipient;
//import pl.paluchsoft.bookstore.model.book.AddBookCommand;
//import pl.paluchsoft.bookstore.model.book.Book;
//import pl.paluchsoft.bookstore.model.book.UpdateBookCommand;
//import pl.paluchsoft.bookstore.model.book.UpdateBookResponse;
//import pl.paluchsoft.bookstore.model.order.AddOrderCommand;
//import pl.paluchsoft.bookstore.model.order.AddOrderResponse;
//import pl.paluchsoft.bookstore.model.order.OrderItem;
//
//import java.math.BigDecimal;
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
//        Recipient recipient = Recipient
//                .builder()
//                .name("Jan Kowalski")
//                .phone("1234567890")
//                .street("Dębowa")
//                .city("Gdańsk")
//                .zipCode("80-872")
//                .email("jankowalski@gmail.com")
//                .build();
//
//        AddOrderCommand addOrderCommand = AddOrderCommand
//                .builder()
//                .recipient(recipient)
//                .item(new OrderItem(panTadeusz, 2))
//                .item(new OrderItem(chlopi, 7))
//                .build();
//        AddOrderResponse response = orderController.addOrder(addOrderCommand);
//        log.info("Created order with id: " + response.getOrderId());
//        System.out.println("Created order with id: " + response.getOrderId());
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
//                    UpdateBookResponse response = catalogController.updateBook(command);
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
//        catalogController.addBook(new AddBookCommand("Pan Tadeusz", "Adam Mickiewicz", 1834, new BigDecimal("19.99")));
//        catalogController.addBook(new AddBookCommand("Ogniem i Mieczem", "Henryk Sienkiewicz", 1884, new BigDecimal("39.99")));
//        catalogController.addBook(new AddBookCommand("Chłopi", "Władysław Reymont", 1904, new BigDecimal("29.99")));
//        catalogController.addBook(new AddBookCommand("Pan Wołodyjowski", "Henryk Sienkiewicz", 1888, new BigDecimal("14.99")));
//    }
//
//    private void addOrdersData() {}
//}
