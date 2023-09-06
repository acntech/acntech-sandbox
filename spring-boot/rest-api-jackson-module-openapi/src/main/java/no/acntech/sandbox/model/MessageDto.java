package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;

public record MessageDto(@NotBlank String text) {
}
