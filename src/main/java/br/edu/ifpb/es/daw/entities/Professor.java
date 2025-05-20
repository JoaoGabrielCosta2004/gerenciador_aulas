package br.edu.ifpb.es.daw.entities;

public class Professor {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Professor(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Professor() {
    }

    // Getters e setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "Professor{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}
