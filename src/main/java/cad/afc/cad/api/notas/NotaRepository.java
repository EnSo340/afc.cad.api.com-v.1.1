package cad.afc.cad.api.notas;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import cad.afc.cad.api.notas.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByAlunoId(Long alunoId);
}