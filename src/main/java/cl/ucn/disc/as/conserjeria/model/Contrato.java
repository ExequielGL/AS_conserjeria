package cl.ucn.disc.as.conserjeria.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

/**
 * Contrato Class.
 *
 * @author Exequiel Gonzalez.
 */
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {

    /**
     * The FechaPago.
     */
    @NotNull
    @Getter
    private Instant fechaPago;

    /**
     * The Persona.
     */
    @ManyToOne
    @Getter
    private Persona persona;

    /**
     * The Departamento.
     */
    @ManyToOne
    @Getter
    private Departamento departamento;

    /**
     * The Pagos.
     */
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    /**
     * Metodo para añadir un pago.
     *
     * @param pago pago a añadir.
     */
    public void addPago(final Pago pago) {
        this.pagos.add(pago);
        persona.addContrato(this);
    }
}
