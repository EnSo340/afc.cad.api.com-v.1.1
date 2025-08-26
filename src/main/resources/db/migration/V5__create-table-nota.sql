CREATE TABLE notas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    p1 DOUBLE NOT NULL,
    p2 DOUBLE NOT NULL,
    atv DOUBLE,
    media_final DOUBLE,
    aluno_id BIGINT NOT NULL,

    CONSTRAINT fk_notas_aluno FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);
