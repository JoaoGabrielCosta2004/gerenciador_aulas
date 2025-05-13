package br.edu.ifpb.es.daw.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aula {
    private Long id;
    private Integer quantidadeFalta;
    private LocalDate data;
    private String conteudo;

    // Construtor padrão
    public Aula() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidadeFalta() {
        return quantidadeFalta;
    }

    public void setQuantidadeFalta(Integer quantidadeFalta) {
        this.quantidadeFalta = quantidadeFalta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", quantidadeFalta=" + quantidadeFalta +
                ", data=" + data +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}
