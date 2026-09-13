package com.maxkors.postservice.api;

import com.maxkors.postservice.application.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class LikeController {

    private final PostService postService;

    public LikeController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<PostResponse> likePost(@PathVariable Long id, @RequestBody LikeRequest likeReq) {
        return ResponseEntity.ok(postService.toggleLike(id, likeReq.userId()));
    }
}
