-- Departments
INSERT INTO department (id, name, description) VALUES
('11111111-1111-1111-1111-111111111111', 'Engineering', 'Builds and maintains all software products'),
('22222222-2222-2222-2222-222222222222', 'Human Resources', 'Handles hiring, payroll, and employee relations'),
('33333333-3333-3333-3333-333333333333', 'Sales', 'Manages client acquisition and revenue growth'),
('44444444-4444-4444-4444-444444444444', 'Marketing', 'Runs branding, campaigns, and market research'),
('55555555-5555-5555-5555-555555555555', 'Finance', 'Oversees budgeting, accounting, and financial planning');

-- Employees
INSERT INTO employee (id, name, email, phone, salary, department_id) VALUES
('a1111111-aaaa-1111-aaaa-111111111111', 'Rohan Mehta', 'rohan.mehta@company.com', '+919876543210', 75000.00, '11111111-1111-1111-1111-111111111111'),
('a2222222-aaaa-2222-aaaa-222222222222', 'Priya Sharma', 'priya.sharma@company.com', '+919876543211', 82000.00, '11111111-1111-1111-1111-111111111111'),
('a3333333-aaaa-3333-aaaa-333333333333', 'Aditya Verma', 'aditya.verma@company.com', '+919876543212', 68000.00, '11111111-1111-1111-1111-111111111111'),
('a4444444-aaaa-4444-aaaa-444444444444', 'Sneha Kapoor', 'sneha.kapoor@company.com', '+919876543213', 55000.00, '22222222-2222-2222-2222-222222222222'),
('a5555555-aaaa-5555-aaaa-555555555555', 'Karan Malhotra', 'karan.malhotra@company.com', '+919876543214', 51000.00, '22222222-2222-2222-2222-222222222222'),
('a6666666-aaaa-6666-aaaa-666666666666', 'Ananya Iyer', 'ananya.iyer@company.com', '+919876543215', 64000.00, '33333333-3333-3333-3333-333333333333'),
('a7777777-aaaa-7777-aaaa-777777777777', 'Vikram Rao', 'vikram.rao@company.com', '+919876543216', 71000.00, '33333333-3333-3333-3333-333333333333'),
('a8888888-aaaa-8888-aaaa-888888888888', 'Ishita Desai', 'ishita.desai@company.com', '+919876543217', 59000.00, '44444444-4444-4444-4444-444444444444'),
('a9999999-aaaa-9999-aaaa-999999999999', 'Arjun Nair', 'arjun.nair@company.com', '+919876543218', 62000.00, '44444444-4444-4444-4444-444444444444'),
('b1111111-bbbb-1111-bbbb-111111111111', 'Neha Joshi', 'neha.joshi@company.com', '+919876543219', 79000.00, '55555555-5555-5555-5555-555555555555');