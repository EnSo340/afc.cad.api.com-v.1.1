package cad.afc.cad.api.aluno;

import java.util.List;
import java.util.stream.Collectors;
import cad.afc.cad.api.aluno.Aluno;
import cad.afc.cad.api.notas.Nota;
import cad.afc.cad.api.notas.DadosDetalhamentoNotas;

public record SituacaoAlunoDTO(
        Long alunoId,
        String nomeAluno,
        boolean aprovado,
        Double mediaFinal,
        Integer faltasTotais,
        String motivoReprovacao,
        List<DadosDetalhamentoNotas> notas
) {
    public SituacaoAlunoDTO(Aluno aluno, Integer faltasTotais, Double mediaFinal, boolean aprovado, String motivoReprovacao, List<Nota> notasDoAluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aprovado,
                mediaFinal,
                faltasTotais,
                motivoReprovacao,
                notasDoAluno.stream()
                        .map(nota -> new DadosDetalhamentoNotas(nota, aprovado ? "Aprovado" : motivoReprovacao))
                        .collect(Collectors.toList())
        );
    }
}