INSERT INTO author (first_name, last_name, biography, birth_date)
VALUES ('Leo', 'Tolstoy', 'Russian writer, author of War and Peace', '1828-09-09'),
       ('Fyodor', 'Dostoevsky', 'Russian novelist and philosopher', '1821-11-11'),
       ('Jane', 'Austen', 'English novelist known for Pride and Prejudice', '1775-12-16'),
       ('Mark', 'Twain', 'American writer, humorist, entrepreneur', '1835-11-30'),
       ('Charles', 'Dickens', 'English writer and social critic', '1812-02-07'),
       ('J.K.', 'Rowling', 'English author of Harry Potter series', '1965-07-31'),
       ('George', 'Orwell', 'English novelist, essayist, journalist', '1903-06-25'),
       ('H.G.', 'Wells', 'English writer, father of science fiction', '1866-09-21');

INSERT INTO genre (name, description)
VALUES ('Novel', 'Long narrative fiction'),
       ('Science Fiction', 'Fiction based on futuristic concepts'),
       ('Fantasy', 'Fiction with magical elements'),
       ('Classic', 'Recognized as culturally significant literature'),
       ('Adventure', 'Exciting or unusual experiences');

INSERT INTO book (isbn, title, description, genre_id, author_id, published_year, page_count, language, cover_url,
                  is_deleted, created_at, updated_at)
VALUES ('9780140447934', 'War and Peace', 'Epic novel by Leo Tolstoy', 1, 1, 1869, 1225, 'EN', NULL, FALSE, now(),
        now()),
       ('9780140449242', 'Crime and Punishment', 'Novel by Fyodor Dostoevsky', 1, 2, 1866, 671, 'EN', NULL, FALSE,
        now(), now()),
       ('9780141439518', 'Pride and Prejudice', 'Novel by Jane Austen', 1, 3, 1813, 432, 'EN', NULL, FALSE, now(),
        now()),
       ('9780486280615', 'Adventures of Huckleberry Finn', 'Novel by Mark Twain', 5, 4, 1884, 366, 'EN', NULL, FALSE,
        now(), now()),
       ('9780141439600', 'Great Expectations', 'Novel by Charles Dickens', 4, 5, 1861, 505, 'EN', NULL, FALSE, now(),
        now()),
       ('9780747532743', 'Harry Potter and the Philosopher''s Stone', 'Fantasy novel by J.K. Rowling', 3, 6, 1997, 223,
        'EN', NULL, FALSE, now(), now()),
       ('9780451524935', '1984', 'Dystopian novel by George Orwell', 2, 7, 1949, 328, 'EN', NULL, FALSE, now(), now()),
       ('9780451530707', 'The Time Machine', 'Science fiction novel by H.G. Wells', 2, 8, 1895, 118, 'EN', NULL, FALSE,
        now(), now()),
       ('9780439064873', 'Harry Potter and the Chamber of Secrets', 'Fantasy novel by J.K. Rowling', 3, 6, 1998, 251,
        'EN', NULL, FALSE, now(), now()),
       ('9780439136365', 'Harry Potter and the Prisoner of Azkaban', 'Fantasy novel by J.K. Rowling', 3, 6, 1999, 317,
        'EN', NULL, FALSE, now(), now());
