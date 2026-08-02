-- ============================================================================
-- COMPLETE MYSQL INDEXING TUTORIAL
-- Run this file top to bottom in MySQL Workbench / CLI to learn indexing.
-- Every concept is explained in comments right above the query that uses it.
-- ============================================================================


-- ============================================================================
-- SECTION 0: WHAT IS AN INDEX?
-- ============================================================================
-- An index is a sorted data structure (B-Tree in InnoDB) that MySQL builds
-- on one or more columns. Instead of scanning every row (full table scan),
-- MySQL can jump straight to matching rows -- like using a book's index
-- instead of reading every page.
--
-- Trade-off: indexes speed up SELECT / WHERE / JOIN / ORDER BY,
-- but slow down INSERT / UPDATE / DELETE and use extra disk space.
-- So we only index columns that are actually searched/sorted/joined on.
-- ============================================================================


-- ============================================================================
-- SECTION 1: SETUP -- sample database, tables, and data
-- ============================================================================

CREATE DATABASE IF NOT EXISTS indexing_demo;
USE indexing_demo;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    customer_id   INT AUTO_INCREMENT PRIMARY KEY,   -- PRIMARY KEY auto-creates a unique index
    email         VARCHAR(100) NOT NULL,
    full_name     VARCHAR(100) NOT NULL,
    city          VARCHAR(50),
    phone         VARCHAR(20),
    bio           TEXT,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders (
    order_id      INT AUTO_INCREMENT PRIMARY KEY,
    customer_id   INT NOT NULL,
    order_status  VARCHAR(20) NOT NULL,
    order_total   DECIMAL(10,2) NOT NULL,
    order_date    DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
    -- NOTE: InnoDB auto-creates an index on customer_id because it's a
    -- foreign key -- it needs a fast index to check referential integrity.
);

INSERT INTO customers (email, full_name, city, phone, bio) VALUES
('anita@example.com', 'Anita Sharma', 'Mumbai', '9990001111', 'Loves cricket and coffee'),
('rahul@example.com', 'Rahul Verma', 'Delhi',  '9990002222', 'Software engineer'),
('priya@example.com', 'Priya Nair',  'Chennai','9990003333', 'Foodie and traveler'),
('sam@example.com',   'Sam Khan',    'Mumbai', '9990004444', 'Musician'),
('neha@example.com',  'Neha Gupta',  'Pune',   '9990005555', 'Data analyst');

INSERT INTO orders (customer_id, order_status, order_total, order_date) VALUES
(1, 'DELIVERED', 1200.00, '2026-01-05'),
(1, 'PENDING',    450.00, '2026-02-10'),
(2, 'DELIVERED',  999.99, '2026-01-15'),
(3, 'CANCELLED',  250.00, '2026-03-01'),
(4, 'DELIVERED', 3000.00, '2026-03-20'),
(5, 'PENDING',    100.00, '2026-04-02');


-- ============================================================================
-- SECTION 2: VIEWING EXISTING INDEXES
-- ============================================================================

-- Every table gets an automatic index on its PRIMARY KEY.
-- Run this to see it (Key_name = 'PRIMARY'):
SHOW INDEX FROM customers;

-- Column meanings:
--   Key_name      -> name of the index ('PRIMARY' for the primary key)
--   Column_name   -> which column is indexed
--   Non_unique    -> 0 = unique values only, 1 = duplicates allowed
--   Seq_in_index  -> column's position within a composite (multi-column) index
--   Cardinality   -> estimated distinct values (higher = more useful index)


-- ============================================================================
-- SECTION 3: UNIQUE INDEX
-- ============================================================================
-- Prevents duplicate values in a column (one NULL still allowed).
-- Perfect for emails, usernames, SKUs, etc.

CREATE UNIQUE INDEX idx_customers_email ON customers(email);

-- Try this -- it will FAIL with a duplicate key error, proving the index works:
-- INSERT INTO customers (email, full_name) VALUES ('anita@example.com', 'Fake Anita');


-- ============================================================================
-- SECTION 4: REGULAR (NON-UNIQUE) INDEX
-- ============================================================================
-- Speeds up filtering but allows duplicate values in the column.

CREATE INDEX idx_customers_city ON customers(city);

-- Compare: without the index above, this would be a full table scan.
-- With it, MySQL jumps straight to 'Mumbai' rows.
EXPLAIN SELECT * FROM customers WHERE city = 'Mumbai';


-- ============================================================================
-- SECTION 5: COMPOSITE (MULTI-COLUMN) INDEX
-- ============================================================================
-- Indexes multiple columns together, like a phone book sorted by
-- last name, then first name. COLUMN ORDER MATTERS -- see Section 8.

CREATE INDEX idx_orders_status_date ON orders(order_status, order_date);

-- This query uses the index efficiently because it filters on the
-- LEFTMOST column (order_status) first:
EXPLAIN SELECT * FROM orders
WHERE order_status = 'DELIVERED' AND order_date > '2026-01-01';


-- ============================================================================
-- SECTION 6: FULLTEXT INDEX
-- ============================================================================
-- For natural-language searching inside long text columns.
-- Only works with MATCH...AGAINST, NOT with LIKE '%word%'.

CREATE FULLTEXT INDEX idx_customers_bio ON customers(bio);

SELECT full_name, bio
FROM customers
WHERE MATCH(bio) AGAINST('coffee cricket' IN NATURAL LANGUAGE MODE);


-- ============================================================================
-- SECTION 7: PREFIX INDEX
-- ============================================================================
-- For long VARCHAR/TEXT columns, indexing only the first N characters
-- saves space while still speeding up most lookups.

CREATE INDEX idx_customers_phone_prefix ON customers(phone(4));
-- Only the first 4 characters of "phone" are stored in this index.


-- ============================================================================
-- SECTION 8: COMPOSITE INDEX COLUMN ORDER -- THE MOST IMPORTANT RULE
-- ============================================================================
-- idx_orders_status_date is built as (order_status, order_date).
-- You can search efficiently using:
--   order_status alone                     -> OK (leftmost column)
--   order_status + order_date together     -> OK (both columns, in order)
-- But NOT efficiently using:
--   order_date alone                       -> skips the leftmost column

-- FAST -- uses the index (leftmost column present):
EXPLAIN SELECT * FROM orders WHERE order_status = 'DELIVERED';

-- FAST -- uses both columns of the index:
EXPLAIN SELECT * FROM orders
WHERE order_status = 'DELIVERED' AND order_date = '2026-01-05';

-- SLOW -- order_date alone CANNOT use this index (falls back to full scan):
EXPLAIN SELECT * FROM orders WHERE order_date = '2026-01-05';

-- RULE OF THUMB: put the column you filter on most often, or the one with
-- the most distinct values (most "selective"), first in the index.


-- ============================================================================
-- SECTION 9: COVERING INDEX (fastest possible query)
-- ============================================================================
-- If an index contains EVERY column a query needs, MySQL reads only the
-- index and never touches the actual table rows. This is the fastest
-- type of query MySQL can run.

CREATE INDEX idx_orders_covering ON orders(customer_id, order_status, order_total);

-- All 3 selected columns exist in the index above, so this is "covered":
EXPLAIN SELECT customer_id, order_status, order_total
FROM orders
WHERE customer_id = 1;

-- In the EXPLAIN output, look at the "Extra" column for the words
-- "Using index" -- that confirms it's a covering index.


-- ============================================================================
-- SECTION 10: READING EXPLAIN OUTPUT
-- ============================================================================

EXPLAIN SELECT * FROM orders
WHERE customer_id = 1 AND order_status = 'DELIVERED';

-- Key columns to read:
--   type            -> access method. Best to worst:
--                       const, eq_ref, ref, range, index, ALL
--                       ('ALL' means full table scan -- usually bad)
--   possible_keys   -> indexes MySQL COULD use
--   key             -> the index MySQL ACTUALLY chose
--   rows            -> estimated rows scanned (lower = better)
--   Extra           -> "Using index" = great (covering index)
--                       "Using filesort" = bad, had to sort manually
--                       "Using temporary" = bad, needed a temp table

-- EXPLAIN ANALYZE (MySQL 8.0.18+) shows REAL execution time, not just
-- an estimate -- more accurate for tuning:
EXPLAIN ANALYZE
SELECT * FROM orders WHERE customer_id = 1 AND order_status = 'DELIVERED';


-- ============================================================================
-- SECTION 11: INDEXES AND ORDER BY
-- ============================================================================
-- An index that is already sorted can remove the need to sort at query time.

CREATE INDEX idx_orders_date_only ON orders(order_date);

-- Avoids "Using filesort" in Extra, because order_date is pre-sorted
-- inside the index:
EXPLAIN SELECT * FROM orders ORDER BY order_date;


-- ============================================================================
-- SECTION 12: WHEN AN INDEX WILL NOT BE USED (common mistakes)
-- ============================================================================

-- MISTAKE 1: wrapping the indexed column in a function disables the index.
EXPLAIN SELECT * FROM customers WHERE YEAR(created_at) = 2026;                 -- BAD, full scan
EXPLAIN SELECT * FROM customers
WHERE created_at >= '2026-01-01' AND created_at < '2027-01-01';                -- GOOD, uses index

-- MISTAKE 2: a leading wildcard in LIKE disables the index.
EXPLAIN SELECT * FROM customers WHERE full_name LIKE '%Sharma';                -- BAD, can't use index
EXPLAIN SELECT * FROM customers WHERE full_name LIKE 'Anita%';                 -- GOOD, can use index

-- MISTAKE 3: comparing a string column to a number causes implicit
-- conversion and disables the index.
EXPLAIN SELECT * FROM customers WHERE phone = 9990001111;                      -- BAD (phone is VARCHAR)
EXPLAIN SELECT * FROM customers WHERE phone = '9990001111';                    -- GOOD

-- MISTAKE 4: OR across two DIFFERENT columns often prevents index use.
-- (A UNION of two indexed queries is usually faster.)
EXPLAIN SELECT * FROM customers WHERE city = 'Mumbai' OR full_name = 'Rahul Verma'; -- often BAD


-- ============================================================================
-- SECTION 13: FUNCTIONAL / GENERATED COLUMN INDEX (MySQL 8.0+)
-- ============================================================================
-- If you MUST query on an expression (like YEAR(created_at)), create a
-- generated column for that expression and index the column instead.

ALTER TABLE customers ADD COLUMN created_year INT
    GENERATED ALWAYS AS (YEAR(created_at)) STORED;

CREATE INDEX idx_customers_created_year ON customers(created_year);

-- Now this uses the index instead of scanning every row:
EXPLAIN SELECT * FROM customers WHERE created_year = 2026;


-- ============================================================================
-- SECTION 14: DESCENDING INDEX (MySQL 8.0+)
-- ============================================================================
-- Stores values in descending order -- helps queries sorting DESC.

CREATE INDEX idx_orders_total_desc ON orders(order_total DESC);

EXPLAIN SELECT * FROM orders ORDER BY order_total DESC;


-- ============================================================================
-- SECTION 15: INVISIBLE INDEX (MySQL 8.0+)
-- ============================================================================
-- Lets you "hide" an index from the query optimizer WITHOUT deleting it.
-- Useful for safely testing "what if I removed this index?" before
-- actually dropping it.

ALTER TABLE orders ALTER INDEX idx_orders_total_desc INVISIBLE;

-- Optimizer now ignores idx_orders_total_desc completely:
EXPLAIN SELECT * FROM orders ORDER BY order_total DESC;

-- Bring it back if performance got worse:
ALTER TABLE orders ALTER INDEX idx_orders_total_desc VISIBLE;


-- ============================================================================
-- SECTION 16: FORCING / IGNORING INDEXES (for testing/comparison)
-- ============================================================================

-- Force MySQL to IGNORE an index, to see how slow the query is without it:
EXPLAIN SELECT * FROM orders IGNORE INDEX (idx_orders_status_date)
WHERE order_status = 'DELIVERED';

-- Force MySQL to USE a specific index (overrides the optimizer's own choice):
EXPLAIN SELECT * FROM orders FORCE INDEX (idx_orders_status_date)
WHERE order_status = 'DELIVERED';


-- ============================================================================
-- SECTION 17: DROPPING INDEXES
-- ============================================================================

DROP INDEX idx_customers_phone_prefix ON customers;
-- Equivalent alternative syntax:
-- ALTER TABLE customers DROP INDEX idx_customers_phone_prefix;


-- ============================================================================
-- SECTION 18: CREATING INDEXES -- ALL THE SYNTAX OPTIONS (reference)
-- ============================================================================

-- A) Inline, while creating a table:
--    CREATE TABLE products (
--        product_id INT PRIMARY KEY,
--        sku VARCHAR(30),
--        INDEX idx_products_sku (sku)
--    );

-- B) After the table exists, using CREATE INDEX:
--    CREATE INDEX idx_orders_customer ON orders(customer_id);

-- C) After the table exists, using ALTER TABLE:
--    ALTER TABLE orders ADD INDEX idx_orders_date (order_date);

-- D) Adding a UNIQUE constraint (this also creates an index automatically):
--    ALTER TABLE customers ADD CONSTRAINT uq_phone UNIQUE (phone);


-- ============================================================================
-- SECTION 19: FINDING UNUSED INDEXES (housekeeping)
-- ============================================================================
-- Requires performance_schema to be enabled (it is, by default, in MySQL 8+).
-- Unused indexes only slow down writes with no read benefit -- good
-- candidates to drop.

-- SELECT * FROM sys.schema_unused_indexes;


-- ============================================================================
-- SECTION 20: BEST PRACTICES SUMMARY (read this, don't run it)
-- ============================================================================
-- 1. Index columns used in WHERE, JOIN ON, ORDER BY, GROUP BY -- not every column.
-- 2. In composite indexes, put the most selective / most-filtered column first.
-- 3. Don't over-index: every index slows down INSERT/UPDATE/DELETE.
-- 4. Always check EXPLAIN before and after adding an index.
-- 5. Use covering indexes for your hottest, most frequent queries.
-- 6. Never wrap an indexed column in a function inside WHERE.
-- 7. Use prefix indexes for long text/VARCHAR columns.
-- 8. Use INVISIBLE indexes to safely test removing an index before dropping it.
-- 9. Periodically check sys.schema_unused_indexes and clean up.


-- ============================================================================
-- SECTION 21: CLEANUP (optional -- run only when you're done practicing)
-- ============================================================================

-- DROP DATABASE indexing_demo;