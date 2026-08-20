ALTER TABLE users
    ADD COLUMN failed_attempts INT NOT NULL DEFAULT 0,
    ADD COLUMN account_locked_until DATETIME NULL;