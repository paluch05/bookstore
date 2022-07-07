package pl.paluchsoft.bookstore.model.order;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {
    NEW {
        @Override
        public OrderStatus updateStatus(OrderStatus status) {
            if (status == PAID) {
                return PAID;
            }
            if (status == CANCELED) {
                return CANCELED;
            }
            if (status == ABANDONED) {
                return ABANDONED;
            }
            return super.updateStatus(status);
        }
    },
    PAID {
        @Override
        public OrderStatus updateStatus(OrderStatus status) {
            if (status == SHIPPED) {
                return SHIPPED;
            }
            return super.updateStatus(status);
        }
    },
    CANCELED,
    ABANDONED,
    SHIPPED;

    public static Optional<OrderStatus> parseString(String value) {
        return Arrays.stream(values())
                .filter(it -> StringUtils.equalsAnyIgnoreCase(it.name(), value))
                .findFirst();
    }

    public OrderStatus updateStatus(OrderStatus status) {
        throw new IllegalArgumentException("Unable to mark " + this.name() + " order as " + status.name());
    }
}
