DROP TABLE users IF EXISTS;
DROP TABLE authorities IF EXISTS;
DROP TABLE user_authorities IF EXISTS;


CREATE TABLE IF NOT EXISTS users (
 	 id INTEGER IDENTITY PRIMARY KEY,
 	 firstName 		VARCHAR(10) NOT NULL,  
     lastName 		VARCHAR(60) NOT NULL,
     username 		VARCHAR(10) NOT NULL,  
     password 		VARCHAR(60) NOT NULL,
     email 			VARCHAR(60) NOT NULL,
	 corporation 	VARCHAR(60) NOT NULL,
	 gender 		VARCHAR(60) NOT NULL,
	 residence 		VARCHAR(60) NOT NULL,
	 street 		VARCHAR(60) NOT NULL,
	 zipcode 		VARCHAR(60) NOT NULL,
	 phone 			VARCHAR(60) NOT NULL
);  

CREATE TABLE IF NOT EXISTS user_authority (
	user_id 		INTEGER NOT NULL,
	authority_id 	INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS authority (
	id 				INTEGER IDENTITY PRIMARY KEY,
	name 			VARCHAR(60) NOT NULL
);


DROP TABLE events IF EXISTS;
DROP TABLE audios IF EXISTS;
DROP TABLE caterings IF EXISTS;

CREATE TABLE IF NOT EXISTS events (
  id         		INTEGER IDENTITY PRIMARY KEY,
  name 		 		VARCHAR(30),
  date 				DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS audios (
	audioId			INTEGER IDENTITY PRIMARY KEY,
	name			VARCHAR (100) NOT NULL,
	event			INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS caterings (
	cateringId			INTEGER IDENTITY PRIMARY KEY,
	eating 				INTEGER NOT NULL,
	eatingType			TINYINT,
	eatingPeople		INTEGER NOT NULL,
	drinking 			TINYINT,
	drinkingPeople		INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS lights (
	lightId			INTEGER IDENTITY PRIMARY KEY,
	name 				VARCHAR(100),
	numberOfLights		INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS locations (
	locationId			INTEGER IDENTITY PRIMARY KEY,
	name 				VARCHAR(100),
	street				VARCHAR(100),
	town				VARCHAR(100),
	type				VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS riggings (
	riggingId			INTEGER IDENTITY PRIMARY KEY,
	name 				VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS securities (
	securityId			INTEGER IDENTITY PRIMARY KEY,
	name 				VARCHAR(100),
	numberOfProtectees 	INTEGER
);

CREATE TABLE IF NOT EXISTS specialties (
	specialtyId			INTEGER IDENTITY PRIMARY KEY,
	subject				VARCHAR(100),
	comment				VARCHAR(100)
);
