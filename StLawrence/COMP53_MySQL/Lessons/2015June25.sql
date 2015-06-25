/*
	In Class Lessons

*/
-- ** Note ** 
	-- any syntax with [ ] is optional to input

USE ap;
-- Update and Delete

CREATE TABLE invoices_copy AS
SELECT *
FROM invoices;	
	-- Create a copy of the invoice TABLE
SELECT *
FROM invoices_copy;
	-- verify copy data

/*		UPDATE STATEMENT
Syntax:
-------

UPDATE table_name
SET column_name_1 = expression_1
	[, column_name_2 = expression_2 ...]
[WHERE search_condition];

*/
-- update 
	-- change data already inputted
UPDATE invoices_copy
SET credit_total = 99
WHERE credit_total = 200;

SELECT *
FROM invoices_copy
WHERE credit_total = 99;

-- update Excercise
UPDATE invoices_copy
set credit_total = credit_total +100
where invoice_number = '97/522';

select * 
from invoices_copy 
where invoice_number;

-- 1 --
CREATE TABLE termsbackup AS
SELECT *
FROM terms;

-- 2 --
SELECT *
from termsbackup
where terms_id = 2; -- check data before coding

update termsbackup
set terms_description = 'Net due 25 days',
	terms_due_days = 25
where terms_id = 2; -- update (change) data

SELECT *
from termsbackup
where terms_id = 2; -- verify change occured



/*  	DELETE STATEMENT
Syntax:
-------
DELETE FROM table_name
[WHERE search_condition];
*/

-- 1 --
CREATE TABLE invoice_line_items_BACKUP AS
SELECT *
FROM invoice_line_items;

SELECT *
FROM invoice_line_items_BACKUP;

-- 2 --
-- a)
DELETE FROM invoice_line_items_backup
WHERE invoice_id = 78 AND invoice_sequence = 2;

-- b)
DELETE FROM invoice_line_items_backup
WHERE invoice_id=12;

-- c)
DELETE FROM invoice_line_items_backup
WHERE invoice_id IN
	(SELECT invoice_id
	 FROM invoices
	 WHERE vendor_id = 115);

-- ---------------------- --
-- ** DELETE EXCERCISE ** --
-- ---------------------- --
 -- 1 )
	-- create new row for termsbackup table and display
INSERT INTO termsbackup VALUES
	(6,'Net due 40 days',40);
select * from termsbackup;

-- 2)
	-- Delete the data you created and verify
DELETE FROM termsbackup
where terms_id = 6;
select * from termsbackup;

-- 3)
	-- display number of terms due greater than 20
SELECT count(terms_due_days)
FROM termsbackup
where terms_due_days > 20 ;

/* 		SUBQUERIES
-- cannot have an ORDER BY clause
*/
	-- EXAMPLE 1 --
-- a)
select avg(invoice_total)
from invoices;
-- b)
select invoice_number, invoice_date, invoice_total
from invoices
where invoice_total >
	(select avg(invoice_total)
	from invoices)
order by invoice_total;

	-- EXAMPLE 2 --
-- a)
select invoice_number, invoice_date, 
	   invoice_total - payment_total - credit_total AS balance_due
from invoices;
-- b)
select invoice_number, invoice_date, 
	   invoice_total - payment_total - credit_total AS balance_due
from invoices
where invoice_total - payment_total-credit_total>0;
-- c)
select avg(invoice_total - payment_total - credit_total)
from invoices
where invoice_total - payment_total - credit_total>0;
-- e)
select invoice_number, invoice_date, 
	   invoice_total - payment_total - credit_total AS balance_due
from invoices
where invoice_total - payment_total-credit_total>0
	and invoice_total - payment_total-credit_total <
	(
	select avg(invoice_total - payment_total - credit_total)
	from invoices
	where invoice_total - payment_total - credit_total>0
	)
order by invoice_total desc;

	-- EXAMPLE 3 --  inner join verse subqueries
-- a)
select invoice_number, invoice_date, invoice_total
from invoices i
	join vendors v
	ON i.vendor_id = v.vendor_id
where vendor_state = 'CA'
order by invoice_date;

-- b)
select vendor_id
	from vendors
	where vendor_state = 'CA';
-- c)
select invoice_number, invoice_date, invoice_total
from invoices
where vendor_id IN
	(
	select vendor_id
	from vendors
	where vendor_state = 'CA'
	)
order by invoice_date;
	-- advantages of Subqueries
		-- allows aggregate value (avg, count) ext in where statement
		-- more intuitive with ad hoc relationships
		-- sometimes easier to code

	-- EXAMPLE 4 -- get vendors without invoices
-- a) subquery only
select distinct vendor_id
from invoices;
-- b) all together
select vendor_id, vendor_name, vendor_state
from vendors
where vendor_id NOT IN
	(
	select distinct vendor_id
	from invoices
	)
order by vendor_id;

/* 	INSERT Statement
	-- Without Column list:
		-- data must be in same order as defined in tables
		-- must include a value for every column
	-- With Column List
		-- must specify column names after table name
		-- can be in any order
Syntax:
-------

INSERT [INTO] table_name [(column_list)] 
VALUES	(experssion_1[,epression_2 ...])[,
		(experssion_1[,epression_2 ...])]
;
*/
	-- Example 1 -- 
-- a) delete rows from color_sample table
USE ex;
CREATE TABLE color_sample_COPY AS
select *
from color_sample;
delete from color_sample_COPY;

-- b) type in the five select statements on p.155 (p.203 older edition)
insert into color_sample_COPY (color_number)
values (606);

insert into color_sample_COPY(color_name)
values ('Yellow');

insert into color_sample_COPY
values(DEFAULT, DEFAULT, 'ORANGE');

insert into color_sample_COPY
values (DEFAULT, 808, NULL);

insert into color_sample_COPY
values (DEFAULT,default,NULL);

-- insert with subquery
	-- p.157(p.205)
/*		CREATE INDEX Statement
		-- auto created for Primary/foriegn key, and unique constraints

Syntax:
-------
CREATE [UNIQUE] INDEX index_name
	ON [db_name.]table_name (column_name_1 [ASC|DESC][,
							 column_name_2 [ASC|DESC]...]);

*/

	-- Example 1 -- 
use ap;
create index invoices_vendor_id_invoice_number_ix
on invoices (vendor_id, invoice_number);
