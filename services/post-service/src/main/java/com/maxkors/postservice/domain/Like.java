package com.maxkors.postservice.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_seq_gen")
    @SequenceGenerator(name = "like_seq_gen", sequenceName = "likes_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Like(Long postId, Long userId){
        this.postId = postId;
        this.userId = userId;
        this.createdAt = Instant.now();
    }
}
