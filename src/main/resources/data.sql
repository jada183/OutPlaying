INSERT INTO  user(name, surname, email, role, create_acount_date) VALUES('Jason', 'Franco', 'jada183.jf@gmail.com', 'ADMIN', '2019-05-03');

INSERT INTO user(name, surname, email, role, create_acount_date)
VALUES('Danilo', 'Quintero', 'jasondq1933@gmail.com', 'USER', '2019-05-03');

INSERT INTO user_credentials(password, username, id_user)VALUES ('$2a$10$eEhAV11mSJBrNM3vRJHU5e7xFrLk.4R5wX9zL/lnuK0ggtJncOZjm', 'jason1993', '1');

INSERT INTO user_credentials(password, username, id_user) VALUES ('$2a$10$Yr6vgBnd7/Eg9OP6P/Mx9eLEInuOMfKCvXNEh5kxPkRF3SUQxuJC2', 'danilo1993', '2');
INSERT INTO post(post_name, content_text, date, likes, id_user)
VALUES('post prueba','esto es el post de prueba inicial', '2019-05-03', '0', '1');

INSERT INTO comment(content_text, likes, date, id_user, id_post)
VALUES('comentario de prueba del primer post', '0', '2019-05-03', '2', '1');