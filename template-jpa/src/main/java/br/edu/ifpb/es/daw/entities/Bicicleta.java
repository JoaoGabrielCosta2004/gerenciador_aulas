package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Bicicleta extends VeiculoDePassageiro {

    private Integer alturaDoSelimEmCm;

    public Integer getAlturaDoSelimEmCm() { return alturaDoSelimEmCm; }
    public void setAlturaDoSelimEmCm(Integer alturaDoSelimEmCm) { this.alturaDoSelimEmCm = alturaDoSelimEmCm; }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Bicicleta bicicleta = (Bicicleta) o;
        return Objects.equals(alturaDoSelimEmCm, bicicleta.alturaDoSelimEmCm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), alturaDoSelimEmCm);
    }

    @Override
    public String toString() {
        return super.toString() + ", Bicicleta{" +
                "alturaDoSelimEmCm=" + alturaDoSelimEmCm +
                '}';
    }
}
