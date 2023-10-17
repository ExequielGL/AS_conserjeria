package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Edificio extends BaseModel {

    /**
     * The Nombre.
     */
    @NotNull
    @Getter
    private String nombre;

    /**
     * The Direccion.
     */
    @NotNull
    @Getter
    private String direccion;

    @Builder.Default
    @Getter
    @OneToMany(mappedBy = "edificio")
    private List<Departamento> departamentos = new ArrayList<>();

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

}
