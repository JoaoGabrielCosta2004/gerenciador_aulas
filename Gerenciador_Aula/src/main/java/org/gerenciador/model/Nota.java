package org.gerenciador.model;

public class Nota {
    private int id;
    private String descricao;
    private double valor;

    public Nota(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;

    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }


    public void setId(int id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setValor(double valor) { this.valor = valor; }


    @Override
    public String toString() {
        return "Nota{id=" + id + ", descricao='" + descricao + "', valor=" + valor + "}";
    }
}