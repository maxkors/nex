package com.maxkors.postservice.api;

import com.maxkors.postservice.domain.Post;

import java.time.Instant;

public record PostResponse(
        Long id,
        Long authorId,
        String text,
        Long like_count,
        Instant created_at
) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getAuthorId(), post.getText(), post.getLike_count(), post.getCreatedAt());
    }
}
