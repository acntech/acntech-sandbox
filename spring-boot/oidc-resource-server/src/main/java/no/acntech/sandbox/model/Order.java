package no.acntech.sandbox.model;

import javax.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Order {

    @NotNull
    private UUID orderId;
    @NotNull
    private UUID customerId;
    @NotNull
    private OrderStatus status;
    private ZonedDateTime created;
    private ZonedDateTime modified;

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public ZonedDateTime getModified() {
        return modified;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private UUID customerId;

        private Builder() {
        }

        public Builder customerId(UUID customerId) {
            this.customerId = customerId;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.orderId = UUID.randomUUID();
            order.customerId = this.customerId;
            order.status = OrderStatus.PENDING;
            order.created = ZonedDateTime.now();
            return order;
        }
    }
}
