CREATE TABLE refresh_tokens (

    id BINARY(16) NOT NULL,

    token VARCHAR(512) NOT NULL,

    user_id BINARY(16) NOT NULL,

    expiry_date DATETIME NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT false,

    PRIMARY KEY (id),

    UNIQUE INDEX uq_refresh_token (token),

    INDEX idx_refresh_token_user_id (user_id)

);
