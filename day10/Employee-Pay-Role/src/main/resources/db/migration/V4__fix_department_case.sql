-- Drop the FK first so column renames don't conflict with it
ALTER TABLE EMPLOYEE DROP CONSTRAINT fk_employee_department;

-- Rename department table and columns
ALTER TABLE DEPARTMENT RENAME TO "department";
ALTER TABLE "department" ALTER COLUMN ID RENAME TO "id";
ALTER TABLE "department" ALTER COLUMN NAME RENAME TO "name";
ALTER TABLE "department" ALTER COLUMN DESCRIPTION RENAME TO "description";

-- Rename employee table and columns
ALTER TABLE EMPLOYEE RENAME TO "employee";
ALTER TABLE "employee" ALTER COLUMN ID RENAME TO "id";
ALTER TABLE "employee" ALTER COLUMN NAME RENAME TO "name";
ALTER TABLE "employee" ALTER COLUMN EMAIL RENAME TO "email";
ALTER TABLE "employee" ALTER COLUMN PHONE RENAME TO "phone";
ALTER TABLE "employee" ALTER COLUMN SALARY RENAME TO "salary";
ALTER TABLE "employee" ALTER COLUMN DEPARTMENT_ID RENAME TO "department_id";

-- Re-add the FK using the new quoted names
ALTER TABLE "employee"
    ADD CONSTRAINT fk_employee_department
    FOREIGN KEY ("department_id")
    REFERENCES "department"("id");