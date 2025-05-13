package br.edu.ifpb.es.daw.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.*;


public class Disciplina {
    private Long id;

    private String nome;

    public Disciplina(){
    }
  
    public Disciplina(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }


    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }


    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}