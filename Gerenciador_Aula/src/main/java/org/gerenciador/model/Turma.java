package org.gerenciador.model;

public class Turma {
    private int id;
    private String nome;
    private Integer ano;


    public Turma(int id, String nome, Integer ano) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public Integer getAno() { return ano; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setAno(Integer ano) { this.ano = ano; }

    @Override
    public String toString() {
        return "Turma{id=" + id + ", nome='" + nome + "', ano=" + ano + "}";
    }
}