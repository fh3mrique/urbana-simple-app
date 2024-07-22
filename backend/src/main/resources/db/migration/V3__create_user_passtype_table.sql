CREATE TABLE tb_user_passtype
(
    user_id     BIGINT NOT NULL,
    passtype_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, passtype_id),
    FOREIGN KEY (user_id) REFERENCES tb_users (id) ON DELETE CASCADE,
    FOREIGN KEY (passtype_id) REFERENCES tb_boarding_pass (id) ON DELETE CASCADE
);