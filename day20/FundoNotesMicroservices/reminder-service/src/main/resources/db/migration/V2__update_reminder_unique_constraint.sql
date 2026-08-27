-- Drop the old constraint that only covered (note_id, email)
ALTER TABLE reminders DROP INDEX uq_reminder_note_email;

-- Add the new constraint covering (note_id, email, reminder_time)
ALTER TABLE reminders
    ADD CONSTRAINT uq_reminder_note_email_time
    UNIQUE (note_id, email, reminder_time);