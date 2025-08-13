package br.edu.ifpb.es.daw.todo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private UUID lookupId;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String titulo;

    private String link;

    @ManyToOne
    @JoinColumn(name = "aula_id", referencedColumnName = "id")
    private Aula aula;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }
}
