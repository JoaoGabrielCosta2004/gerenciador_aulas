package br.edu.ifpb.es.daw.entities;


public class Nota {

    private Long id;
    private String descricao;
    private Double valor;

    private Integer id_aluno;

    private Integer id_disciplina;

    public Nota(){

    }

    public Integer getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(Integer id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public Integer getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Integer id_aluno) {
        this.id_aluno = id_aluno;
    }

    public Nota(Long id, String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public Double getValor() { return valor; }


    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setValor(Double valor) { this.valor = valor; }


    @Override
    public String toString() {
        return "Nota{id=" + id + ", descricao='" + descricao + "', valor=" + valor + "}";
    }
}