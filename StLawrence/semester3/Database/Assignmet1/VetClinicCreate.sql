DROP DATABASE IF EXISTS VetClinic;
CREATE DATABASE VetClinic;
USE VetClinic;

CREATE TABLE Owner
(
	owner_id			INT(10)			PRIMARY KEY,
	firstName			VARCHAR(45),
	lastName			VARCHAR(45),
	phone 				VARCHAR(12),
	street_address		VARCHAR(45),
	city				VARCHAR(20),
	postal_code			VARCHAR(8)
);

CREATE TABLE Vet
(
	vet_id				INT(10)			PRIMARY KEY,
	fistName			VARCHAR(45),
	lastName			VARCHAR(45)
);

CREATE TABLE Pet
(
	pet_id				INT(10)			PRIMARY KEY,
	petName				VARCHAR(45),
	owner_id			INT(10),
	breed				VARCHAR(20),
	CONSTRAINTS fk_owner_id FOREIGN KEY(owner_id) REFERENCES Owner(owner_id)
);

CREATE TABLE Appointment
(
	appointment_id		INT(10)			PRIMARY KEY,
	vet_id 				INT(10),
	pet_id				INT(10),
	appt_time			DATETIME
	CONSTRAINT fk_vet_id FOREIGN KEY (vet_id) REFERENCES Vet(vet_id),
	CONSTRAINT fk_pet_id FOREIGN KEY (pet_id) REFERENCES Pet(pet_id)
	
);

