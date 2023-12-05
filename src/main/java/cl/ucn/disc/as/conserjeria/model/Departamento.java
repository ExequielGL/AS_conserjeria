package cl.ucn.disc.as.conserjeria.model;

import io.ebean.annotation.NotNull;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Departamento Class.
 *
 * @author Exequiel Gonzalez.
 */
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Departamento extends BaseModel {

    /**
     * The Numero.
     */
    @NotNull
    private Integer numero;

    /**
     * The Piso.
     */
    @NotNull
    private Integer piso;

    /**
     * The Edificio.
     */
    @ToString.Exclude
    @ManyToOne
    @Setter
    private Edificio edificio;

}
