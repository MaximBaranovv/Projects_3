SELECT * FROM customer_address;

SELECT * FROM book;

SELECT postal_code FROM customer_address;

SELECT publication_date FROM book;

SELECT publication_date, number_of_page FROM book;

--
-- Retrieve and join first_names and last_names and retrieve emails of the customers
--
SELECT  first_name || ' ' || last_name, email
FROM  customer;
   
--
-- Retrieve not duplicate number_of_page from the books and sort by it  
--
SELECT DISTINCT order_id 
FROM order_history 
ORDER BY order_id;

SELECT DISTINCT publisher_id
FROM book;

SELECT DISTINCT number_of_page 
FROM book;
	
SELECT *
FROM book
WHERE price > 500;

SELECT *
FROM book
WHERE price < 300;

SELECT *
FROM customer_address
WHERE house_number != 2;

--
-- Retrieve all books, with titles: 'Book1', 'Book5', 'Book6'
--
SELECT *
FROM book
WHERE title IN ('Book1', 'Book5', 'Book6');

--
-- Retrieve all books, which number_of_page is 2100, then 100, then 
-- 200
--
SELECT *
FROM book
WHERE number_of_page = 2100
OR number_of_page = 100
OR number_of_page = 200;

SELECT *
FROM customer
WHERE first_name IS NOT NULL;

--
-- Retrieve all customers, whose first_name start from 'Va'
--
SELECT *
FROM customer
WHERE first_name LIKE 'Va%';

SELECT * FROM book WHERE price BETWEEN 100 AND 700;

--
-- Retrieve all book which price start from number 10 and cast the 
-- price to TEXT
--
SELECT * FROM book WHERE CAST(price AS TEXT) LIKE '10%';

--
-- Retrieve all books which price is bigger then the average price of all the books
--
SELECT *
FROM book
WHERE price > (
    SELECT AVG(price)
    FROM book);

--
-- Retrieve and count the average price of only those books, whose author's first_name is 'Maks' or 'Petya'
--
SELECT AVG(number_of_page)
FROM book
WHERE author_id IN (
    SELECT author_id
    FROM author
    WHERE first_name = 'Maks' OR first_name = 'Petya');

--
-- Retrieve all of the customer_order, customers of whom have the biggest number_of_house in their address  
--
SELECT *
FROM customer_order
WHERE customer_address_id = (
    SELECT customer_id
    FROM customer_address
    WHERE house_number = (
        SELECT MAX(house_number)
        FROM customer_address));

SELECT street_name
FROM customer_address
WHERE city = 'Khm'
ORDER BY customer_id;

--
-- Retrieve titles of the books, first_names of the authors and join them into one table 
--
SELECT title FROM book
UNION 
SELECT first_name FROM author;

--
-- Retrieve titles of the books, method_names from the shipping_method table and join them into one table 
--
SELECT title FROM book WHERE price > 500
UNION 
SELECT method_name FROM shipping_method;

--
-- Retrieve and join titles of the books and first_names of authors in one table
--
SELECT b.title, au.first_name 
FROM book AS b
JOIN author AS au ON b.author_id = au.author_id; 

--
-- Retrieve first_names of the customers, their cities from the customer_address table and order_date of when this customers bought a book and join it into one table 
--
SELECT ct.first_name, ca.city, co.order_date
FROM customer AS ct
JOIN customer_address AS ca 
ON ct.customer_id = ca.customer_id
JOIN customer_order AS co
ON co.customer_id = ca.customer_id; 

--
-- Retrieve titles of those the books from one table and join if with first_name of the second table, using left join. Joining is processing by the id of both tables, so we need to keep in mind that there may be some mismatches between them or null ids  
--
SELECT b.title, au.first_name 
FROM book AS b
LEFT JOIN author AS au ON b.author_id = au.author_id; 

--
-- Retrieve and join with the first_names of authors from author table only those books, ids of authors whose are NULL 
--
SELECT b.title, au.first_name 
FROM book AS b
LEFT JOIN author AS au ON b.author_id = au.author_id
WHERE b.author_id IS NULL;

SELECT title FROM book WHERE price > 10 * 10;

--
-- Retrieve publication_date from those books, number of pages is more than 500. Using numeric ABS() function, which returns positive number 
--
SELECT publication_date FROM book WHERE number_of_page > ABS(-500);

--
-- Retrieve titles of the books, whose price is more than 300. Using numeric function FLOOR(), which returns the largest integer value that is not greater than passed numeric expression. 
--
SELECT title FROM book WHERE price > FLOOR(300.55);

--
-- Retrieve publisher_id of those puublishers, name of whose is 'Ranok', which was concatenated with the CONCAT() function.
--
SELECT publisher_id FROM publisher WHERE publisher_name = CONCAT('Ra', 'no', 'k'); 

SELECT city FROM customer_address WHERE street_name = REVERSE('ydeboP');

--
-- Retrieve number of rows in the book table
--
SELECT COUNT(*) FROM book;

SELECT MAX(price) FROM book;

--
-- Retrieve cities, which starts on 'K' and get count of each
--
SELECT number_of_page, COUNT(number_of_page) AS "number_of_pages"
FROM book
GROUP BY number_of_page
HAVING number_of_page > 300;