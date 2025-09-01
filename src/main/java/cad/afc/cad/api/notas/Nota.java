package cad.afc.cad.api.notas;

import cad.afc.cad.api.aluno.Aluno;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore

    private Aluno aluno;


    public Nota(Double p1, Double p2, Double atv, Aluno aluno) {
        this.p1 = p1;
        this.p2 = p2;
        this.atv = atv;
        this.aluno = aluno;
        this.calcularMedia();
    }
    @PrePersist
    @PreUpdate
    private void calcularMedia() {
        if (this.p1 != null && this.p2 != null && this.atv != null) {
            this.mediaFinal = (p1 + p2 + atv) / 3;
        }
    }

    public Double getMedia() {
        return (p1 + p2 + atv) / 3;
    }
}