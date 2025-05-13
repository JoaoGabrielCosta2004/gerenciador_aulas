package br.edu.ifpb.es.daw.entities;

public class Turma {
    private Long id;
    private String nome;
    private Integer ano;

    // Construtor com parâmetros
    public Turma(Long id, String nome, Integer ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }

    // Construtor padrão (sem parâmetros)
    public Turma() {
    }

    // Getters e setters
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
}
