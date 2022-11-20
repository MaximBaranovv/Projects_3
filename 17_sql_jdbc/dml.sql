INSERT INTO author (author_id, first_name, last_name) VALUES (80, 'Maks', 'Bar');
INSERT INTO author (author_id, first_name, last_name) VALUES (12, 'Tanya', 'Ksl');
INSERT INTO author (author_id, first_name, last_name) VALUES (8318391, 'Petya', 'DWA');
INSERT INTO author (author_id, first_name, last_name) VALUES (31831391, 'Vasya', 'Pop');
INSERT INTO author (author_id, first_name, last_name) VALUES (82, 'Vasya', 'Ui');
INSERT INTO author (author_id, first_name, last_name) VALUES (111, 'Lokya', 'WJD');
INSERT INTO author (author_id, first_name, last_name) VALUES (200, 'Anna', 'ASD');


INSERT INTO publisher (publisher_id, publisher_name) VALUES (2929,'Ranok');
INSERT INTO publisher (publisher_id, publisher_name) VALUES (31,'Veselka');
INSERT INTO publisher (publisher_id, publisher_name) VALUES (1311,'Dvor');
INSERT INTO publisher (publisher_id, publisher_name) VALUES (11231,'Reka');
INSERT INTO publisher (publisher_id, publisher_name) VALUES (218381,'Sun');


INSERT INTO book (title, publication_date, number_of_page, price, publisher_id, author_id) VALUES ('Book1', '01-02-2000', 900,100, 2929, 80);
INSERT INTO book (title, publication_date, number_of_page,price, publisher_id, author_id) VALUES ('Book2', '01-01-2000', 100, 1000,31, 12);
INSERT INTO book (title, publication_date, number_of_page,price, publisher_id, author_id) VALUES ('Book3', '01-01-2002', 2100, 300,1311, 8318391);
INSERT INTO book (title, publication_date, number_of_page,price, publisher_id, author_id) VALUES ('Book4', '01-04-1999', 500, 700,31, 31831391);
INSERT INTO book (title, publication_date, number_of_page,price, publisher_id, author_id) VALUES ('Book5', '01-05-1879', 500, 500,1311, 200);
INSERT INTO book (title, publication_date, number_of_page, price,publisher_id) VALUES ('Book6', '01-03-1885', 200, 900,31);
INSERT INTO book (title, publication_date, number_of_page,price, publisher_id, author_id) VALUES ('Book7', '01-03-1930', 100, 200,1311, 200);
INSERT INTO book (title, publication_date, number_of_page,price, publisher_id) VALUES ('Book8', '01-03-1984', 700, 500,2929 );


INSERT INTO customer (first_name, last_name, email) VALUES ('Max', 'Bar', 'max@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('John', 'Snow', 'john@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Tanya', 'Rab', 'tanya@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Vaya', 'Gur', 'vasya@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Vasya', 'Mur', 'vasya@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Petya', 'Pur', 'petya@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Katya', 'Sur', 'petya@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Dima', 'Jok', 'dima@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Olya', 'Kok', 'olya@gmail.com');
INSERT INTO customer (first_name, last_name, email) VALUES ('Lilya', 'Lop', 'lilya@gmail.com');

INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (1, 'Svobody', 2, 'Khm', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (2, 'Pobedy', 3, 'Odessa', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (3, 'Pidpillya', 2, 'Khm', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (4, 'Street1', 2, 'Kyiv', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (5, 'Street2', 2, 'Kharkiv', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (6, 'Street3', 3, 'Nikolaiv', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (7, 'Street4', 4, 'Khm', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (8, 'Street5', 5, 'Khm', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (9, 'Street6', 2, 'Khm', 'Ukraine');
INSERT INTO customer_address (customer_id, street_name, house_number, city, country) VALUES (10, 'Street7', 1, 'Khm', 'Ukraine');

INSERT INTO shipping_method (method_name, cost_of_the_method) VALUES ('First method', 40);
INSERT INTO shipping_method (method_name, cost_of_the_method) VALUES ('Second method', 200);
INSERT INTO shipping_method (method_name, cost_of_the_method) VALUES ('Third method', 4000);

INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (1, '05-21-2000', 1, 1, 1);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (2, '05-22-2000', 2, 2, 2);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (3, '05-24-2000',3, 3, 3);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (4, '05-25-2000',2, 4, 4);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (3, '05-26-2000',2, 3, 5);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (7, '05-27-2000',1, 7, 6);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (8, '05-28-2000',1, 8, 7);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (3, '05-29-2000',2, 3, 8);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (9, '05-20-2000',2, 9, 4);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (3, '05-19-2000',1, 3, 3);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (3, '05-18-2000',1, 3, 5);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (10, '05-17-2000',2, 10, 3);
INSERT INTO customer_order (customer_id, order_date, method_id , customer_address_id, book_id) VALUES (3, '05-16-2000',2, 3, 1);

INSERT INTO order_history (order_id, order_date) VALUES (1, '05-23-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (2, '05-22-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (3, '05-24-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (4, '05-25-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (5, '05-26-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (6, '05-27-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (7, '05-28-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (8, '05-29-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (9, '05-20-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (10, '05-19-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (11, '05-18-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (12, '05-17-2000');
INSERT INTO order_history (order_id, order_date ) VALUES (13, '05-16-2000');

INSERT INTO jdbc_role (role_name) VALUES ('admin');
INSERT INTO jdbc_role (role_name) VALUES ('client');

INSERT INTO jdbc_user (user_name, role_id) VALUES ('Maks', 1);
INSERT INTO jdbc_user (user_name, role_id) VALUES ('Dima', 2);





UPDATE shipping_method SET method_name = 'by plane' WHERE id_method = 1;

UPDATE publisher SET publisher_name = 'Pluton' WHERE publisher_id = 31;

UPDATE customer SET first_name = 'Bob' WHERE customer_id = 3;

ALTER TABLE customer_address ADD postal_code INTEGER;

UPDATE customer_address set postal_code = 131313 where customer_id = 1;
UPDATE customer_address set postal_code = 81230 where customer_id = 2;
UPDATE customer_address set postal_code = 132121 where customer_id = 3;
UPDATE customer_address set postal_code = 213131 where customer_id = 4;
UPDATE customer_address set postal_code = 12123 where customer_id = 5;
UPDATE customer_address set postal_code = 13123 where customer_id = 6;
UPDATE customer_address set postal_code = 12312 where customer_id = 7;
UPDATE customer_address set postal_code = 132131 where customer_id = 8;
UPDATE customer_address set postal_code = 1313123 where customer_id = 9;
UPDATE customer_address set postal_code = 1313123 where customer_id = 10;

