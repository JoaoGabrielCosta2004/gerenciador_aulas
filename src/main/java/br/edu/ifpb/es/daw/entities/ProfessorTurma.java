package br.edu.ifpb.es.daw.entities;

import java.util.Objects;

public class ProfessorTurma {
    private Long id;
    private Long professorId;
    private Long turmaId;

    // Construtores
    public ProfessorTurma() {}

    public ProfessorTurma(Long professorId, Long turmaId) {
        this.professorId = professorId;
        this.turmaId = turmaId;
    }

    public ProfessorTurma(Long id, Long professorId, Long turmaId) {
        this.id = id;
        this.professorId = professorId;
        this.turmaId = turmaId;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    @Override
    public String toString() {
        return "ProfessorTurma{" +
                "id=" + id +
                ", professorId=" + professorId +
                ", turmaId=" + turmaId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorTurma that = (ProfessorTurma) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(professorId, that.professorId) &&
                Objects.equals(turmaId, that.turmaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professorId, turmaId);
    }
}