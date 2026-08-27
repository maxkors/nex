package com.maxkors.postservice.infrastructure;

import com.maxkors.postservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthorId(Long authorId);

    List<Post> findAllByTextContaining(String text);
}
