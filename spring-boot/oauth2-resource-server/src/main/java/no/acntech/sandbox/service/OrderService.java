package no.acntech.sandbox.service;

import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import no.acntech.sandbox.exception.OrderNotFoundException;
import no.acntech.sandbox.model.CreateOrder;
import no.acntech.sandbox.model.Order;
import no.acntech.sandbox.model.OrderQuery;
import no.acntech.sandbox.model.OrderStatus;
import no.acntech.sandbox.repository.OrderRepository;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findOrders(OrderQuery orderQuery) {
        UUID customerId = orderQuery.getCustomerId();
        OrderStatus status = orderQuery.getStatus();
        if (customerId != null && status != null) {
            return orderRepository.findAllByCustomerIdAndStatus(customerId, status);
        } else if (customerId != null) {
            return orderRepository.findAllByCustomerId(customerId);
        } else if (status != null) {
            return orderRepository.findAllByStatus(status);
        } else {
            return orderRepository.findAll();
        }
    }

    public Order getOrder(UUID orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public Order createOrder(@NotNull CreateOrder createOrder) {
        Order order = Order.builder()
                .customerId(createOrder.getCustomerId())
                .build();
        Order createdOrder = orderRepository.save(order);
        LOGGER.debug("Created order with order-id {}", createdOrder.getOrderId());
        return createdOrder;
    }
}
