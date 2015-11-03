DROP DATABASE IF EXISTS tickets;

CREATE DATABASE tickets;

USE tickets;

CREATE TABLE event
(
    eventID SERIAL PRIMARY KEY,
    date DATE,
    eventType ENUM('CONCERT', 'SPORTS')
);

CREATE TABLE customer
(
    customerID SERIAL PRIMARY KEY,
    firstName VARCHAR(20),
    LastName VARCHAR(20)
);

CREATE TABLE customerEvent
(
    customerID BIGINT,
    eventID BIGINT,
    ticketCount INT DEFAULT 0
    FOREIGN KEY (eventID)
        REFERENCES event(eventID)
    FOREIGN KEY (customerID)
            REFERENCES customer(customerID)
);
 
CREATE TABLE ticket
(
    ticketID SERIAL PRIMARY KEY,
    eventID     BIGINT,
    FOREIGN KEY (eventID)
        REFERENCES event(eventID)
);

/* Excercise
    Add in Foriegn keys and  
... */

INSERT INTO event VALUES
(NULL, '2015-11-20','SPORTS'),
(null, '2015-11-23', 'CONCERT');

insert into customer values
(null, 'fred', 'smith'),
(null, 'sue', 'taylor');

DELIMITER // /* 2 common dilimiters is 2 forward slashes or 2 dollar symbols */

CREATE PROCEDURE SellTicket
    /* PERAMETER*/
    ( IN customer BIGINT,
      /* NEED TO SPECIFIY the direction the data is going with the procedure ( ex: IN)*/
      IN event BIGINT, /* references eventID.  The name doen't have to be the same*/
      IN nTickets INT
    )
BEGIN /* Start the procedure */
    IF nTickets <= 4 THEN
        INSERT INTO customerEvent VALUES
        (customer, event, nTickets);
    ELSE
        SELECT 'Too Many Tickets';
    END IF;
END//
DELIMITER ; /* Set the delimiter back to the semi-colon*/

/* call the above procedure */
call sellTicket(1,1,4);
call sellTicket(1,2,4);
call sellTicket(2,2,7);
call sellTicket(1,3,4); /* allows  it as long of the forign keys aren't created*/

/* check the customer Event log */
SELECT * FROM customerEvent;
