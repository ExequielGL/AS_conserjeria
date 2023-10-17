package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Pago extends BaseModel {

    @Getter
    @NotNull
    private Instant fechaPago;

    @Getter
    @NotNull
    private Integer monto;

    @ManyToOne
    private Contrato contrato;

}
