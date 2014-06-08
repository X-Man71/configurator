INSERT INTO users VALUES (1,'Christian','Henle','chris','$2a$10$WVPq/GcSLNcKECb.wIfGeuFD0etGZpkgh6cgxdzmdzlBGdgp7Jzh2','christian@test.de',0,'x','2014-05-01');
INSERT INTO users VALUES (2,'Oliver','Roevekamp','oliverr','$2a$10$5GMyJxnb8405GyQ3rzhrQuke8DTtEoKJCq9Fhf8Y41zluFhPZa0Qi','asdf@asdf.de',0,'x','2014-05-01');
INSERT INTO authority VALUES (1,'ROLE_CUSTOMER');
INSERT INTO user_authority VALUES (1,1);
INSERT INTO user_authority VALUES (2,1);

INSERT INTO bank VALUES (1, 'Bank A', 5, 12, 500, 10000, 9600, 9999, 'asyncron', 'requestBankA');
INSERT INTO bank VALUES (2, 'Bank B', 1, 24, 100, 10000, 9700, 9999, 'asyncron', 'requestBankB');
INSERT INTO bank VALUES (3, 'VBank', 0, 1000, 0, 1000000, 500, 9999, 'syncron', 'default');