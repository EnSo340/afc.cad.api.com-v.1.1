CREATE TABLE faltas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    quantidade INT NOT NULL,

    aluno_id BIGINT NOT NULL,
    CONSTRAINT fk_faltas_aluno FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);
