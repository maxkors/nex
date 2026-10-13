package com.maxkors.postservice.api;

import com.maxkors.postservice.domain.Follow;

import java.time.Instant;

public record FollowResponse(
        Long followerId,
        Long followeeId,
        Instant createdAt
) {
    public static FollowResponse from(Follow follow) {
        return new FollowResponse(follow.getFollowerId(), follow.getFolloweeId(), follow.getCreatedAt());
    }
}
