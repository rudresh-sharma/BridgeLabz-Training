CREATE TABLE audit_log (
    id UUID PRIMARY KEY,
    action VARCHAR(100),
    entity_type VARCHAR(50),
    entity_id VARCHAR(100),
    details VARCHAR(500),
    timestamp TIMESTAMP
);