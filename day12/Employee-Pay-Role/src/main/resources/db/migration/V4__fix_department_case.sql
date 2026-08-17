-- With DATABASE_TO_UPPER=FALSE, V1's unquoted "employee"/"department"
-- are already stored literally in lowercase, so no renaming is needed here.
-- This migration now only re-establishes the FK using quoted, case-matching names.

ALTER TABLE "employee" DROP CONSTRAINT fk_employee_department;

ALTER TABLE "employee"
    ADD CONSTRAINT fk_employee_department
    FOREIGN KEY ("department_id")
    REFERENCES "department"("id");