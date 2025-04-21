package org.gerenciador.model;

public class Material {
    private int id;
    private String tipo;
    private String titulo;
    private String link;
    private int idAula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", link='" + link + '\'' +
                ", idAula=" + idAula +
                '}';
    }
}
