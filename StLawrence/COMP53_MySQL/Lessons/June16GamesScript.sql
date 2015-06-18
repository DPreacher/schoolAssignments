-- June 16 M:N Practice
DROP DATABASE IF EXISTS June16Games;
CREATE DATABASE IF NOT EXISTS June16Games;
USE June16Games;

CREATE TABLE game
(
	gameID			smallint		primary key,
	game_name		VARCHAR(35),
	game_released	YEAR
);

insert into game values
	(101,'Civilization', 1992),
    (102,'Rise of Nations', 2003),
    (103, 'Earthworm Jim', 1994),
    (104, 'Sonic Hedgehog',1991)
    ;
    
CREATE TABLE developer
(
	developerID		smallint		primary key,
    developerFName	varchar(15),
    developerLName	varchar(15)
);

INSERT INTO developer VALUES
	(01,'Brian', 'Reynolds'),
	(02,'David', 'Perry'),
    (03,'Paulo', 'Sanchez'),
    (04, 'Yuji', 'Naka'),
    (05,'Tetsu','Katano')
    ;

CREATE TABLE gameDevLink
(
	developerID	SMALLINT,
    gameID 		SMALLINT,
		PRIMARY KEY(developerID,gameID),
        
        CONSTRAINT dev_fk
			FOREIGN KEY (developerID)
            REFERENCES developer(developerID),
		
        CONSTRAINT game_fk
			FOREIGN KEY (gameID)
            REFERENCES game(gameID)
);
INSERT INTO gameDevLink VALUES
	(01,101),
    (01,102),
    (02,103),
    (03,103),
    (04,104),
    (05,104)
	;
    
SELECT *
FROM game
ORDER BY gameID;

SELECT *
FROM developer
ORDER BY developerID;
