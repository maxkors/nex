package com.maxkors.postservice.application;

import com.maxkors.postservice.api.PostDTO;
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

    public List<PostDTO> getAll() {
        return postRepository.findAll().stream().map(PostDTO::from).collect(Collectors.toList());
    }

    public List<PostDTO> getAllByAuthorId(Long authorId) {
        return postRepository.findAllByAuthorId(authorId).stream().map(PostDTO::from).collect(Collectors.toList());
    }

    public Optional<PostDTO> getById(Long id) {
        return postRepository.findById(id).map(PostDTO::from);
    }

    public List<PostDTO> search(String text) {
        return postRepository.findAllByTextContaining(text).stream().map(PostDTO::from).collect(Collectors.toList());
    }

    @Transactional
    public PostDTO create(PostDTO postDTO) {
        Post post = new Post(postDTO.authorId(), postDTO.text());
        Post savedPost = postRepository.save(post);
        return PostDTO.from(savedPost);
    }

    @Transactional
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        postRepository.delete(post);
    }

    @Transactional
    public PostDTO update(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        post.setText(postDTO.text());
        return PostDTO.from(post);
    }
}
