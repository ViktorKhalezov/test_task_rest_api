

CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    city VARCHAR(255),
    country VARCHAR(255),
    age INTEGER
);


CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2),
    language VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    author_id BIGINT NOT NULL,

    FOREIGN KEY(author_id) REFERENCES author(id) ON DELETE CASCADE
);
