INSERT INTO users VALUES (1,'Christian','Henle','chris','$2a$10$WVPq/GcSLNcKECb.wIfGeuFD0etGZpkgh6cgxdzmdzlBGdgp7Jzh2','christian@test.de',1,'2014-05-01');
INSERT INTO users VALUES (2,'Oliver','Roevekamp','oliverr','$2a$10$5GMyJxnb8405GyQ3rzhrQuke8DTtEoKJCq9Fhf8Y41zluFhPZa0Qi','asdf@asdf.de',1,'2014-05-01');
INSERT INTO authority VALUES (1,'ROLE_CUSTOMER');
INSERT INTO user_authority VALUES (1,1);
INSERT INTO user_authority VALUES (2,1);