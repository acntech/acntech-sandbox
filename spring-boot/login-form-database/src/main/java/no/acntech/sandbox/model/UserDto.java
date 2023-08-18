package no.acntech.sandbox.model;

import java.util.List;

public record UserDto(
        String username,
        String firstName,
        String lastName,
        String email,
        List<String> roles
) {
}
