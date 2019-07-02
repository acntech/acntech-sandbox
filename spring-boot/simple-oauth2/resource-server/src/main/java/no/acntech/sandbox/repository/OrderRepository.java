package no.acntech.sandbox.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import no.acntech.sandbox.exception.OrderExistsException;
import no.acntech.sandbox.model.Order;
import no.acntech.sandbox.model.OrderStatus;

/**
 * Simple repository of orders, simulating a JPA repository.
 */
@Repository
public class OrderRepository {

    private static final List<Order> ORDERS = new ArrayList<>();

    public Optional<Order> findByOrderId(UUID orderId) {
        return ORDERS.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst();
    }

    public List<Order> findAllByCustomerId(UUID customerId) {
        return ORDERS.stream()
                .filter(o -> o.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public List<Order> findAllByCustomerIdAndStatus(UUID customerId, OrderStatus status) {
        return ORDERS.stream()
                .filter(o -> o.getCustomerId().equals(customerId))
                .filter(o -> o.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Order> findAllByStatus(OrderStatus status) {
        return ORDERS.stream()
                .filter(o -> o.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Order> findAll() {
        return ORDERS;
    }

    public Order save(Order order) {
        Optional<Order> existingOrder = findByOrderId(order.getOrderId());
        if (existingOrder.isPresent()) {
            throw new OrderExistsException(order.getOrderId());
        } else {
            ORDERS.add(order);
            return order;
        }
    }
}
