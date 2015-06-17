-- June 17 Example
-- Joining tables without using alias

-- 1.A Teacher --
USE ap;
SELECT invoice_number, vendor_name
FROM vendors
	INNER JOIN invoices
		ON vendors.vendor_id = invoices.vendor_id
ORDER BY invoice_number;
/*
ON - conects like fields together and must be unique otherwise won't work
*/

-- 1.B Practice --
SELECT invoice_number, terms_description
FROM invoices
	INNER JOIN terms
	ON invoices.terms_id = terms.terms_id
ORDER BY invoice_number DESC;  -- if invoices. is missing, will recieve error of: fieldName in on clause is ambiguous

-- 2.a  rewrite 1.a with alliases--
SELECT invoice_number, vendor_name, i.vendor_id
FROM vendors v
	INNER JOIN invoices i
		ON v.vendor_id = i.vendor_id
ORDER BY invoice_number;  
		-- once using aliases, must use them throughout the rest of the query when used with common fileds.
		-- if want to use the foreign key must specify which table's foreign key you want to use.
		-- Remember: FROM STATEMENT IS USED FIRST

-- 2.b rewite 1.b with aliases --
SELECT invoice_number, terms_description
FROM invoices
	INNER JOIN terms
	ON invoices.terms_id = terms.terms_id
ORDER BY invoice_number DESC; 

-- 3.  Compund Join --
USE ex;
SELECT customer_first_name, customer_last_name
FROM customers c 
	JOIN employees e
	ON c.customer_first_name = e.first_name
	AND c.customer_last_name = e.last_name;

-- Joining 3 Tables --
-- 4.a --
USE ap;
SELECT vendor_name, invoice_number, invoice_due_date, terms_description
FROM invoices i
	JOIN vendors  v
		ON i.vendor_id = v.vendor_id
	JOIN terms t
		ON i.terms_id = t.terms_id
ORDER BY invoice_number DESC;  -- if field names are different then don't need to add table name or alias

-- Other ways from 1.A--

	-- USING p 135/137
	USE ap;
	SELECT invoice_number, vendor_name
	FROM vendors
		 JOIN invoices USING (vendor_id)
	ORDER BY invoice_number;
			-- works if both foriegn key and primary key are the same

	-- p 137/139
	SELECT invoice_number, vendor_name
	FROM vendors
		NATURAL JOIN invoices
	ORDER BY invoice_number;
		-- could run into problems if the structure of the database changes
		-- works by searching for 2 field names that are the same in both tables

-- 4.B --
USE om;

SELECT customer_first_name, customer_last_name, order_date
FROM customers c
	INNER JOIN orders o
	ON o.customer_id = c.customer_id
ORDER BY customer_last_name, order_date DESC;
