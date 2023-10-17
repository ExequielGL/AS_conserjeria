package cl.ucn.disc.as;

import cl.ucn.disc.as.dao.PersonaFinder;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
import java.util.Optional;

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


        //CREACION DE PERSONA EN LA BASE DE DATOS
          Persona persona = Persona.builder().rut("999999999")
                  .nombre("Exequiel")
                  .apellidos("Gonzalez")
                  .email("exequiel.gonzalez01@alumnos.ucn.cl")
                  .telefono("+56934985274")
                  .build();

          log.debug("The persona before bd: ${}",persona);
          db.save(persona);
          log.debug("The persona after bd: ${}",persona);

        // PERSONA FINDER
        //PersonaFinder pf = new PersonaFinder();
        //Optional<Persona> oPersona = pf.byRut("204163499");
        //oPersona.ifPresent(p -> log.debug("Persona from db: {}", p ));
        //log.debug("Done... ");

        //ADD EDIFICIO
        Sistema sistema = new SistemaImpl(db);
        Edificio edificio = Edificio.builder()
                .nombre("Las aguilas")
                .direccion("Av argentina 6674")
                .build();

        log.debug("Edificio before db: ${}", edificio);
        db.save(edificio);
        edificio = sistema.add(edificio);
        log.debug("Edificio after db: {}",edificio);
    }


}
