package cad.afc.cad.api.notas;

import cad.afc.cad.api.materia.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}