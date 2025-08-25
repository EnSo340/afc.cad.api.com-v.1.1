// Na entidade Faltas.java
package cad.afc.cad.api.faltas;

import cad.afc.cad.api.aluno.Aluno;
import cad.afc.cad.api.materia.Materia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Faltas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private int quantidade;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Materia materia;


    public Faltas(Long id, LocalDate data, int quantidade, Aluno aluno, Materia materia) {
        this.id = id;
        this.data = data;
        this.quantidade = quantidade;
        this.aluno = aluno;
        this.materia = materia;
    }

    public Faltas(Object o, LocalDate data, int quantidade, Aluno aluno) {
    }
}