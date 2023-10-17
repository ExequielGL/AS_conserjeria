package cl.ucn.disc.as;


import cl.ucn.disc.as.model.*;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.List;


/**
 *
 * The Main
 * @author Exequiel González
 *
 * */

@Slf4j
public class Main {
    public static void main(String[] args)
    {
        log.debug("Starting Main..");

        // Base de datos
        Database db = DB.getDefault();
        Sistema sistema = new SistemaImpl(db);

        //Creacion de persona en la base de datos
        Persona persona = Persona.builder()
                .rut("20544764-4")
                .nombre("Exequiel")
                .apellidos("Gonzalez Lopez")
                .email("exequiel.gonzalez01@alumnos.ucn.cl")
                .telefono("+56934985274")
                .build();

        log.debug("The persona before bd: ${}",persona);
        db.save(persona);
        persona = sistema.add(persona);
        log.debug("The persona after bd: ${}",persona);

        //Creacion de edificio en la base de datos
        Edificio edificio = Edificio.builder()
                .nombre("Las aguilas")
                .direccion("Av argentina 6674")
                .build();

        log.debug("Edificio before db: ${}", edificio);
        db.save(edificio);
        edificio = sistema.add(edificio);
        log.debug("Edificio after db: {}",edificio);

        //Crear departamento y asociarlo a un edificio

        Departamento departamento1 = Departamento.builder()
                .piso(1)
                .numero(1)
                .edificio(null)
                .build();
        log.debug("Departamento before db: ${}", departamento1);
        departamento1 = sistema.addDepartamento(departamento1,edificio);
        log.debug("Departamento after db: {}",departamento1);
        log.debug("Edificio after addDepartamento Objeto: {}",edificio);

        Departamento departamento2 = Departamento.builder()
                .piso(1)
                .numero(2)
                .edificio(null)
                .build();
        log.debug("Departamento before db: ${}", departamento2);
        departamento2 = sistema.addDepartamento(departamento2, 1L);
        log.debug("Departamento after db: {}",departamento2);
        log.debug("Edificio after addDepartamento Long: {}",edificio);

        // Realizar un contrato
        Contrato contrato1 = sistema.realizarContrato(persona, departamento1, Instant.now());
        log.debug("Contrato 1: {}", contrato1);

        // Realizar un contrato usando IDs
        Contrato contrato2 = sistema.realizarContrato(persona.getId(), departamento2.getId(), Instant.now());
        log.debug("Contrato 2: {}", contrato2);



    }
}
