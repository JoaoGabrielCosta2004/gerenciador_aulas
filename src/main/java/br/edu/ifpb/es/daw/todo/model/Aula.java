package br.edu.ifpb.es.daw.todo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidadeFalta = 0;

    private LocalDate data;

    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany
    @JoinTable(
            name = "aula_aluno",
            joinColumns = @JoinColumn(name = "aula_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private Set<Aluno> alunos = new HashSet<>();

    @PrePersist
    private void init() {
        if (this.quantidadeFalta == null) this.quantidadeFalta = 0;
    }
    // Setter que aceita LocalDate diretamente
    public void setData(LocalDate data) {
        this.data = data;
    }
    // Setter auxiliar que aceita String no formato dd/MM/yyyy
    public void setData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);
    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null) {
            this.alunos.add(aluno);
            aluno.getAulas().add(this); // mantém a relação bidirecional
        }
    }
}
