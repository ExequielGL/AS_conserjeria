package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.ToString;
import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Edificio Class.
 *
 * @author Exequiel Gonzalez.
 */
@ToString(callSuper = true)
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

    /**
     * The Departamentos.
     */
    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<Departamento> departamentos;

    /**
     * Metodo para añadir un departamento al edificio.
     * @param departamento departamento a añadir.
     */
    public void addDepartamento(final Departamento departamento) {
        // TODO: Evitar los duplicados
        for (Departamento depa : this.departamentos) {
            if (depa.getPiso().equals(departamento.getPiso())
                    && depa.getNumero().equals(departamento.getNumero())) {
                throw new IllegalArgumentException("Edificio ya tiene departamento: "
                        + departamento.getNumero());
            }
        }
        this.departamentos.add(departamento);
        departamento.setEdificio(this);

    }

}
