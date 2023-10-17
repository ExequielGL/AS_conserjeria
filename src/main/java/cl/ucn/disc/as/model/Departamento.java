package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Departamento extends BaseModel {

    @NotNull
    private Integer numero;

    @NotNull
    private Integer piso;

    @ToString.Exclude
    @ManyToOne
    @Setter
    private Edificio edificio;

}
