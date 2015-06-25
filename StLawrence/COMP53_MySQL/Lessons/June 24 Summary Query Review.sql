USE om;

-- 1 --
SELECT *
FROM order_details;

-- 2 --
SELECT count(*) AS 'Number of Orders', MAX(order_qty) AS 'Most Ordered'
from order_details;

-- 3 --
SELECT COUNT(order_id) AS 'Number of Orders', MAX(order_qty) AS 'Most Ordered'
FROM order_details;

-- 4 --
SELECT count(*) from order_details where order_id = 796;

-- 5 --
SELECT  count(DISTINCT order_id) from order_details;

-- 6 --
SELECT count(*) from order_details where order_id <= 300 AND order_id >= 100;

-- GROUP BY & HAVING --
-- 1 --
SELECT count(*) from order_details having order_id between 100 and 300;  -- doesn't work, see page 175

-- 2 --
select order_id, count(*)
from order_details
group By order_id;
-- 3 --
select order_id, count(*)
from order_details
group By order_id 
having count(*) > 2;

USE ap;

-- 1 --
select vendor_id, invoice_total
FROM invoices;

-- 2 --
select vendor_id, invoice_total
FROM invoices
group by vendor_id;

-- 3 --
select vendor_id, invoice_total
FROM invoices
group by vendor_id
having invoice_total >2000;
