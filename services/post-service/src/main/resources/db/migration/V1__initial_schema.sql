CREATE TABLE posts
(
    id        BIGSERIAL PRIMARY KEY,
    author_id BIGINT NOT NULL,
    text      TEXT   NOT NULL
);

INSERT INTO posts (author_id, text)
VALUES (1, 'Hello World!'),
       (2, 'This is a test post.'),
       (3, 'Another post for testing.');