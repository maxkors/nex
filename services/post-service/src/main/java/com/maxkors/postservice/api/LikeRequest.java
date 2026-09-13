package com.maxkors.postservice.api;

import jakarta.validation.constraints.Positive;

public record LikeRequest(@Positive Long userId) {
}
