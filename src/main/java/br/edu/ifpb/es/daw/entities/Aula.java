package br.edu.ifpb.es.daw.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidadeFalta;

    @Column(name = "data_aula")
    private LocalDate data;

    private String conteudo;


    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(String data) {
        this.data = LocalDate.parse(data);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                '}';
    }
}