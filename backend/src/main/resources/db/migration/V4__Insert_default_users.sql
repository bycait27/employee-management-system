-- migration to add default users with BCrypt hashed passwords
-- default password for all users is "password123" (hashed with BCrypt)

-- insert default admin user if not exists
INSERT INTO users (username, email, password, role, created_date) 
SELECT 'admin', 'admin@company.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'ADMIN', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

-- insert default HR user if not exists  
INSERT INTO users (username, email, password, role, created_date)
SELECT 'hr', 'hr@company.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'HR', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'hr');

-- insert default employee user if not exists
INSERT INTO users (username, email, password, role, created_date)
SELECT 'employee', 'employee@company.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'EMPLOYEE', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'employee');
