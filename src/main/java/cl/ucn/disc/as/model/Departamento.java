package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Departamento extends BaseModel {

    @Getter
    @NotNull
    private int numero;

    @Getter
    @NotNull
    private int piso;

    @Getter
    @ManyToOne
    private Edificio edificio;

    @OneToOne
    private Contrato contrato;
}
