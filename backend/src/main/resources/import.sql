-- Inserir dados na tabela tb_users
INSERT INTO tb_users (name, email, password, user_role) VALUES ('John Doe', 'john.doe@example.com', '$2a$12$/xenxVn7vzKd0DgzPwhr1uurZUQzU9FADL4tma/mPcPuA28Gu2M8S', 'admin');
INSERT INTO tb_users (name, email, password, user_role) VALUES ('Jane Smith', 'jane.smith@example.com', '$2a$12$/xenxVn7vzKd0DgzPwhr1uurZUQzU9FADL4tma/mPcPuA28Gu2M8S', 'admin');

-- Inserir dados na tabela tb_boarding-pass
INSERT INTO tb_boarding_pass (id, number, status, type_boarding_pass) VALUES (1, 1001, TRUE, 'COMUM');

INSERT INTO tb_boarding_pass (id, number, status, type_boarding_pass) VALUES (2, 1002, FALSE, 'ESTUDANTE');

INSERT INTO tb_boarding_pass (id, number, status, type_boarding_pass) VALUES (3, 1003, TRUE, 'TRABALHADOR');

INSERT INTO tb_boarding_pass (id, number, status, type_boarding_pass) VALUES (4, 1004, TRUE, 'COMUM');

INSERT INTO tb_boarding_pass (id, number, status, type_boarding_pass) VALUES(5, 1005, FALSE, 'ESTUDANTE');

INSERT INTO tb_boarding_pass (id, number, status, type_boarding_pass) VALUES(6, 1006, TRUE, 'TRABALHADOR');

-- Inserir dados na tabela de junção tb_user_passtype
INSERT INTO tb_user_passtype (user_id, passtype_id) VALUES (1, 1); -- John Doe tem um BoardingPassType 'Economy'
INSERT INTO tb_user_passtype (user_id, passtype_id) VALUES (1, 2); -- John Doe também tem um BoardingPassType 'Business'
INSERT INTO tb_user_passtype (user_id, passtype_id) VALUES (2, 3); -- Jane Smith tem um BoardingPassType 'First Class'
INSERT INTO tb_user_passtype (user_id, passtype_id) VALUES (2, 1); -- Jane Smith tem um BoardingPassType 'First Class'
