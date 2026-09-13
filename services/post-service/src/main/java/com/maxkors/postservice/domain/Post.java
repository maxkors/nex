package com.maxkors.postservice.domain;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq_gen")
    @SequenceGenerator(name = "post_seq_gen", sequenceName = "posts_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "like_count", nullable = false)
    private Long like_count;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Post(Long authorId, String text) {
        this.authorId = authorId;
        this.text = text;
        this.like_count = 0L;
        this.createdAt = Instant.now();
    }
}
