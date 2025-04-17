package org.gerenciador.model;

public class Nota {
    private int id;
    private String descricao;
    private double valor;
    private int idAluno;

    public Nota(int id, String descricao, double valor, int idAluno) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.idAluno = idAluno;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public int getIdAluno() { return idAluno; }

    public void setId(int id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setValor(double valor) { this.valor = valor; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }

    @Override
    public String toString() {
        return "Nota{id=" + id + ", descricao='" + descricao + "', valor=" + valor + "}";
    }
}