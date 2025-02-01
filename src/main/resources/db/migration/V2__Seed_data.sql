-- Insert admin user
INSERT INTO "user" (username, email, password, role, is_active)
VALUES
    ('admin', 'admin@admin.com', '$2b$12$LSag3NgIVVVuEl6ikSzVIev24F75IaswU7BCmShsDcH9sDaeVQqaW', 'ADMIN', true);

-- Insert sample publishers
INSERT INTO publisher (publisher_id, name, address, website, contact_number)
VALUES
    (1, 'Scribner', NULL, NULL, NULL),
    (2, 'J.B. Lippincott & Co.', NULL, NULL, NULL),
    (3, 'Secker & Warburg', NULL, NULL, NULL),
    (4, 'Little, Brown and Company', NULL, NULL, NULL),
    (5, 'T. Egerton, Whitehall', NULL, NULL, NULL),
    (6, 'George Allen & Unwin', NULL, NULL, NULL),
    (7, 'Bloomsbury', NULL, NULL, NULL),
    (8, 'Harper & Brothers', NULL, NULL, NULL),
    (9, 'The Russian Messenger', NULL, NULL, NULL),
    (10, 'HarperOne', NULL, NULL, NULL);

-- Insert sample authors
INSERT INTO author (author_id, first_name, last_name, nationality, biography)
VALUES
    (1, 'F. Scott', 'Fitzgerald', 'American', NULL),
    (2, 'Harper', 'Lee', 'American', NULL),
    (3, 'George', 'Orwell', 'British', NULL),
    (4, 'J.D.', 'Salinger', 'American', NULL),
    (5, 'Jane', 'Austen', 'British', NULL),
    (6, 'J.R.R.', 'Tolkien', 'British', NULL),
    (7, 'J.K.', 'Rowling', 'British', NULL),
    (8, 'Herman', 'Melville', 'American', NULL),
    (9, 'Leo', 'Tolstoy', 'Russian', NULL),
    (10, 'Paulo', 'Coelho', 'Brazilian', NULL);

-- Insert sample categories
INSERT INTO category (category_id, name, description)
VALUES
    (1, 'Fiction', NULL),
    (2, 'Classic', NULL),
    (3, 'Dystopian', NULL),
    (4, 'Young Adult', NULL),
    (5, 'Romance', NULL),
    (6, 'Fantasy', NULL),
    (7, 'Adventure', NULL),
    (8, 'Historical', NULL),
    (9, 'Philosophical', NULL);

-- Insert sample books
INSERT INTO book (book_id, title, genre, publish_year, isbn, language, page_count, description, publisher_id)
VALUES
    (1, 'The Great Gatsby', 'Classic', 1925, '9780743273565', 'English', 180, 'A novel set in the Jazz Age...', 1),
    (2, 'To Kill a Mockingbird', 'Fiction', 1960, '9780061120084', 'English', 324, 'A story of racial injustice...', 2),
    (3, '1984', 'Dystopian', 1949, '9780451524935', 'English', 328, 'A chilling portrayal of a totalitarian society...', 3),
    (4, 'The Catcher in the Rye', 'Fiction', 1951, '9780316769488', 'English', 214, 'The struggles of a teenage boy...', 4),
    (5, 'Pride and Prejudice', 'Romance', 1813, '9781503290563', 'English', 279, 'A romantic tale of love...', 5),
    (6, 'The Hobbit', 'Fantasy', 1937, '9780547928227', 'English', 310, 'The prelude to the Lord of the Rings trilogy...', 6),
    (7, 'Harry Potter and the Sorcerer''s Stone', 'Fantasy', 1997, '9780590353427', 'English', 309, 'The beginning of Harry Potter''s journey...', 7),
    (8, 'Moby Dick', 'Adventure', 1851, '9781503280786', 'English', 635, 'The tale of Captain Ahab''s obsessive quest...', 8),
    (9, 'War and Peace', 'Historical', 1869, '9781400079988', 'English', 1225, 'A sprawling epic of Russian society...', 9),
    (10, 'The Alchemist', 'Philosophical', 1988, '9780061122415', 'English', 208, 'A story about following one''s dreams...', 10);

-- Link books to authors
INSERT INTO book_author (book_id, author_id) VALUES
                                                 (1, 1),
                                                 (2, 2),
                                                 (3, 3),
                                                 (4, 4),
                                                 (5, 5),
                                                 (6, 6),
                                                 (7, 7),
                                                 (8, 8),
                                                 (9, 9),
                                                 (10, 10);

-- Link books to categories
INSERT INTO book_category (book_id, category_id) VALUES
                                                     (1, 1), (1, 2),
                                                     (2, 1), (2, 2),
                                                     (3, 1), (3, 3),
                                                     (4, 1), (4, 4),
                                                     (5, 1), (5, 5),
                                                     (6, 1), (6, 6),
                                                     (7, 1), (7, 6), (7, 4),
                                                     (8, 1), (8, 7),
                                                     (9, 1), (9, 8),
                                                     (10, 1), (10, 9);