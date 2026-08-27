CREATE TABLE labels (

    id BIGINT NOT NULL AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE note_labels (

    note_id BIGINT NOT NULL,

    label_id BIGINT NOT NULL,

    PRIMARY KEY (note_id, label_id),

    CONSTRAINT fk_note_labels_note
        FOREIGN KEY (note_id)
        REFERENCES notes(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_note_labels_label
        FOREIGN KEY (label_id)
        REFERENCES labels(id)
        ON DELETE CASCADE
);