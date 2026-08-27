ALTER TABLE notes
    DROP INDEX idx_notes_user_id,
    DROP COLUMN user_id,
    ADD COLUMN user_email VARCHAR(255) NOT NULL AFTER id,
    ADD INDEX idx_notes_user_email (user_email);
    
ALTER TABLE notes
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;