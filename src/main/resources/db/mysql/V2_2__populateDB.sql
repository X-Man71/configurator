INSERT INTO users VALUES (1,'Christian','Henle','christian','$2a$10$WA/gJRl61iVg6MbXSmvRNO3CqQm0gjSd5kllBhfca/2TaeUh4XiAy','christian@test.de',0,'x','2014-05-01');
INSERT INTO users VALUES (2,'Oliver','Roevekamp','oliver','$2a$10$8bokICydBJXRRvKLBJbNFe85t3gMyKkcwHs1Jy79NNTmbFXgN3CMW','asdf@asdf.de',0,'x','2014-05-01');
INSERT INTO authority VALUES (1,'ROLE_CUSTOMER');
INSERT INTO user_authority VALUES (1,1);
INSERT INTO user_authority VALUES (2,1);

INSERT INTO bank VALUES (1, 'kein Angebot', 0, 0, 0, 1000000, 0, 0, 'syncron', 'default');
INSERT INTO bank VALUES (2, 'Bank A', 5, 12, 500, 10000, 9600, 9999, 'asyncron', 'requestBankA');
INSERT INTO bank VALUES (3, 'Bank B', 1, 24, 100, 10000, 9700, 9999, 'asyncron', 'requestBankB');
INSERT INTO bank VALUES (4, 'VBank', 0, 36, 0, 1000000, 500, 9999, 'syncron', 'default');
