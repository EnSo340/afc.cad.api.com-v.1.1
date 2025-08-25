package cad.afc.cad.api.aluno;


import cad.afc.cad.api.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAluno (

        @NotBlank
        String nome,
        @NotNull
        int anoDeNasc,
        @NotBlank
        String serieAtual,
        @Email
        @NotBlank
        String email,
        @NotNull
        String telefone,
        @NotBlank
        String cpf,
        @Valid
        @NotNull
        DadosEndereco endereco)







{
}
