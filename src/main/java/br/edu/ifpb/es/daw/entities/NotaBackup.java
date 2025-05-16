package br.edu.ifpb.es.daw.entities;

import jakarta.json.bind.annotation.JsonbProperty;

public class NotaBackup {
    private Long id;
    private String nota;
    private String alunoNome;
    private String alunoMatricula;
    private String disciplinaNome;

    // Construtor
    public NotaBackup(Long id,String nota, String alunoNome, String alunoMatricula, String disciplinaNome) {
        this.id = id;
        this.nota = nota;
        this.alunoNome = alunoNome;
        this.alunoMatricula = alunoMatricula;
        this.disciplinaNome = disciplinaNome;
    }

    // Getters e Setters
    @JsonbProperty("nota")
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(String alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    @JsonbProperty("notabackup_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}