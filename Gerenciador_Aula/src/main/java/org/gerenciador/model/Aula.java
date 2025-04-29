package org.gerenciador.model;

@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantidadeFalta;

    @Column(name = "data_aula")
    private LocalDate data;

    private String conteudo;


    private int idProfessor;

    private int idAluno_aula;

    private  int idAula_turma;
    // faltou professor e materials que n ta expecificado


    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAluno_aula() {
        return idAluno_aula;
    }

    public void setIdAluno_aula(int idAluno_aula) {
        this.idAluno_aula = idAluno_aula;
    }

    public int getIdAula_turma() {
        return idAula_turma;
    }

    public void setIdAula_turma(int idAula_turma) {
        this.idAula_turma = idAula_turma;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getQuantidadeFalta() {
        return quantidadeFalta;
    }

    public void setQuantidadeFalta(int quantidadeFalta) {
        this.quantidadeFalta = quantidadeFalta;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "conteudo='" + conteudo + '\'' +
                ", id=" + id +
                ", quantidadeFalta=" + quantidadeFalta +
                ", data='" + data + '\'' +
                ", idProfessor=" + idProfessor +
                ", idAluno_aula=" + idAluno_aula +
                ", idAula_turma=" + idAula_turma +
                '}';
    }
}
