package br.edu.ifpb.es.daw.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID lookupId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer ano;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }
}
