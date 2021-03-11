package no.acntech.sandbox.model;

import javax.validation.constraints.NotNull;

import java.util.UUID;

public class CreateOrder {

    @NotNull
    private UUID customerId;

    public UUID getCustomerId() {
        return customerId;
    }
}
