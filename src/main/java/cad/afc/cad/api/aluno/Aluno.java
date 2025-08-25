package cad.afc.cad.api.aluno;

import cad.afc.cad.api.endereco.Endereco;
import cad.afc.cad.api.faltas.Faltas;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "aluno")
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    int anoDeNasc;
    String serieAtual;
    String email;
    String telefone;
    String cpf;
    int totalFaltas;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "aluno")
    private List<Faltas> faltas = new ArrayList<>();


    public Aluno(DadosCadastroAluno dadosAluno){
        this.nome = dadosAluno.nome();
        this.anoDeNasc = dadosAluno.anoDeNasc();
        this.email = dadosAluno.email();
        this.endereco = new Endereco(dadosAluno.endereco());
        this.telefone = dadosAluno.telefone();
        this.serieAtual = dadosAluno.serieAtual();
        this.cpf = dadosAluno.cpf();
        this.totalFaltas = 0;
    }


    public void atualizarInformacoes(DadosAtualizacaoAluno dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.serieAtual() != null) {
            this.serieAtual = dados.serieAtual();
        }
    }

    public void adicionarFalta(int quantidade) {
        this.totalFaltas += quantidade;
    }


    public boolean ReprovadoPorFaltas(int totalAulas) {
        int maxFaltas = (int) (totalAulas * 0.25); // 25%
        return getTotalFaltas() > maxFaltas;
    }
}