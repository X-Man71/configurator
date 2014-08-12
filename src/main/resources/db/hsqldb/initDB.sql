DROP TABLE users IF EXISTS;
DROP TABLE authorities IF EXISTS;
DROP TABLE user_authorities IF EXISTS;

CREATE TABLE IF NOT EXISTS users (
 	 id 				INTEGER IDENTITY PRIMARY KEY,
 	 version 			DATETIME,
 	 firstName 			VARCHAR(10) NOT NULL,  
     lastName 			VARCHAR(60) NOT NULL,
     username 			VARCHAR(10) NOT NULL,  
     password 			VARCHAR(60) NOT NULL,
     enabled			TINYINT
);  

CREATE TABLE IF NOT EXISTS user_authority (
	user_id 			INTEGER NOT NULL,
	authority_id 		INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS authority (
	id 					INTEGER IDENTITY PRIMARY KEY,
	name 				VARCHAR(60) NOT NULL
);
