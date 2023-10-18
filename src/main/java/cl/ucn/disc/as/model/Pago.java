package cl.ucn.disc.as.model;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

/**
 * Pago Class.
 *
 * @author Exequiel Gonzalez.
 */
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Pago extends Model {

    /**
     * The FechaPago.
     */
    @Getter
    @NotNull
    private Instant fechaPago;

    /**
     * The Monto.
     */
    @Getter
    @NotNull
    private Integer monto;

    /**
     * The Contrato.
     */
    @ToString.Exclude
    @ManyToOne
    private Contrato contrato;

}
