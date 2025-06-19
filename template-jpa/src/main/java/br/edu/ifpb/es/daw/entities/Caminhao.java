package br.edu.ifpb.es.daw.entities;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Caminhao extends VeiculoDeTransporte {

    private Integer numeroDeVagões;

    public Integer getNumeroDeVagões() { return numeroDeVagões; }
    public void setNumeroDeVagões(Integer numeroDeVagões) { this.numeroDeVagões = numeroDeVagões; }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Caminhao caminhao = (Caminhao) o;
        return Objects.equals(numeroDeVagões, caminhao.numeroDeVagões);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroDeVagões);
    }

    @Override
    public String toString() {
        return super.toString() + ", Caminhao{" +
                "numeroDeVagões=" + numeroDeVagões +
                '}';
    }
}
