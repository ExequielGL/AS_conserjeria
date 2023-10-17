package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Builder
@Entity
@Getter
public class Departamento extends BaseModel {

    @NotNull
    private int numero;

    @NotNull
    private int piso;

    @ManyToOne
    private Edificio edificio;

    @Override
    public String toString() {
        String edificioNombre = (edificio != null) ? edificio.getNombre() : "N/A";
        return "Departamento{" +
                "super=" + super.toString() + // Llama al método toString() de BaseModel
                ", id=" + getId() +
                ", numero=" + numero +
                ", piso=" + piso +
                ", edificio=" + edificioNombre +
                '}';
    }
}
