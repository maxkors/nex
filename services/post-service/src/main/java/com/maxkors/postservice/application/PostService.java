package com.maxkors.postservice.application;

import com.maxkors.postservice.api.PostRequest;
import com.maxkors.postservice.api.PostResponse;
import com.maxkors.postservice.domain.Post;
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

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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

}
