-- update default users with correct BCrypt hash for "password123"
UPDATE users SET password = '$2a$10$vB6hSj44tuvlW3NNex0NIuoC8thViA0R4sohCc1p7avG1tCd.x9p2' 
WHERE username IN ('admin', 'hr', 'employee');
