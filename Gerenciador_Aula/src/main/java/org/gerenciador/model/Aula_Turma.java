package org.gerenciador.model;

public class Aula_Turma {
    private int idAula;
    private int idTurma;

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    @Override
    public String toString() {
        return "Aula_Turma{" +
                "idAula=" + idAula +
                ", idTurma=" + idTurma +
                '}';
    }
}
