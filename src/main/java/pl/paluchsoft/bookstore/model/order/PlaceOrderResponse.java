package pl.paluchsoft.bookstore.model.order;

import pl.paluchsoft.bookstore.commons.Either;

public class PlaceOrderResponse extends Either<String, Long> {
    public PlaceOrderResponse(boolean success, String left, Long right) {
        super(success, left, right);
    }

    public static PlaceOrderResponse success(Long orderId) {
        return new PlaceOrderResponse(true, null, orderId);
    }

    public static PlaceOrderResponse failure(String error) {
        return new PlaceOrderResponse(false, error, null);
    }

}
