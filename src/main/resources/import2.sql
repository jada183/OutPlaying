INSERT INTO `outplaying`.`user`(`name`, `surname`, `email`, `role`, `create_acount_date`)
VALUES('Jason', 'Franco', 'jada183.jf@gmail.com', 'ADMIN', '2019-05-03');

INSERT INTO `outplaying`.`user`(`name`, `surname`, `email`, `role`, `create_acount_date`)
VALUES('Danilo', 'Quintero', 'jasondq1933@gmail.com', 'USER', '2019-05-03');

INSERT INTO `outplaying`.`post`(`post_name`, `content_text`, `date`, `likes`, `id_user`)
VALUES('post prueba','esto es el post de prueba inicial', '2019-05-03', '0', '1');

INSERT INTO `outplaying`.`comment`(`content_text`, `likes`, `date`, `id_user`, `id_post`)
VALUES('comentario de prueba del primer post', '0', '2019-05-03', '2', '1');