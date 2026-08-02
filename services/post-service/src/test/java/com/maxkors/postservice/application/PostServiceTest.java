package com.maxkors.postservice.application;

import com.maxkors.postservice.api.PostDTO;
import com.maxkors.postservice.domain.Post;
import com.maxkors.postservice.infrastructure.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void getByIdReturnsMappedPostWhenItExists() {
        Post post = new Post(7L, "Hello World!");
        post.setId(42L);
        when(postRepository.findById(42L)).thenReturn(Optional.of(post));

        Optional<PostDTO> result = postService.getById(42L);

        assertThat(result).contains(new PostDTO(42L, 7L, "Hello World!"));
        verify(postRepository).findById(42L);
    }

    @Test
    void getByIdReturnsEmptyWhenPostDoesNotExist() {
        when(postRepository.findById(42L)).thenReturn(Optional.empty());

        Optional<PostDTO> result = postService.getById(42L);

        assertThat(result).isEmpty();
        verify(postRepository).findById(42L);
    }
}
