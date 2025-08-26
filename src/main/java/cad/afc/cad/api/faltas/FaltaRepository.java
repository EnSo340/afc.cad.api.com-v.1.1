package cad.afc.cad.api.faltas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaltaRepository extends JpaRepository<Falta, Long> {
    long countByAlunoId(Long alunoId);
}
