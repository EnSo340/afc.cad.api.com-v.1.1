package cad.afc.cad.api.boletins;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoletimRepository extends JpaRepository<Boletim, Long> {

    List<Boletim> findByAlunoId(Long alunoId);
}