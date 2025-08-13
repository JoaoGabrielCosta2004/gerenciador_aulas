package br.edu.ifpb.es.daw.app.service;

public class UsuarioLogado {
    private Long id;
    private String tipo;

    public UsuarioLogado(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public String getTipo() { return tipo; }
}