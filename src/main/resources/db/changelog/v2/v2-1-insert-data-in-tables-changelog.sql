INSERT INTO author (first_name, middle_name, last_name)
VALUES ('Jane', '', 'Austen'),
       ('Harper', '', 'Lee'),
       ('Scott', 'Francis', 'Fitzgerald'),
       ('Fyodor', 'Ivanovich', 'Dostoevsky'),
       ('Arkady', 'Natanovich', 'Strugatsky'),
       ('Boris', 'Natanovich', 'Strugatsky');

INSERT INTO publisher (country, city, email, telephone)
VALUES ('Belarus', 'Minsk', 'Minsk@publisher.bl', '11-22-33'),
       ('Russia', 'Moscow', 'Moscow@publisher.ru', '44-55-66');


INSERT INTO book (title, year, pages, isbn, publisher_id)
VALUES ('Pride and Prejudice', '1813', '100', '978-3-16-148410-0', '1'),
       ('To Kill a Mockingbird', '1960', '150', '123-3-16-148410-7', '2'),
       ('The Great Gatsby', '1925', '200', '198-3-16-148410-5', '1'),
       ('Crime and Punishment', '1866', '400', '111-3-16-148410-9', '1'),
       ('The Idiot', '1869', '250', '654-3-16-148410-5', '2'),
       ('The Brothers Karamazov', '1880', '350', '222-3-16-148410-4', '2'),
       ('Monday Begins on Saturday', '1965', '250', '777-3-16-148410-7', '1');

INSERT INTO book_author (book_id, author_id)
VALUES ('1', '1'),
       ('2', '2'),
       ('3', '3'),
       ('4', '4'),
       ('5', '4'),
       ('6', '4'),
       ('7', '5'),
       ('7', '6');
