package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ProjetoPequeno extends Projeto {

    private LocalDate prazoMaximoConclusao;

    public LocalDate getPrazoMaximoConclusao() {
        return prazoMaximoConclusao;
    }

    public void setPrazoMaximoConclusao(LocalDate prazoMaximoConclusao) {
        this.prazoMaximoConclusao = prazoMaximoConclusao;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        ProjetoPequeno that = (ProjetoPequeno) o;
        return Objects.equals(prazoMaximoConclusao, that.prazoMaximoConclusao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prazoMaximoConclusao);
    }

    @Override
    public String toString() {
        return super.toString() + ", ProjetoPequeno{" +
                "prazoMaximoConclusao=" + prazoMaximoConclusao +
                '}';
    }
}
