package br.edu.ifpb.es.daw.entities;

public class AlunoAula {

    private Long alunoId;
    private Long aulaId;
    private int faltas;

    public AlunoAula(Long alunoId, Long aulaId, int faltas) {
        this.alunoId = alunoId;
        this.aulaId = aulaId;
        this.faltas = faltas;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
