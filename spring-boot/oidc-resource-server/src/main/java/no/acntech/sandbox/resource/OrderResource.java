package no.acntech.sandbox.resource;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import no.acntech.sandbox.model.CreateOrder;
import no.acntech.sandbox.model.Order;
import no.acntech.sandbox.model.OrderQuery;
import no.acntech.sandbox.service.OrderService;

@RequestMapping(path = "orders")
@RestController
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping
    public List<Order> get(final OrderQuery orderQuery) {
        return orderService.findOrders(orderQuery);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(path = "{orderId}")
    public Order get(@PathVariable("orderId") final UUID orderId) {
        return orderService.getOrder(orderId);
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<Void> post(@Valid @RequestBody final CreateOrder createOrder) {
        Order order = orderService.createOrder(createOrder);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{orderId}")
                .buildAndExpand(order.getOrderId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
