package com.maxkors.postservice.api;

import com.maxkors.postservice.application.PostService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // for testing purposes
    @GetMapping
    public List<PostDTO> getAll() {
        return postService.getAll();
    }  

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable Long id) {
        return postService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostDTO>> getAllByAuthorId(@PathVariable Long authorId) {
        return ResponseEntity.ok(postService.getAllByAuthorId(authorId));
    }  

    @PostMapping
    public ResponseEntity<PostDTO> create(@Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.update(id, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().<Void>build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> search(@RequestParam String query) {
        return ResponseEntity.ok(postService.search(query));
    }
}