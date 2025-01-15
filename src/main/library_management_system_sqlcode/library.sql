
CREATE DATABASE IF EXISTS library_project;

CREATE TABLE member(
    id VARCHAR(6) PRIMARY KEY,
    name VARCHAR(45),
    address TEXT,
    email VARCHAR(255),
    contact VARCHAR(12)
);

CREATE TABLE publisher(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    location TEXT,
    contact VARCHAR(12)
);

CREATE TABLE author(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    contact VARCHAR(12)
);

CREATE TABLE category(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25)
);

CREATE TABLE book(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    isbn VARCHAR(13),
    price DECIMAL(10,2),
    publisher_id INT,
    main_category_id INT,

    FOREIGN KEY(publisher_id) REFERENCES publisher(id),
    FOREIGN KEY(main_category_id) REFERENCES category(id)
);

CREATE TABLE book_author(
    book_id INT,
    author_id INT,

        PRIMARY KEY(book_id,author_id),
        FOREIGN KEY(book_id) REFERENCES book(id) ON DELETE CASCADE,
        FOREIGN KEY(author_id) REFERENCES author(id) ON DELETE CASCADE
);

CREATE TABLE sub_categories(
    book_id INT,
    category_id INT,

    PRIMARY KEY(book_id,category_id),
    FOREIGN KEY(book_id) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY(category_id) REFERENCES category(id) ON DELETE CASCADE
);

CREATE TABLE book_records(
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    member_id VARCHAR(6),
    borrowed_date DATE,
    isReturned TINYINT(1),
    returnDate DATE,
    returnedDate DATE,

    FOREIGN KEY(book_id) REFERENCES book(id),
    FOREIGN KEY(member_id) REFERENCES member(id)
);

CREATE TABLE admin(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(10),
    email VARCHAR( 45),
    password VARCHAR(25)
);

CREATE TABLE fine(
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(10,2),
    date_count SMALLINT,
    book_record_id INT,
    admin_id INT,

    FOREIGN KEY(book_record_id) REFERENCES book_records(id),
    FOREIGN KEY(admin_id) REFERENCES admin(id)
);