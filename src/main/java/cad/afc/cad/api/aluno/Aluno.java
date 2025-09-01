package cad.afc.cad.api.aluno;

import cad.afc.cad.api.aluno.DadosAtualizacaoAluno;
import cad.afc.cad.api.aluno.DadosCadastroAluno;
import cad.afc.cad.api.endereco.Endereco;
import cad.afc.cad.api.faltas.Falta;
import cad.afc.cad.api.notas.Nota;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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


    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Falta> faltas = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference   // ✅ resolve o loop
    private List<Nota> notas = new ArrayList<>();

    public Aluno(DadosCadastroAluno dadosAluno) {
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
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.email() != null) this.email = dados.email();
        if (dados.telefone() != null) this.telefone = dados.telefone();
        if (dados.serieAtual() != null) this.serieAtual = dados.serieAtual();
    }

    public void adicionarFalta(int quantidade) {
        this.totalFaltas += quantidade;
    }

    public boolean SistemaDeReprovacaoPorFaltas(int totalAulas) {
        int maxFaltas = (int) (totalAulas * 0.25);
        return totalFaltas > maxFaltas;
    }

    public boolean SistemaDeAprovacaoPorPresenca(int totalAulas) {
        int maxFaltas = (int) (totalAulas * 0.25);
        return totalFaltas <= maxFaltas;
    }

}
