CREATE TABLE password_reset_tokens (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    token VARCHAR(255) NOT NULL UNIQUE,

    expires_at DATETIME NOT NULL,

    user_id BIGINT NOT NULL UNIQUE,

    CONSTRAINT fk_password_reset_tokens_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);