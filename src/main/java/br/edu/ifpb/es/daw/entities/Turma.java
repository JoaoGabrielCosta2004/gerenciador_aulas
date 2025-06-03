package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer ano;

    public Turma(){
    }

    public Turma(Long id, String nome, Integer ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getAno() { return ano; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setAno(Integer ano) { this.ano = ano; }

    @Override
    public String toString() {
        return "Turma{id=" + id + ", nome='" + nome + "', ano=" + ano + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id) && Objects.equals(nome, turma.nome) && Objects.equals(ano, turma.ano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, ano);
    }
}