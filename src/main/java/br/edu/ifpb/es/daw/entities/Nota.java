package br.edu.ifpb.es.daw.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) && Objects.equals(descricao, nota.descricao) && Objects.equals(valor, nota.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, valor);
    }
}