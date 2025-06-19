package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class EmpregadoTempoIntegral extends Empregado {

    private BigDecimal salario;

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        EmpregadoTempoIntegral that = (EmpregadoTempoIntegral) o;
        return Objects.equals(salario, that.salario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salario);
    }

    @Override
    public String toString() {
        return super.toString() + ", EmpregadoTempoIntegral{" +
                "salario=" + salario +
                '}';
    }
}
