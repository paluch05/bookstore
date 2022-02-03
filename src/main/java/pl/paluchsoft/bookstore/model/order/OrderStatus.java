package pl.paluchsoft.bookstore.model.order;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {
    NEW, CONFIRMED, IN_DELIVERY, DELIVERED, CANCELED, RETURNED;

    public static Optional<OrderStatus> parseString(String value) {
        return Arrays.stream(values())
                .filter(it -> StringUtils.equalsAnyIgnoreCase(it.name(), value))
                .findFirst();
    }
}
