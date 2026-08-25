CREATE TABLE refresh_tokens (
    id BINARY(16) NOT NULL,

    token VARCHAR(512) NOT NULL,

    user_id BINARY(16) NOT NULL,

    expiry_date DATETIME NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),

    CONSTRAINT uk_refresh_token UNIQUE (token)
);