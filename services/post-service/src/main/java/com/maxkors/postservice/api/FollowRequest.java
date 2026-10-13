package com.maxkors.postservice.api;

import jakarta.validation.constraints.Positive;

public record FollowRequest(@Positive Long userId) {
}
