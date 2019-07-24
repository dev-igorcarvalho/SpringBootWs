-- o spring roda no startup as sqls desse arquivo automaticamente
INSERT INTO springBoot.usuario(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$TDvDs8/RQCKGRzrQVZmVZeIQr8K6WZTxtwN/Fipe.GFuNJVlzm11u');

INSERT INTO springBoot.cursos(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO springBoot.cursos(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);
INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 4', 'Usando Pageable', '2019-06-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 5', 'Usando @EnableSpringDataWebSupport', '2019-05-07 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 6', 'Testando novidades do paginator', '2019-07-07 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 7', 'Refatorando o paginator', '2019-08-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO springBoot.topicos(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 8', 'Qual o proximo modulo', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
