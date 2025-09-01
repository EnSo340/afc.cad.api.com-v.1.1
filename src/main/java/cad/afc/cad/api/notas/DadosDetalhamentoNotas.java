package cad.afc.cad.api.notas;

public record DadosDetalhamentoNotas(Long id, Double p1, Double p2, Double atv, Double media, String situacaoDoAluno) {
    public DadosDetalhamentoNotas(Nota nota, String situacaoDoAluno) {
        this(nota.getId(), nota.getP1(), nota.getP2(), nota.getAtv(), nota.getMediaFinal(), situacaoDoAluno);
    }
}