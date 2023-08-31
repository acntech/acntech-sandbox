package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PrefixDto(@NotNull Long id, @NotBlank String prefix) {
}
