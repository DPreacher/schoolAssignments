DROP PROCEDURE IF EXISTS AddVet;
DELIMITER //
  CREATE PROCEDURE AddVet
    (
        in  vetId int,
        in  firstName VARCHAR (45),
        in lastName VARCHAR(45)
    )
BEGIN
        DECLARE duplicate_record TINYINT DEFAULT FALSE;   
        DECLARE CONTINUE HANDLER FOR 1062       
        SET duplicate_record = TRUE;
        IF duplicate_record = TRUE THEN
            SELECT "ERROR 1062: Duplicate Primary Key";
        END IF;
        
INSERT INTO VET VALUES (vetId, firstName, lastName);
        SELECT "PROCEDURE FINISHED";
    END//
DELIMITER ;

call AddVet(30,"Barry", "Moody");
