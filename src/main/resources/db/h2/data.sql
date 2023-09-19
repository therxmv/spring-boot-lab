INSERT INTO libraries VALUES (default, 'Cool Library');

INSERT INTO genres (name, libraries_id) VALUES
    ('Fantasy', 1),
    ('Science Fiction', 1),
    ('Mystery', 1),
    ('Romance', 1),
    ('Thriller', 1);

INSERT INTO books (title, genres_id, author, pages) VALUES
    ('Fantasy Book 1', 1, 'Fantasy Author 1', 300),
    ('Fantasy Book 2', 1, 'Fantasy Author 2', 400),
    ('Fantasy Book 3', 1, 'Fantasy Author 3', 350),
    ('Science Fiction Book 1', 2, 'SF Author 1', 320),
    ('Science Fiction Book 2', 2, 'SF Author 2', 280),
    ('Science Fiction Book 3', 2, 'SF Author 3', 410),
    ('Mystery Book 1', 3, 'Mystery Author 1', 250),
    ('Mystery Book 2', 3, 'Mystery Author 2', 320),
    ('Mystery Book 3', 3, 'Mystery Author 3', 290),
    ('Romance Book 1', 4, 'Romance Author 1', 280),
    ('Romance Book 2', 4, 'Romance Author 2', 310),
    ('Romance Book 3', 4, 'Romance Author 3', 270),
    ('Thriller Book 1', 5, 'Thriller Author 1', 340),
    ('Thriller Book 2', 5, 'Thriller Author 2', 380),
    ('Thriller Book 3', 5, 'Thriller Author 3', 310);