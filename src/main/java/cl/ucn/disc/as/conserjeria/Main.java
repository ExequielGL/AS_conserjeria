package cl.ucn.disc.as.conserjeria;


import cl.ucn.disc.as.conserjeria.ui.ApiRestServer;
import cl.ucn.disc.as.conserjeria.ui.WebController;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;


/**
 * The Main Class.
 *
 * @author Exequiel González.
 * */
@Slf4j
public final class Main {

    /**
     * The Main.
     * @param args to use.
     */
    public static void main(String[] args) {

        log.debug("Starting Main ..");

        log.debug("Library path: {}", System.getProperty("java.library.path"));

        //Start the API Rest server
        Javalin app = ApiRestServer.start(7070, new WebController());

        log.debug("Done. :)");
    }
}
