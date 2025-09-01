package cad.afc.cad.api.aluno;


import cad.afc.cad.api.faltas.FaltaRepository;
import cad.afc.cad.api.notas.Nota;
import cad.afc.cad.api.notas.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private NotaRepository notaRepository;


    @Autowired
    private FaltaRepository faltaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public SituacaoAlunoDTO verificarAprovacao(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID: " + alunoId));

        List<Nota> notasDoAluno = notaRepository.findByAlunoId(alunoId);

        double mediaFinal = calcularMediaFinal(notasDoAluno);

        long totalFaltas = faltaRepository.countByAlunoId(alunoId);

        final double MEDIA_MINIMA = 7.0;
        final long LIMITE_FALTAS = 10;

        boolean aprovado = true;
        String motivoReprovacao = "";

        if (mediaFinal < MEDIA_MINIMA) {
            aprovado = false;
            motivoReprovacao += "Reprovado por nota. ";
        }

        if (totalFaltas > LIMITE_FALTAS) {
            aprovado = false;
            motivoReprovacao += "Reprovado por faltas.";

        }

        return new SituacaoAlunoDTO(
                aluno, // Pass the entire 'aluno' object as the first parameter
                (int) totalFaltas,
                mediaFinal,
                aprovado,
                aprovado ? "Aprovado" : motivoReprovacao.trim(),
                notasDoAluno
        );
    }

    private double calcularMediaFinal(List<Nota> notas) {
        if (notas.isEmpty()) {
            return 0.0;
        }
        return notas.stream()
                .mapToDouble(Nota::getMedia)
                .average()
                .orElse(0.0);
    }
}