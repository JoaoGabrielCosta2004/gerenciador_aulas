package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ProjetoGrande extends Projeto {

    private BigDecimal orcamento;

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        ProjetoGrande that = (ProjetoGrande) o;
        return Objects.equals(orcamento, that.orcamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orcamento);
    }

    @Override
    public String toString() {
        return super.toString() + ", ProjetoGrande{" +
                "orcamento=" + orcamento +
                '}';
    }
}
