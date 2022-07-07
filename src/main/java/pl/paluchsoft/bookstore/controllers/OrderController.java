package pl.paluchsoft.bookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.paluchsoft.bookstore.model.order.*;
import pl.paluchsoft.bookstore.services.IPlaceOrder;
import pl.paluchsoft.bookstore.services.IQueryOrder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final IQueryOrder queryOrderService;
    private final IPlaceOrder placeOrderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RichOrder> findAll() {
       return queryOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RichOrder> findById(long id) {
        return queryOrderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addOrder(@RequestBody PlaceOrderCommand command) {
        return placeOrderService
                .placeOrderResponse(command)
                .handle(
                        orderId -> ResponseEntity.created(new CreatedURI("/" + orderId).uri()).build(),
                        error -> ResponseEntity.badRequest().body(error)
                );
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatus updateOrderStatus) {
        OrderStatus orderStatus = OrderStatus
                .parseString(updateOrderStatus.getStatus())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown status" + updateOrderStatus.getStatus()));
        placeOrderService.updateOrderStatus(id, orderStatus);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOrderById(@PathVariable Long id) {
        placeOrderService.deleteOrderById(id);
    }
}
