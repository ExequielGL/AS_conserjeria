package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.EdificioNotFoundException;
import cl.ucn.disc.as.exceptions.PerDepNotFoundException;
import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Pago;
import cl.ucn.disc.as.model.Persona;
import io.ebean.Database;
import io.ebean.Query;
import java.time.Instant;
import java.util.List;
import javax.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * Sistema implementación.
 *
 * @author Exequiel Gonzalez
 */
@Slf4j
public class SistemaImpl implements Sistema {
    /**
     * Base de datos.
     */
    private final Database database;

    /**
     * The Constructor.
     * @param database Base de datos a utilizar.
     */
    public SistemaImpl(Database database) {
        this.database = database;
    }

    /**
     * Agrega un edificio al Sistema.
     * @param edificio edificio a agregar.
     * @return edificio agregado.
     */
    @Override
    public Edificio add(@NotNull Edificio edificio) {
        try {
            this.database.save(edificio);
        } catch (PersistenceException ex) {
            // TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
        //WARN: need to retrieve the id?
        return edificio;
    }

    /**
     * Agrega una persona al Sistema.
     * @param persona persona a agregar.
     * @return persona agregada.
     */
    @Override
    public Persona add(Persona persona) {
        try {
            this.database.save(persona);
        } catch (PersistenceException ex) {
            // TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
        return persona;
    }

    /**
     * Agrega un departamento a un edificio.
     * @param departamento departamento a agregar.
     * @param edificio edificio al que se le agregará un departamento.
     * @return departamento agregado.
     */
    @Override
    public Departamento addDepartamento(Departamento departamento, Edificio edificio) {
        log.debug("Adding {} to {}.", departamento, edificio);
        try {
            this.database.save(departamento);
            edificio.addDepartamento(departamento);
            this.database.save(edificio);
        } catch (PersistenceException ex) {
            // TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
        return departamento;
    }

    /**
     * Agrega un departamento a un edificio.
     * @param departamento departamento a agregar.
     * @param idEdificio Id del edificio al que se le agregará un departamento.
     * @return departamento agregado.
     */
    @Override
    public Departamento addDepartamento(Departamento departamento, Long idEdificio) {
        Edificio edificio = this.database.find(Edificio.class, idEdificio);
        if (edificio == null) {
            throw new EdificioNotFoundException("El edificio con ID " + idEdificio + " no existe");
        }
        return this.addDepartamento(departamento, edificio);
    }

    /**
     * Se agregará un contrato al Sistema.
     * @param duenio Persona a la que se vinculará el contrato.
     * @param departamento Departamento por el que se hace el contrato.
     * @param fechaPago Fecha en que se realiza el contrato.
     * @return contrato
     */
    @Override
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) {
        Contrato contrato = Contrato.builder()
                .fechaPago(fechaPago)
                .persona(duenio)
                .departamento(departamento)
                .build();

        Pago pago = Pago.builder()
                .fechaPago(fechaPago)
                .monto(0)  // Define el monto del pago, puedes establecerlo como desees
                .contrato(contrato)
                .build();

        try {
            contrato.addPago(pago);
            this.database.save(contrato);
        } catch (PersistenceException ex) {
            // TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al realizar un contrato", ex);
        }
        return contrato;
    }

    /**
     * Se agregará un contrato al Sistema.
     * @param idDuenio Id de la persona a la que se vinculará el contrato.
     * @param idDepartamento Id del departamento por el que se hace el contrato.
     * @param fechaPago Fecha en que se realiza el contrato.
     * @return contrato
     */
    @Override
    public Contrato realizarContrato(Long idDuenio, Long idDepartamento, Instant fechaPago) {
        Persona duenio = this.database.find(Persona.class, idDuenio);
        Departamento departamento = this.database.find(Departamento.class, idDepartamento);
        if (duenio == null || departamento == null) {
            throw new PerDepNotFoundException("No se encontró la persona o el departamento");
        }
        return this.realizarContrato(duenio, departamento, fechaPago);
    }

    /**
     * Se obtienen los contratos en Sistema.
     * @return lista de contratos
     */
    @Override
    public List<Contrato> getContratos() {
        Query<Contrato> query = database.find(Contrato.class);
        return query.findList();
    }

    /**
     * Se obtienen las personas en Sistema.
     * @return lista de personas.
     */
    @Override
    public List<Persona> getPersonas() {
        Query<Persona> query = database.find(Persona.class);
        return query.findList();
    }

    /**
     * Se obtienen los pagos de cierta persona en sistema.
     * @param rut Rut de la persona.
     * @return lista de pagos
     */
    @Override
    public List<Pago> getPagos(String rut) {
        return database.find(Pago.class)
                .fetch("contrato.persona")
                .where()
                .eq("contrato.persona.rut", rut)
                .findList();
    }
}

