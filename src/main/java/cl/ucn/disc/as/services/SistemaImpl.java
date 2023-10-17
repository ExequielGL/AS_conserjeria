package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.EdificioNotFoundException;
import cl.ucn.disc.as.exceptions.PerDepNotFoundException;
import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.*;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.List;

/**
 * Sistema implementación
 *
 * @author Exequiel Gonzalez
 */
@Slf4j
public class SistemaImpl implements Sistema{

    private final Database database;

    /**
     * The Constructor
     */
    public SistemaImpl(Database database) {

        this.database = database;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Edificio add(@NotNull Edificio edificio){
        try {
            this.database.save(edificio);
        }catch(PersistenceException ex){
            //TODO: save the exception
            log.error("Error ",ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
        //WARN: need to retrieve the id?
        return edificio;
    }

    public Persona add(Persona persona) {
        try {
            this.database.save(persona);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
        return persona;
    }

    public Departamento addDepartamento(Departamento departamento, Edificio edificio) {

        Departamento departamentoActualizado = Departamento.builder()
                .numero(departamento.getNumero())
                .piso(departamento.getPiso())
                .edificio(edificio)
                .build();

        try {
            this.database.save(departamentoActualizado);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }

        return departamentoActualizado;
    }

    public Departamento addDepartamento(Departamento departamento, Long idEdificio) {

        Edificio edificio = this.database.find(Edificio.class, idEdificio);

        if (edificio == null) {
            throw new EdificioNotFoundException("El edificio con ID " + idEdificio + " no existe");
        }

        Departamento departamentoActualizado = Departamento.builder()
                .numero(departamento.getNumero())
                .piso(departamento.getPiso())
                .edificio(edificio)
                .build();

        try {
            this.database.save(departamentoActualizado);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }

        return departamentoActualizado;
    }

    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) {
        Contrato contrato = Contrato.builder()
                .fechaPago(fechaPago)
                .duenio(duenio)
                .departamento(departamento)
                .build();

        try {
            this.database.save(contrato);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al realizar un contrato", ex);
        }

        return contrato;
    }

    public Contrato realizarContrato(Long idDuenio, Long idDepartamento, Instant fechaPago) {

        Persona duenio = this.database.find(Persona.class, idDuenio);
        Departamento departamento = this.database.find(Departamento.class, idDepartamento);

        if (duenio == null || departamento == null) {
            throw new PerDepNotFoundException("No se encontró la persona o el departamento");
        }

        // Utilizamos Lombok @Builder para construir el contrato con los valores proporcionados.
        Contrato contrato = Contrato.builder()
                .fechaPago(fechaPago)
                .duenio(duenio)
                .departamento(departamento)
                .build();

        try {
            this.database.save(contrato);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error ", ex);
            throw new SistemaException("Error al realizar un contrato", ex);
        }

        return contrato;
    }

    public List<Contrato> getContratos(){
        //return contratos;
        return null;
    }

    public List<Persona> getPersonas(){
        //return personas;
        return null;
    }

    public List<Pago> getPagos(String rut){
        //return pagos;
        return null;
    }
}
