
package cad.afc.cad.api.faltas;

import java.time.LocalDate;

public record DadosCadastroFalta(
        LocalDate data,
        int quantidade,
        Long alunoId,
        Long materiaId
) {}