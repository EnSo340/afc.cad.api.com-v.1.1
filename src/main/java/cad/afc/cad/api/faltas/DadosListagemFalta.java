package cad.afc.cad.api.faltas;

import java.time.LocalDate;

public record DadosListagemFalta(
        Long id,
        LocalDate data,
        Integer quantidade,
        String nomeAluno
) {
    public DadosListagemFalta(Falta falta) {
        this(
                falta.getId(),
                falta.getData(),
                falta.getQuantidade(),
                falta.getAluno().getNome()
        );
    }
}
