CREATE TABLE reminders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    reminder_time DATETIME NOT NULL,

    notified BOOLEAN NOT NULL DEFAULT FALSE,

    note_id BIGINT NOT NULL,

    user_id BIGINT NOT NULL,

    CONSTRAINT uk_reminder_note_user
        UNIQUE (note_id, user_id),

    CONSTRAINT fk_reminder_note
        FOREIGN KEY (note_id)
        REFERENCES notes(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_reminder_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);