ALTER TABLE notes
    ADD COLUMN status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';

UPDATE notes
SET status = CASE
    WHEN trashed = TRUE THEN 'TRASHED'
    WHEN archived = TRUE THEN 'ARCHIVED'
    WHEN pinned = TRUE THEN 'PINNED'
    ELSE 'ACTIVE'
END;

ALTER TABLE notes
    DROP COLUMN pinned,
    DROP COLUMN archived,
    DROP COLUMN trashed;