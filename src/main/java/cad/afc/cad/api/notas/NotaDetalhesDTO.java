// Este DTO vai exibir os dados da nota E do aluno
package cad.afc.cad.api.notas;

public record NotaDetalhesDTO(
        Long id,
        Double p1,
        Double p2,
        Double atv,
        Double mediaFinal,
        Long alunoId,
        String nomeAluno
) {
    public NotaDetalhesDTO(Nota nota) {
        this(
                nota.getId(),
                nota.getP1(),
                nota.getP2(),
                nota.getAtv(),
                nota.getMediaFinal(),
                nota.getAluno().getId(),
                nota.getAluno().getNome()
        );
    }
}