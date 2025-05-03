package br.edu.ifpb.es.daw.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;


    public Disciplina(int id, String nome) {
        this.id = id;
        this.nome = nome;

    }

    public int getId() { return id; }
    public String getNome() { return nome; }


    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }


    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}