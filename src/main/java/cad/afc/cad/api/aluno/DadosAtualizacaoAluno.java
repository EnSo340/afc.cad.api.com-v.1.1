package cad.afc.cad.api.aluno;

import cad.afc.cad.api.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(

        @NotNull
        Long id,
        String nome,
        Integer anoDeNasc,
        String serieAtual,
        String email,
        String telefone,
        String cpf,
        @Valid
        DadosEndereco endereco
) { }
