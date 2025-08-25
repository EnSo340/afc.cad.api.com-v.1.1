package cad.afc.cad.api.faltas;


import java.time.LocalDate;

public record DadosListagemFaltas(
        Long id,
        LocalDate data,
        int quantidade,
        Long alunoId
) {
    public DadosListagemFaltas(Faltas falta) {
        this(falta.getId(), falta.getData(), falta.getQuantidade(), falta.getAluno().getId());
    }
}