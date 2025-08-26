package cad.afc.cad.api.aluno;

import cad.afc.cad.api.boletins.Boletim;
import cad.afc.cad.api.boletins.BoletimDTO;

import java.util.List;
import java.util.stream.Collectors;

public record SituacaoAlunoDTO(
        Long alunoId,
        String nomeAluno,
        boolean aprovado,
        Double mediaFinal,
        Integer faltasTotais,
        String motivoReprovacao,
        List<BoletimDTO> boletins
) {
    public SituacaoAlunoDTO(Aluno aluno, List<Boletim> boletins, Integer faltasTotais, Double mediaFinal, boolean aprovado, String motivoReprovacao) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aprovado,
                mediaFinal,
                faltasTotais,
                motivoReprovacao,
                boletins.stream()
                        .map(BoletimDTO::new)
                        .collect(Collectors.toList())
        );
    }
}