CREATE TABLE IF NOT EXISTS users (
 	 id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	 firstName 		VARCHAR(10) NOT NULL,  
     lastName 		VARCHAR(60) NOT NULL,
     username 		VARCHAR(10) NOT NULL,  
     password 		VARCHAR(60) NOT NULL,
     email 			VARCHAR(60) NOT NULL,
	 consumerRateNumber INTEGER(4),
	 consumerRateLetter VARCHAR(5), 
	 consumerRateValideUntil DATE
) engine=InnoDB; 

CREATE TABLE IF NOT EXISTS user_authority (
	user_id 		INTEGER NOT NULL,
	authority_id 	INTEGER NOT NULL
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS authority (
	id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name 			VARCHAR(60) NOT NULL
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS loan (
  requestId     	VARCHAR(60) PRIMARY KEY,
  status 			VARCHAR(30),
  amount 			DOUBLE,
  term				INTEGER,
  quoteRate			DOUBLE,
  responseTime		DATE,
  user_id			INTEGER,
  bank_id			INTEGER
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS bank (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bankName			VARCHAR(30),
  minTerm			INTEGER,
  maxTerm			INTEGER,
  minAmount			DOUBLE,
  maxAmount			DOUBLE,
  minConsumerRate	INTEGER,
  maxConsumerRate	INTEGER,
  bankType			VARCHAR(30),
  jmsChannel		VARCHAR(30)
) engine=InnoDB;