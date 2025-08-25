CREATE TABLE materias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    aulas_por_semana INT NOT NULL
);

ALTER TABLE faltas ADD COLUMN materia_id BIGINT;

ALTER TABLE faltas ADD CONSTRAINT fk_faltas_materia FOREIGN KEY (materia_id) REFERENCES materias(id);