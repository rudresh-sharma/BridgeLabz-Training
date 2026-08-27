CREATE TABLE reminders (

    id              BIGINT          NOT NULL AUTO_INCREMENT,

    note_id         BIGINT          NOT NULL,

    email           VARCHAR(255)    NOT NULL,

    reminder_time   DATETIME        NOT NULL,

    notified        BOOLEAN         NOT NULL DEFAULT false,

    created_at      DATETIME        NOT NULL,

    PRIMARY KEY (id),

    UNIQUE INDEX uq_reminder_note_email (note_id, email),

    INDEX idx_reminder_email          (email),
    INDEX idx_reminder_notified_time  (notified, reminder_time)

);
