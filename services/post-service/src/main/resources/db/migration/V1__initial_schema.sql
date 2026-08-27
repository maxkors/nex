CREATE TABLE
    posts (
        id BIGSERIAL PRIMARY KEY,
        author_id BIGINT NOT NULL,
        text TEXT NOT NULL,
        likes BIGINT NULL,
        created_at TIMESTAMPTZ NOT NULL
    );

INSERT INTO
    posts (author_id, text, likes, created_at)
VALUES
    (1, 'Hello, world!', 0, '2026-01-01 12:00:00'),
    (2, 'Test post.', 10, '2026-01-02 13:00:00'),
    (3, 'Another test post.', 5, '2026-01-03 14:00:00');