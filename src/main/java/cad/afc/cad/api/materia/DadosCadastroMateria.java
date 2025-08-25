package cad.afc.cad.api.materia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMateria(
        @NotBlank
        String nome,
        @NotNull
        int aulasPorSemana
) {
}