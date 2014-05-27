INSERT INTO users VALUES (1,'Christian','Henle','chris','$2a$10$WVPq/GcSLNcKECb.wIfGeuFD0etGZpkgh6cgxdzmdzlBGdgp7Jzh2','christian@test.de','Test GmbH','male','Test Town','asdfstreet 1','33333','016011111111');
INSERT INTO users VALUES (2,'Oliver','Roevekamp','oliverr','$2a$10$5GMyJxnb8405GyQ3rzhrQuke8DTtEoKJCq9Fhf8Y41zluFhPZa0Qi','asdf@asdf.de','asdf GmbH','male','asdftown','asdfstreet 1','33333','016011111111');
INSERT INTO authority VALUES (1,'ROLE_CUSTOMER');
INSERT INTO user_authority VALUES (1,1);
INSERT INTO user_authority VALUES (2,1);