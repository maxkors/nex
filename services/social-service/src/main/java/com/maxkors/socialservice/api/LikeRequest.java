package com.maxkors.socialservice.api;

import jakarta.validation.constraints.Positive;

public record LikeRequest(@Positive Long userId) {
}
