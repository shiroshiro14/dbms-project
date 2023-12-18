DROP TABLE IF exists book
CREATE TABLE book (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    publish_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(10,2),
    rating DECIMAL(1,1)
    CONSTRAINT chk_rating CHECK (rating >=0 AND rating <=5)
    author_id INT
);

DROP TABLE IF exists author
CREATE TABLE author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

DROP TABLE IF exists stock
CREATE TABLE stock (
    id INT PRIMARY KEY,
    instock BOOLEAN,
    lastUpdate DATETIME DEFAULT CURRENT_TIMESTAMP
)

