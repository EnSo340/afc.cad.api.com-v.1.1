package cad.afc.cad.api.notas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<DadosDetalhamentoNotas> listarNotasPorAluno(Long alunoId) {
        return notaRepository.findByAlunoId(alunoId).stream()
                .map(nota -> new DadosDetalhamentoNotas(nota, obterSituacao(calcularMedia(nota))))
                .collect(Collectors.toList());
    }

    private double calcularMedia(Nota nota) {
        return (nota.getP1() + nota.getP2() + nota.getAtv()) / 3.0;
    }

    private String obterSituacao(double media) {
        if (media == 0.0) return "PENDENTE";
        if (media >= 7.0) return "APROVADO";
        return "REPROVADO";
    }
}