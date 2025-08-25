package cad.afc.cad.api.materia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int aulasPorSemana; // 2 ou 4


    public Materia(DadosCadastroMateria dados) {
        this.nome = dados.nome();
        this.aulasPorSemana = dados.aulasPorSemana();
    }
}