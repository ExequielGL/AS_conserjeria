package cl.ucn.disc.as;

import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;


/**
 * The Main Class.
 *
 * @author Exequiel González
 * */
@Slf4j
public final class Main {

    /**
     * The Main.
     * @param args to use.
     */
    public static void main(String[] args) {
        log.debug("Starting Main..");

        // Base de datos.
        Sistema sistema = new SistemaImpl(DB.getDefault());

        // Creacion de personas en la base de datos.
        Persona persona = Persona.builder()
                .rut("20544764-4")
                .nombre("Exequiel")
                .apellidos("Gonzalez Lopez")
                .email("exequiel.gonzalez01@alumnos.ucn.cl")
                .telefono("+56934985274")
                .build();
        Persona persona2 = Persona.builder()
                .rut("20544763-6")
                .nombre("Natalia")
                .apellidos("Gonzalez Lopez")
                .email("natalia.gonzalez01@alumnos.ucn.cl")
                .telefono("+56934985274")
                .build();

        log.debug("The persona 1 before bd: ${}", persona);
        log.debug("The persona 2 before bd: ${}", persona2);
        persona = sistema.add(persona);
        persona2 = sistema.add(persona2);
        log.debug("The persona 1 after bd: ${}", persona);
        log.debug("The persona 2 after bd: ${}", persona2);

        //Creacion de edificio en la base de datos.
        Edificio edificio = Edificio.builder()
                .nombre("Las aguilas")
                .direccion("Av argentina 6674")
                .build();
        log.debug("Edificio before db: ${}", edificio);
        edificio = sistema.add(edificio);
        log.debug("Edificio after db: {}", edificio);

        // Crear departamento 1 y asociarlo a un edificio.
        Departamento departamento1 = Departamento.builder()
                .piso(1)
                .numero(1)
                .build();
        log.debug("Departamento before db: ${}", departamento1);
        departamento1 = sistema.addDepartamento(departamento1, edificio);
        log.debug("Departamento after db: {}", departamento1);
        log.debug("Edificio after addDepartamento Objeto: {}", edificio);

        // Crear departamento 2 y asociarlo al edificio anterior.
        Departamento departamento2 = Departamento.builder()
                .piso(1)
                .numero(2)
                .build();
        log.debug("Departamento before db: ${}", departamento2);
        departamento2 = sistema.addDepartamento(departamento2, 1L);
        log.debug("Departamento after db: {}", departamento2);
        log.debug("Edificio after addDepartamento Long: {}", edificio);

        // Realizar un contrato.
        Contrato contrato1 = sistema.realizarContrato(persona, departamento1, Instant.now());
        log.debug("Contrato 1: {}", contrato1);

        // Realizar un contrato usando IDs.
        Contrato contrato2 = sistema.realizarContrato(persona.getId(), departamento2.getId(), Instant.now());
        log.debug("Contrato 2: {}", contrato2);

        // Se obtienen las personas
        log.debug("Personas en el sistema: {}", sistema.getPersonas().toString());

        // Se obtienen los contratos
        log.debug("Contratos en el sistema: {}", sistema.getContratos().toString());

        // Se obtienen los pagos de la persona de rut 20544764-4.
        log.debug("Pagos en el sistema de 20544764-4: {}", sistema.getPagos("20544764-4").toString());

        log.debug("Done.");
    }
}
