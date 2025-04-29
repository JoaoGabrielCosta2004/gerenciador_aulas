package org.gerenciador.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String matricula;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private int idTurma;
    private int idAlunoNota;

    //@ManyToOne // Professor que isso agr não
    //@JoinColumn(name = "id_aluno_aula") // Relacionamento com a tabela de Aulas
    private AlunoAula alunoAula;

    public Aluno() {
    }

    public Aluno(int id, String nome, String matricula, LocalDate dataNascimento, int idTurma, int idAlunoNota, AlunoAula alunoAula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.idTurma = idTurma;
        this.idAlunoNota = idAlunoNota;
        this.alunoAula = alunoAula;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public int getIdAlunoNota() {
        return idAlunoNota;
    }

    public void setIdAlunoNota(int idAlunoNota) {
        this.idAlunoNota = idAlunoNota;
    }

    public AlunoAula getAlunoAula() {
        return alunoAula;
    }

    public void setAlunoAula(AlunoAula alunoAula) {
        this.alunoAula = alunoAula;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", idTurma=" + idTurma +
                ", idAlunoNota=" + idAlunoNota +
                ", alunoAula=" + alunoAula +
                '}';
    }
}
