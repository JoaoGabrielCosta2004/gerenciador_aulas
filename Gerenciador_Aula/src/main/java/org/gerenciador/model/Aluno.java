package org.gerenciador.model;

import java.time.LocalDate;

public class Aluno {
    private int id;
    private String nome;
    private String matricula;
    private LocalDate dataNascimento;
    private int idTurma;
    private int idAluno_nota;
    private int idAluno_aula;

    public Aluno(int id, String nome, String matricula, LocalDate dataNascimento, int idTurma, int idAluno_nota, int idAluno_aula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.idTurma = idTurma;
        this.idAluno_nota = idAluno_nota;
        this.idAluno_aula = idAluno_aula;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public int getIdTurma() { return idTurma; }
    public int getIdAluno_nota() {return idAluno_nota; }
    public int getIdAluno_aula() {return idAluno_aula;}

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setIdTurma(int idTurma) { this.idTurma = idTurma; }
    public void setIdAluno_aula(int idAluno_aula) {this.idAluno_aula = idAluno_aula;}
    public void setIdAluno_nota(int idAluno_nota) {this.idAluno_nota = idAluno_nota;}

    @Override
    public String toString() {
        return "Aluno{" +
                "dataNascimento=" + dataNascimento +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", idTurma=" + idTurma +
                ", idAluno_nota=" + idAluno_nota +
                ", idAluno_aula=" + idAluno_aula +
                '}';
    }
}