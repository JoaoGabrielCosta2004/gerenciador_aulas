package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public abstract class VeiculoDePassageiro extends Veiculo {

    private Integer numeroMaximoPassageiros;

    public Integer getNumeroMaximoPassageiros() { return numeroMaximoPassageiros; }
    public void setNumeroMaximoPassageiros(Integer numeroMaximoPassageiros) {
        this.numeroMaximoPassageiros = numeroMaximoPassageiros;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        VeiculoDePassageiro that = (VeiculoDePassageiro) o;
        return Objects.equals(numeroMaximoPassageiros, that.numeroMaximoPassageiros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroMaximoPassageiros);
    }

    @Override
    public String toString() {
        return super.toString() + ", VeiculoDePassageiro{" +
                "numeroMaximoPassageiros=" + numeroMaximoPassageiros +
                '}';
    }
}
