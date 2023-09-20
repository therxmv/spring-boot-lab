INSERT INTO libraries VALUES (default, 'Cool Library');
INSERT INTO libraries VALUES (default, 'The Best Library');

INSERT INTO genres (name, library_id) VALUES
    ('Fantasy', 2),
    ('Science Fiction', 1),
    ('Mystery', 2),
    ('Romance', 1),
    ('Thriller', 2);

INSERT INTO books (name, author, pages, genre_id) VALUES
    ('Fantasy Book 1', 'Fantasy Author 1', 300, 1),
    ('Fantasy Book 2', 'Fantasy Author 2', 400, 1),
    ('Fantasy Book 3', 'Fantasy Author 3', 350, 1),
    ('Science Fiction Book 1', 'SF Author 1', 320, 2),
    ('Science Fiction Book 2', 'SF Author 2', 280, 2),
    ('Science Fiction Book 3', 'SF Author 3', 410, 2),
    ('Mystery Book 1', 'Mystery Author 1', 250, 3),
    ('Mystery Book 2', 'Mystery Author 2', 320, 3),
    ('Mystery Book 3', 'Mystery Author 3', 290, 3),
    ('Romance Book 1', 'Romance Author 1', 280, 4),
    ('Romance Book 2', 'Romance Author 2', 310, 4),
    ('Romance Book 3', 'Romance Author 3', 270, 4),
    ('Thriller Book 1', 'Thriller Author 1', 340, 5),
    ('Thriller Book 2', 'Thriller Author 2', 380, 5),
    ('Thriller Book 3', 'Thriller Author 3', 310, 5);