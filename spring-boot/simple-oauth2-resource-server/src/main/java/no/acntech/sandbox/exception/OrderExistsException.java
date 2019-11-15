package no.acntech.sandbox.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class OrderExistsException extends RuntimeException {

    public OrderExistsException(UUID orderId) {
        super("An order already exists for order-id " + orderId);
    }
}
