package org.gerenciador.model;

public class Aluno_Aula {
    private int idAula;
    private int idAluno;
    private int faltas;

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    @Override
    public String toString() {
        return "Aluno_Aula{" +
                "faltas=" + faltas +
                ", idAula=" + idAula +
                ", idAluno=" + idAluno +
                '}';
    }
}
