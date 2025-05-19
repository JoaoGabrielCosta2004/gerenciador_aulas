package br.edu.ifpb.es.daw.entities;
import org.bson.Document;

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

    //colocar esses métodos em uma classe de conexão separada!!!!!!
    public Document toDocument() {
        return new Document("nota", nota)
                .append("alunoNome", alunoNome)
                .append("alunoMatricula", alunoMatricula)
                .append("disciplinaNome", disciplinaNome)
                .append("id", id);
    }

    public static NotaBackup fromDocument(Document doc) {
        return new NotaBackup(
                doc.getLong("id"),
                doc.getString("nota"),
                doc.getString("alunoNome"),
                doc.getString("alunoMatricula"),
                doc.getString("disciplinaNome")
        );
    }
    //********************Até Aqui***********************************

    // Getters e Setters
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}