package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Entity
public class Edificio extends BaseModel {

    /**
     * The Nombre.
     */
    @NotNull
    private String nombre;

    /**
     * The Direccion.
     */
    @NotNull
    private String direccion;

    @OneToMany(mappedBy = "edificio")
    private List<Departamento> departamentos;

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "super=" + super.toString() + // Llama al método toString() de BaseModel
                ", id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }
}
