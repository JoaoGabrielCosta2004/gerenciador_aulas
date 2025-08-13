package br.edu.ifpb.es.daw.entities;

public class Material {

    private Long id;
    private String tipo;
    private String titulo;
    private String link;
    private Boolean avaliacao;
    private Long id_aula;

    // Vai ter que mexer em material para adaptar pro formato que atenda o que precisa no MinIO
    public Material(){this.avaliacao = false;}

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

    public Boolean getAvaliacao() {
        return avaliacao;
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

    public Boolean isAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Boolean avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Long getId_aula() {
        return id_aula;
    }

    public void setId_aula(Long id_aula) {
        this.id_aula = id_aula;
    }
// @Override
    // public String toString() {
    //     return "Material{" +
    //             "id=" + id +
    //             ", tipo='" + tipo + '\'' +
    //             ", titulo='" + titulo + '\'' +
    //             ", link='" + link + '\'' +
    //             '}';
    // }
}
