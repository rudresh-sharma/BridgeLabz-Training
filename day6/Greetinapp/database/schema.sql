-- =============================================================
-- GREETINGAPP DATABASE SETUP SCRIPT — TiDB Cloud
-- Database: student_mvc  (already created in TiDB Cloud cluster)
-- Run this in TiDB Cloud SQL Editor or via MySQL CLI:
--   mysql -h gateway01.ap-southeast-1.prod.aws.tidbcloud.com \
--         -P 4000 -u 29MZ68waXLv7AT4.root -p \
--         --ssl-ca=C:/certs/isrgrootx1.pem \
--         student_mvc < database/schema.sql
-- =============================================================

-- Switch to the student_mvc database (already exists on TiDB Cloud)
USE student_mvc;

-- Step 3: Create the users table
-- 
-- id       : auto-incrementing primary key (BIGINT for scalability)
-- name     : user's display name (VARCHAR 100)
-- email    : unique email used for login
-- password : stores the plain password (in production, use BCrypt hashing)
--
CREATE TABLE IF NOT EXISTS users (
    id       BIGINT        AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100)  NOT NULL,
    email    VARCHAR(100)  NOT NULL UNIQUE,
    password VARCHAR(255)  NOT NULL,
    CONSTRAINT chk_email CHECK (email LIKE '%@%.%')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Step 4: (Optional) Insert a test user to verify the table
--         password = 'password123'
INSERT INTO users (name, email, password)
VALUES ('Rudresh', 'rudresh@example.com', 'password123')
ON DUPLICATE KEY UPDATE name = name; -- Safe to re-run

-- Step 5: Verify
SELECT * FROM users;
