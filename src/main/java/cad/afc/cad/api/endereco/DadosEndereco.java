package cad.afc.cad.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String bairro,
        @NotBlank
        String logradouro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotBlank
        String complemento,
        @NotBlank
        String numero ) {
}
