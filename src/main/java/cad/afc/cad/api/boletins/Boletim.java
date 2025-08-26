package cad.afc.cad.api.boletins;

import cad.afc.cad.api.aluno.Aluno;
import cad.afc.cad.api.materia.Materia;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boletins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Boletim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    private Double p1;
    private Double p2;
    private Double atv;

    // Use o enum para o tipo de avaliação
    @Enumerated(EnumType.STRING)
    private TipoDeEnsino.TipoAvaliacao tipoAvaliacao;


    private Integer periodo;

    public Boletim(BoletimDTO dados) {
    }

    public Double getMedia() {
        return (p1 + p2 + atv) / 3;
    }
}