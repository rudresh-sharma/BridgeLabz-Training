CREATE TABLE audit_logs (

    id          BIGINT          NOT NULL AUTO_INCREMENT,

    note_id     BIGINT          NOT NULL,

    email       VARCHAR(255)    NOT NULL,

    action      VARCHAR(20)     NOT NULL,

    note_title  VARCHAR(255),

    created_at  DATETIME        NOT NULL,

    PRIMARY KEY (id),

    INDEX idx_audit_note_id  (note_id),
    INDEX idx_audit_email    (email),
    INDEX idx_audit_action   (action)

);
