-- clean and recreate default users with proper BCrypt passwords
-- first delete any existing default users, then recreate them

DELETE FROM users WHERE username IN ('admin', 'hr', 'employee');

-- insert default users with BCrypt hash for "password123"  
INSERT INTO users (username, email, password, role, created_date) VALUES
('admin', 'admin@company.com', '$2a$10$vB6hSj44tuvlW3NNex0NIuoC8thViA0R4sohCc1p7avG1tCd.x9p2', 'ADMIN', CURRENT_TIMESTAMP),
('hr', 'hr@company.com', '$2a$10$vB6hSj44tuvlW3NNex0NIuoC8thViA0R4sohCc1p7avG1tCd.x9p2', 'HR', CURRENT_TIMESTAMP),
('employee', 'employee@company.com', '$2a$10$vB6hSj44tuvlW3NNex0NIuoC8thViA0R4sohCc1p7avG1tCd.x9p2', 'EMPLOYEE', CURRENT_TIMESTAMP);
