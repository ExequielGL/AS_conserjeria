package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {

    @Getter
    @NotNull
    private Instant fechaPago;

    @Getter
    @OneToOne
    private Persona duenio;

    @Getter
    @OneToOne
    private Departamento departamento;

    @Getter
    @Builder.Default
    @OneToMany(mappedBy = "contrato")
    private List<Pago> pagos = new ArrayList<>();

}
