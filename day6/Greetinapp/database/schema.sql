-- =============================================================
-- GREETINGAPP DATABASE SETUP
-- =============================================================

-- Docker creates this database:
-- greeting_app_db

USE greeting_app_db;


-- =============================================================
-- USERS TABLE
-- =============================================================

CREATE TABLE IF NOT EXISTS users (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,

    CONSTRAINT chk_email
        CHECK (email LIKE '%@%.%')
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;


-- =============================================================
-- TEST USER
-- =============================================================

INSERT INTO users (
    name,
    email,
    password
)
VALUES (
    'Rudresh',
    'rudresh@example.com',
    'password123'
)
ON DUPLICATE KEY UPDATE
    name = name;


-- =============================================================
-- VERIFY
-- =============================================================

SELECT * FROM users;