package br.edu.ifpb.es.daw.entities;

public class Material {

    private Long id;

    private String tipo;
    private String titulo;
    private String link;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                '}';
    }
}
