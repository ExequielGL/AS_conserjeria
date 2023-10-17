package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {


    @NotNull
    @Getter
    private Instant fechaPago;


    @ManyToOne
    @Getter
    private Persona persona;


    @ManyToOne
    @Getter
    private Departamento departamento;

    @OneToMany(mappedBy = "contrato")
    private List<Pago> pagos;

}
