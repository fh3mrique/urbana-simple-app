-- Inserir dados na tabela tb_users
INSERT INTO tb_users (name, email, password) VALUES ('John Doe', 'john.doe@example.com', 'password123');
INSERT INTO tb_users (name, email, password) VALUES ('Jane Smith', 'jane.smith@example.com', 'password456');

-- Inserir dados na tabela tb_boarding-pass
INSERT INTO tb_boarding_pass (id, type_board) VALUES (1, 'COMUM');
INSERT INTO tb_boarding_pass (id, type_board) VALUES (2, 'ESTUDANTE');
INSERT INTO tb_boarding_pass (id, type_board) VALUES (3, 'TRABALHADOR');

-- Inserir dados na tabela de junção tb_user_passtype
INSERT INTO tb_user_passtype (user_id, passtype_id) VALUES (1, 1); -- John Doe tem um BoardingPassType 'Economy'
INSERT INTO tb_user_passtype (user_id, passtype_id) VALUES (1, 2); -- John Doe também tem um BoardingPassType 'Business'
INSERT INTO tb_user_passtype (user_id, passtype_id) VALUES (2, 3); -- Jane Smith tem um BoardingPassType 'First Class'
