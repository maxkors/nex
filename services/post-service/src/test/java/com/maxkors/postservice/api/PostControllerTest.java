package com.maxkors.postservice.api;

import com.maxkors.postservice.application.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    @Test
    void getByIdReturnsPostWhenItExists() throws Exception {
        PostDTO post = new PostDTO(42L, 7L, "Hello World!");
        when(postService.getById(42L)).thenReturn(Optional.of(post));

        mockMvc.perform(get("/posts/{id}", 42L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(42))
                .andExpect(jsonPath("$.authorId").value(7))
                .andExpect(jsonPath("$.text").value("Hello World!"));

        verify(postService).getById(42L);
    }

    @Test
    void getByIdReturnsNotFoundWhenPostDoesNotExist() throws Exception {
        when(postService.getById(42L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/posts/{id}", 42L))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

        verify(postService).getById(42L);
    }
}
