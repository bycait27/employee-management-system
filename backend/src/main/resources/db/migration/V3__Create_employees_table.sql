CREATE SEQUENCE employees_seq START 1 INCREMENT 1;

CREATE TABLE employees (
    id BIGINT PRIMARY KEY DEFAULT nextval('employees_seq'),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    position VARCHAR(255) NOT NULL,
    employee_number VARCHAR(20) NOT NULL UNIQUE,
    salary DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    hired_date DATE NOT NULL,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    department_id BIGINT NOT NULL REFERENCES departments(id),
    user_id BIGINT NOT NULL REFERENCES users(id)
);