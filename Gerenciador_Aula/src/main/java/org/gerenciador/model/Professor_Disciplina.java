package org.gerenciador.model;

public class Professor_Disciplina {
    private int idProfessor;
    private int idDisciplina;

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    @Override
    public String toString() {
        return "Professor_Disciplina{" +
                "idDisciplina=" + idDisciplina +
                ", idProfessor=" + idProfessor +
                '}';
    }
}
