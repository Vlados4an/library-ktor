CREATE TABLE author
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    biography  TEXT,
    birth_date DATE
);

CREATE TABLE genre
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE book
(
    id             SERIAL PRIMARY KEY,
    isbn           VARCHAR(50)  NOT NULL,
    title          VARCHAR(255) NOT NULL,
    description    TEXT,
    genre_id       INT          NOT NULL,
    author_id      INT          NOT NULL,
    published_year INT,
    page_count     INT,
    language       VARCHAR(50)  NOT NULL,
    cover_url      VARCHAR(500),
    is_deleted     BOOLEAN DEFAULT FALSE,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
);

ALTER TABLE book
    ADD CONSTRAINT fk_book_genre FOREIGN KEY (genre_id) REFERENCES genre (id);

ALTER TABLE book
    ADD CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES author (id);

