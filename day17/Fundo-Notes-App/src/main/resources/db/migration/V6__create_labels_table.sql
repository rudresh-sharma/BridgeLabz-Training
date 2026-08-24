CREATE TABLE labels (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    
    name VARCHAR(50) NOT NULL,
    
    user_id BIGINT NOT NULL,
    
    CONSTRAINT uk_label_name_user
        UNIQUE (name, user_id),
    
    CONSTRAINT fk_label_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);