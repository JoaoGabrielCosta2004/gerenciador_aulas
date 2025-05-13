package br.edu.ifpb.es.daw.entities;


import jakarta.persistence.*;


public class Nota {

    private Long id;
    private String descricao;
    private Double valor;
  
    public Nota(){

    }

    public Nota(Long id, String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public Double getValor() { return valor; }


    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setValor(Double valor) { this.valor = valor; }


    @Override
    public String toString() {
        return "Nota{id=" + id + ", descricao='" + descricao + "', valor=" + valor + "}";
    }
}