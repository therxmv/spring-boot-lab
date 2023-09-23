INSERT INTO libraries VALUES (default, 'Cool Library');
INSERT INTO libraries VALUES (default, 'The Best Library');

INSERT INTO genres (name, library_id) VALUES
    ('Fantasy', 2),
    ('Science Fiction', 1),
    ('Mystery', 2),
    ('Romance', 1),
    ('Thriller', 2);

INSERT INTO books (name, author, pages, genre_id) VALUES
    ('The Hobbit', 'J.R.R. Tolkien', 310, 1),
    ('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 320, 1),
    ('A Game of Thrones', 'George R.R. Martin', 694, 1),
    ('Dune', 'Frank Herbert', 412, 2),
    ('Neuromancer', 'William Gibson', 271, 2),
    ('Snow Crash', 'Neal Stephenson', 470, 2),
    ('Sherlock Holmes: The Hound of the Baskervilles', 'Arthur Conan Doyle', 190, 3),
    ('Gone Girl', 'Gillian Flynn', 432, 5),
    ('The Girl on the Train', 'Paula Hawkins', 336, 5),
    ('Pride and Prejudice', 'Jane Austen', 432, 4),
    ('To Kill a Mockingbird', 'Harper Lee', 281, 3),
    ('The Notebook', 'Nicholas Sparks', 214, 4),
    ('The Da Vinci Code', 'Dan Brown', 489, 5),
    ('The Silent Patient', 'Alex Michaelides', 336, 3),
    ('The Girl with the Dragon Tattoo', 'Stieg Larsson', 480, 5);