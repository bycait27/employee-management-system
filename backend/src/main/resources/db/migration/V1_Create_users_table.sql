CREATE SEQUENCE users_seq START 1 INCREMENT 1;

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT nextval('users_seq'),
    username VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_date TIMESTAMP,
    last_login TIMESTAMP
);