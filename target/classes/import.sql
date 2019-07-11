INSERT INTO user(name, surname, email, role, create_acount_date) VALUES('Jason', 'Franco', 'jada183.jf@gmail.com', 'ADMIN', '2019-05-03');

INSERT INTO user(name, surname, email, role, create_acount_date) VALUES('Danilo', 'Quintero', 'jasondq1933@gmail.com', 'USER', '2019-05-03');

INSERT INTO user_credentials(password, username, id_user) VALUES('$2a$10$9NJCpFVtctuciVtWSHsGWeqL.KLTh4X1ePDEnGpL4BhjzWWIJNtsa', 'jason1993', '1'); 
INSERT INTO user_credentials(password, username, id_user) VALUES('$2a$10$DYAJD6fVnNOmqh.RtRcJEu0zWMzVPoz237mDq20rz9R0gCcW14nmG', 'danilo1993', '2');

INSERT INTO post(content_text, date, likes, manage_date, picture, post_name, status, id_user, id_user_manager) VALUES('esto es el post de prueba inicial', '2019-05-03', '0', '2019-05-03', 'postInicial.png', 'post prueba', '2', '2', '1');
INSERT INTO post(content_text, date, likes, manage_date, picture, post_name, status, id_user, id_user_manager) VALUES('esto es el post de prueba inicial', '2019-05-03', '0', '2019-05-03', 'segundoPost.png', 'post prueba2', '1', '2', '1');



INSERT INTO comment(content_text, likes, date, id_user, id_post) VALUES('comentario de prueba del primer post', '0', '2019-05-03', '2', '1');