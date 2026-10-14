package com.maxkors.socialservice.application;

import com.maxkors.socialservice.domain.Follow;
import com.maxkors.socialservice.infrastructure.FollowRepository;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public List<Long> getFollowerIds(Long followeeId) {
        return followRepository.findByFolloweeId(followeeId);
    }

    @Transactional
    public Follow follow(Long followerId, Long followeeId) {
        return followRepository.findByFollowerIdAndFolloweeId(followerId, followeeId).orElseGet(() -> {
            try {
                return followRepository.saveAndFlush(new Follow(followerId, followeeId));
            } catch (DataIntegrityViolationException e) {
                return followRepository
                    .findByFollowerIdAndFolloweeId(followerId, followeeId)
                    .orElseThrow(() -> new IllegalStateException("failed to create follow"));
            }
        });
    }

    @Transactional
    public void unfollow(Long followerId, Long followeeId) {
        followRepository.deleteByFollowerIdAndFolloweeId(followerId, followeeId);
    }
}
