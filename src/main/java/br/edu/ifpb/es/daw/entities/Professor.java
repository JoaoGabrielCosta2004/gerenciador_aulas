package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.*;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;

    // Construtor com parâmetros
    public Professor(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Construtor padrão (sem parâmetros)
    public Professor() {
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "Professor{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}
