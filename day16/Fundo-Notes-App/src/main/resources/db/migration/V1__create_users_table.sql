CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,

    name VARCHAR(255) NOT NULL,

    email VARCHAR(255) NOT NULL,

    password VARCHAR(255) NOT NULL,

    provider VARCHAR(50) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_users_email UNIQUE (email)
);