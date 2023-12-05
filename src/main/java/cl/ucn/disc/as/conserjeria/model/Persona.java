/*
 * Copyright (c) 2023. Arquitectura de Sistemas, DISC, UCN.
 */

package cl.ucn.disc.as.conserjeria.model;

import cl.ucn.disc.as.conserjeria.model.exceptions.IllegalDomainException;
import cl.ucn.disc.as.utils.ValidationUtils;
import io.ebean.annotation.Cache;
import io.ebean.annotation.NotNull;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * The Persona class.
 *
 * @author Diego Urrutia-Astorga.
 */
@Cache(enableQueryCache = true, nearCache = true)

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Persona extends BaseModel {

    /**
     * The RUT.
     */
    @Getter
    @NotNull
    private String rut;

    /**
     * The Nombre.
     */
    @Getter
    @NotNull
    private String nombre;

    /**
     * The Apellidos.
     */
    @Getter
    @NotNull
    private String apellidos;

    /**
     * The Email.
     */
    @Getter
    @NotNull
    private String email;

    /**
     * The Telefono.
     */
    @Getter
    @NotNull
    private String telefono;

    /**
     * The Contratos.
     */
    @Setter
    @ToString.Exclude
    @OneToMany(mappedBy = "persona")
    private List<Contrato> contratos;

    /**
     * Metodo que vincula un contrato a una persona.
     * @param contrato Contrato a añadir.
     */
    public void addContrato(final Contrato contrato) {
        this.contratos.add(contrato);
    }
    /**
     * Custom builder to validate.
     */
    public static class PersonaBuilder {

        /**
         * @return the Persona.
         */
        public Persona build() {

            //validate the rut
            //if (!ValidationUtils.isRutValid((this.rut))) {
            //    throw new IllegalDomainException("RUT no valido: " + this.rut);
            //}

            //validate the email
            if (!ValidationUtils.isEmailValid(this.email)) {
                throw new IllegalDomainException("Email no valido: " + this.email);
            }

            this.contratos = new ArrayList<>();

            return new Persona(this.rut, this.nombre, this.apellidos,
                    this.email, this.telefono, this.contratos);
        }
    }

}
