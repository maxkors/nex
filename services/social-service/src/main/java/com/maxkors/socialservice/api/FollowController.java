package com.maxkors.socialservice.api;

import com.maxkors.socialservice.application.FollowService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/follow")
    public ResponseEntity<FollowResponse> follow(
        @PathVariable Long userId,
        @Valid @RequestBody FollowRequest followRequest
    ) {
        return ResponseEntity.ok(FollowResponse.from(followService.follow(followRequest.userId(), userId)));
    }

    @DeleteMapping("/follow")
    public ResponseEntity<Void> unfollow(@PathVariable Long userId, @Valid @RequestBody FollowRequest followRequest) {
        followService.unfollow(followRequest.userId(), userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/followers")
    public ResponseEntity<List<Long>> getFollowerIds(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.getFollowerIds(userId));
    }
}
