package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class EmpregadoTempoParcial extends Empregado {

    private BigDecimal valorHora;

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        EmpregadoTempoParcial that = (EmpregadoTempoParcial) o;
        return Objects.equals(valorHora, that.valorHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), valorHora);
    }

    @Override
    public String toString() {
        return super.toString() + ", EmpregadoTempoParcial{" +
                "valorHora=" + valorHora +
                '}';
    }
}
