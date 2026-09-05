package com.maxkors.postservice.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PostRequest(
        @NotNull @Positive Long authorId,
        @NotBlank String text
) {
}
