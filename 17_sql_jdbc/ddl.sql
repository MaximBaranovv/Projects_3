CREATE TABLE author
(
    author_id SERIAL PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL
);

CREATE TABLE publisher
(
    publisher_id SERIAL PRIMARY KEY,
    publisher_name VARCHAR(40) NOT NULL
);

CREATE TABLE book
(
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    publication_date DATE NOT NULL,
    number_of_page INTEGER NOT NULL,
    price INTEGER NOT NULL,
    publisher_id INTEGER NOT NULL, 
    author_id INTEGER,
	FOREIGN KEY (publisher_id) REFERENCES Publisher (publisher_id),
	FOREIGN KEY (author_id) REFERENCES Author (author_id)
);
CREATE TABLE shipping_method
(
    id_method SERIAL PRIMARY KEY,
    method_name VARCHAR(40) NOT NULL,
	cost_of_the_method INTEGER NOT NULL
);
CREATE TABLE customer
(
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL
);
CREATE TABLE customer_address
(
    address_id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
	street_name VARCHAR(40) NOT NULL,
    house_number INTEGER NOT NULL,
	city VARCHAR(40) NOT NULL,
	country VARCHAR(40) NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES Customer (customer_id)
);

CREATE TABLE customer_order(
	order_id SERIAL PRIMARY KEY,
	customer_id INTEGER NOT NULL,
	order_date DATE NOT NULL,
	method_id INTEGER NOT NULL,
	customer_address_id INTEGER NOT NULL,
	book_id INTEGER NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES Customer (customer_id),
	FOREIGN KEY (method_id) REFERENCES Shipping_method (id_method),
	FOREIGN KEY (customer_address_id) REFERENCES Customer_address (address_id),
	FOREIGN KEY (book_id) REFERENCES book (book_id)
);
CREATE TABLE order_history
(
    history_id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    order_date DATE NOT NULL, 
	FOREIGN KEY (order_id) REFERENCES Customer_order (order_id)
);
CREATE TABLE jdbc_role
(
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(40) NOT NULL
);
CREATE TABLE jdbc_user
(
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(40) NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES jdbc_role (role_id)
);

