CREATE TABLE notes (

    id BINARY(16) NOT NULL,

    user_id BINARY(16) NOT NULL,

    title VARCHAR(255) NOT NULL,

    content TEXT,

    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    created_at DATETIME NOT NULL,

    updated_at DATETIME NOT NULL,

    PRIMARY KEY (id),

    INDEX idx_notes_user_id (user_id)
);