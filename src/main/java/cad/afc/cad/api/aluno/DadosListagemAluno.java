package cad.afc.cad.api.aluno;

public record DadosListagemAluno(
        Long id,
        String nome,
        int anoDeNasc,
        String serieAtual,
        String email,
        String telefone,
        String cpf

) {
    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getId(),
                aluno.getNome(),
                aluno.getAnoDeNasc(),
                aluno.getSerieAtual(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getCpf()

        );
    }
}
