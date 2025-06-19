package br.edu.ifpb.es.daw.entities;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Carro extends VeiculoDePassageiro {

    private Integer numeroDePortas;

    public Integer getNumeroDePortas() { return numeroDePortas; }
    public void setNumeroDePortas(Integer numeroDePortas) { this.numeroDePortas = numeroDePortas; }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Carro carro = (Carro) o;
        return Objects.equals(numeroDePortas, carro.numeroDePortas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroDePortas);
    }

    @Override
    public String toString() {
        return super.toString() + ", Carro{" +
                "numeroDePortas=" + numeroDePortas +
                '}';
    }
}
