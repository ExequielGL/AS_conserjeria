package cl.ucn.disc.as.services;

import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Pago;
import java.time.Instant;
import java.util.List;

/**
 * System operations.
 *
 * @author Arquitectura de Software
 */

public interface Sistema {

    /**
     * Agrega un edificio al Sistema.
     * @param edificio edificio a agregar.
     * @return edificio agregado.
     */
    Edificio add(Edificio edificio);

    /**
     * Agrega una persona al Sistema.
     * @param persona persona a agregar.
     * @return persona agregada.
     */
    Persona add(Persona persona);

    /**
     * Agrega un departamento a un edificio.
     * @param departamento departamento a agregar.
     * @param edificio edificio al que se le agregará un departamento.
     * @return departamento agregado.
     */
    Departamento addDepartamento(Departamento departamento, Edificio edificio);

    /**
     * Agrega un departamento a un edificio.
     * @param departamento departamento a agregar.
     * @param idEdificio Id del edificio al que se le agregará un departamento.
     * @return departamento agregado.
     */
    Departamento addDepartamento(Departamento departamento, Long idEdificio);

    /**
     * Se agregará un contrato al Sistema.
     * @param duenio Persona a la que se vinculará el contrato.
     * @param departamento Departamento por el que se hace el contrato.
     * @param fechaPago Fecha en que se realiza el contrato.
     * @return contrato
     */
    Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago);

    /**
     * Se agregará un contrato al Sistema.
     * @param idDuenio Id de la persona a la que se vinculará el contrato.
     * @param idDepartamento Id del departamento por el que se hace el contrato.
     * @param fechaPago Fecha en que se realiza el contrato.
     * @return contrato
     */
    Contrato realizarContrato(Long idDuenio, Long idDepartamento, Instant fechaPago);

    /**
     * Se obtienen los contratos en Sistema.
     * @return lista de contratos
     */
    List<Contrato> getContratos();

    /**
     * Se obtienen las personas en Sistema.
     * @return lista de personas.
     */
    List<Persona> getPersonas();

    /**
     * Se obtiene una persona especifica del Sistema.
     * @param rut Rut de la persona a buscar
     * @return lista de personas de tamaño 1
     */
    List<Persona> getPersonas(String rut);

    /**
     * Se obtienen los pagos de una persona especifica en sistema.
     * @param rut Rut de la persona.
     * @return lista de pagos
     */
    List<Pago> getPagos(String rut);

    /**
     * Método que pobla la base de datos con personas
     */
    void populate();
}
