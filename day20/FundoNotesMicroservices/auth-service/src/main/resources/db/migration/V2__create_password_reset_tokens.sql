CREATE TABLE password_reset_tokens (

    id BINARY(16) NOT NULL,

    token VARCHAR(255) NOT NULL,

    user_id BINARY(16) NOT NULL,

    expires_at DATETIME NOT NULL,

    PRIMARY KEY (id),

    UNIQUE INDEX uq_password_reset_token (token),

    INDEX idx_password_reset_user_id (user_id)

);
