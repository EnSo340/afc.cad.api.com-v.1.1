package cad.afc.cad.api.notas;

import cad.afc.cad.api.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double p1;

    @Column(nullable = false)
    private Double p2;

    private Double atv;


    private Double mediaFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;


    public Nota(Double p1, Double p2, Double atv, Aluno aluno) {
        this.p1 = p1;
        this.p2 = p2;
        this.atv = atv;
        this.aluno = aluno;
        this.calcularMedia();
    }

    public void calcularMedia() {
        double p1Peso = (p1 != null ? p1 : 0) * 0.3;
        double p2Peso = (p2 != null ? p2 : 0) * 0.4;
        double atvPeso = (atv != null ? atv : 0) * 0.3;
        this.mediaFinal = p1Peso + p2Peso + atvPeso;
    }

    public boolean aprovadoPorNota() {

        return mediaFinal >= 6.0;
    }
}