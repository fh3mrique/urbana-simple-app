CREATE TABLE tb_boarding_pass
(
    id                 BIGINT PRIMARY KEY,
    number             BIGINT,
    status             BOOLEAN DEFAULT TRUE,
    type_boarding_pass VARCHAR(255) NOT NULL
);