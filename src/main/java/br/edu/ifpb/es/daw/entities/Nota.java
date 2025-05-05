package br.edu.ifpb.es.daw.entities;


import jakarta.persistence.*;

@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private double valor;
  
    public Nota(){

    }

    public Nota(Long id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }


    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setValor(double valor) { this.valor = valor; }


    @Override
    public String toString() {
        return "Nota{id=" + id + ", descricao='" + descricao + "', valor=" + valor + "}";
    }
}