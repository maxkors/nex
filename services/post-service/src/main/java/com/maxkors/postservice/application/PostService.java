package com.maxkors.postservice.application;

import com.maxkors.postservice.api.PostRequest;
import com.maxkors.postservice.api.PostResponse;
import com.maxkors.postservice.domain.Like;
import com.maxkors.postservice.domain.Post;
import com.maxkors.postservice.infrastructure.LikeRepository;
import com.maxkors.postservice.infrastructure.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public List<PostResponse> getAll() {
        return postRepository.findAll().stream().map(PostResponse::from).collect(Collectors.toList());
    }

    public List<PostResponse> getAllByAuthorId(Long authorId) {
        return postRepository.findAllByAuthorId(authorId).stream().map(PostResponse::from).collect(Collectors.toList());
    }

    public Optional<PostResponse> getById(Long id) {
        return postRepository.findById(id).map(PostResponse::from);
    }

    public List<PostResponse> search(String text) {
        return postRepository.findAllByTextContaining(text).stream().map(PostResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public PostResponse create(PostRequest postRequest) {
        Post post = new Post(postRequest.authorId(), postRequest.text());
        Post savedPost = postRepository.save(post);
        return PostResponse.from(savedPost);
    }

    @Transactional
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        postRepository.delete(post);
    }

    @Transactional
    public PostResponse update(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        post.setText(postRequest.text());
        return PostResponse.from(post);
    }

    @Transactional
    public PostResponse toggleLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        Optional<Like> existing = likeRepository.findByUserIdAndPostId(userId, postId);
        long delta = existing.isPresent() ? -1L : 1;
        if (existing.isPresent()) {
            likeRepository.delete(existing.get());
        } else {
            likeRepository.save(new Like(userId, postId));
        }
        post.setLike_count(post.getLike_count() + delta);
        return PostResponse.from(post);
    }
}
