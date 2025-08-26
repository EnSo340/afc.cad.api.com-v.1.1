package cad.afc.cad.api.aluno;

import cad.afc.cad.api.boletins.Boletim;
import cad.afc.cad.api.boletins.BoletimDTO;
import cad.afc.cad.api.boletins.BoletimRepository;
import cad.afc.cad.api.faltas.FaltaRepository;
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
    private BoletimRepository boletimRepository;

    @Autowired
    private FaltaRepository faltaRepository;

    @Autowired
    private AlunoRepository alunoRepository;
    public SituacaoAlunoDTO verificarAprovacao(Long alunoId) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID: " + alunoId));


        List<Boletim> boletinsDoAluno = boletimRepository.findByAlunoId(alunoId);
        List<BoletimDTO> boletinsDTO = boletinsDoAluno.stream()
                .map(BoletimDTO::new)
                .collect(Collectors.toList());

        double mediaFinal = calcularMediaFinal(boletinsDoAluno);


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
                aluno.getId(),
                aluno.getNome(),
                aprovado,
                mediaFinal,
                (int) totalFaltas,
                aprovado ? "Aprovado" : motivoReprovacao.trim(),
                boletinsDTO
        );
    }

    private double calcularMediaFinal(List<Boletim> boletins) {
        if (boletins.isEmpty()) {
            return 0.0;
        }
        return boletins.stream()
                .mapToDouble(Boletim::getMedia)
                .average()
                .orElse(0.0);
    }
}