package org.gerenciador.model;

@Entity
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;


    private int idProfessor_disciplina;

    public Disciplina(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.idProfessor_disciplina = idProfessor_disciplina;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdProfessor_disciplina() {return idProfessor_disciplina;}

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdProfessor_disciplina(int idProfessor_disciplina) {this.idProfessor_disciplina = idProfessor_disciplina;}

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idProfessor_disciplina=" + idProfessor_disciplina +
                '}';
    }
}