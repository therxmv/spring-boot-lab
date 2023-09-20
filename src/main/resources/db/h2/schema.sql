DROP TABLE libraries IF EXISTS;
DROP TABLE genres IF EXISTS;
DROP TABLE books IF EXISTS;

CREATE TABLE libraries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE genres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    library_id INT
);

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    author VARCHAR(255),
    pages INT,
    genre_id INT
);