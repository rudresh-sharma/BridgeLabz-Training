CREATE TABLE notes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    content TEXT,

    pinned BOOLEAN NOT NULL DEFAULT FALSE,
    archived BOOLEAN NOT NULL DEFAULT FALSE,
    trashed BOOLEAN NOT NULL DEFAULT FALSE,

    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    user_id BIGINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_notes_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);