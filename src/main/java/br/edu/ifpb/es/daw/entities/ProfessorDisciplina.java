package br.edu.ifpb.es.daw.entities;

public class ProfessorDisciplina {
    private Long professorId;
    private Long disciplinaId;


    public ProfessorDisciplina() {}

    public ProfessorDisciplina(Long id, Long professorId, Long disciplinaId) {
        this.professorId = professorId;
        this.disciplinaId = disciplinaId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}
