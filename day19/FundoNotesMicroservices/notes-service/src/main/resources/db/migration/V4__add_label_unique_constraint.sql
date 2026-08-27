ALTER TABLE labels
    ADD CONSTRAINT uq_label_name_email UNIQUE (name, email);
