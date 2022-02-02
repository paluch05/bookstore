package pl.paluchsoft.bookstore.model.order;
import lombok.Value;
import java.util.Arrays;
import java.util.List;
import static java.util.Collections.emptyList;

@Value
public class AddOrderResponse {
    boolean success;
    Long orderId;
    List<String> errors;

    public static AddOrderResponse success(Long orderId) {
        return new AddOrderResponse(true, orderId, emptyList());
    }

    public static AddOrderResponse failure(String ... errors) {
        return new AddOrderResponse(false, null, Arrays.asList(errors));
    }
}
