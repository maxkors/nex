package com.maxkors.socialservice.api;

import jakarta.validation.constraints.Positive;

public record FollowRequest(@Positive Long userId) {
}
