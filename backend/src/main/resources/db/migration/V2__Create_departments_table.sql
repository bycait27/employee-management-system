CREATE SEQUENCE departments_seq START 1 INCREMENT 1;

CREATE TABLE departments (
    id BIGINT PRIMARY KEY DEFAULT nextval('departments_seq'),
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(500),
    location VARCHAR(255) NOT NULL,
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);