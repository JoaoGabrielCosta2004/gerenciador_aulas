package br.edu.ifpb.es.daw.entities;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public abstract class VeiculoDeTransporte extends Veiculo {

    private Integer cargaMaximaKg;

    public Integer getCargaMaximaKg() { return cargaMaximaKg; }
    public void setCargaMaximaKg(Integer cargaMaximaKg) { this.cargaMaximaKg = cargaMaximaKg; }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        VeiculoDeTransporte that = (VeiculoDeTransporte) o;
        return Objects.equals(cargaMaximaKg, that.cargaMaximaKg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargaMaximaKg);
    }

    @Override
    public String toString() {
        return super.toString() + ", VeiculoDeTransporte{" +
                "cargaMaximaKg=" + cargaMaximaKg +
                '}';
    }
}
