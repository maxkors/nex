package com.maxkors.postservice.api;

import com.maxkors.postservice.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PostDTO(Long id,
                      @NotNull @Positive Long authorId,
                      @NotBlank String text) {
    public static PostDTO from(Post post) {
        return new PostDTO(post.getId(), post.getAuthorId(), post.getText());
    }
}
