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