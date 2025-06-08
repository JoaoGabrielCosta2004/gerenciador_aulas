package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidadeFalta = 0;

    private LocalDate data;

    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @ManyToMany(mappedBy = "aulas")
    private Set<Aluno> alunos = new HashSet<>();

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidadeFalta() {
        return quantidadeFalta;
    }

    public void setQuantidadeFalta(Integer quantidadeFalta) {
        this.quantidadeFalta = quantidadeFalta;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        if (aluno != null) {
            this.alunos.add(aluno);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(id, aula.id) && Objects.equals(quantidadeFalta, aula.quantidadeFalta) && Objects.equals(data, aula.data) && Objects.equals(conteudo, aula.conteudo) && Objects.equals(professor, aula.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidadeFalta, data, conteudo, professor);
    }

    @Override
    public String toString() {
        return "Aula{" +
                "conteudo='" + conteudo + '\'' +
                ", id=" + id +
                ", quantidadeFalta=" + quantidadeFalta +
                ", data='" + data + '\'' +
                ", professor=" + professor.getNome() +
                '}';
    }
}
