package com.maxkors.postservice.api;

import com.maxkors.postservice.domain.Post;

public record PostResponse(
        Long id,
        Long authorId,
        String text
) {
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getAuthorId(), post.getText());
    }
}
