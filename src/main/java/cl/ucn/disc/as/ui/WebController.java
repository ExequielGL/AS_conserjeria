/*
 * Copyright (c) 2023. Arquitectura de Software, DISC, UCN.
 */
package cl.ucn.disc.as.ui;

import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import io.javalin.Javalin;

import java.util.List;

/**
 * The Sistema.
 */
public final class WebController implements RoutesConfigurator {
    private final Sistema sistema;

    /**
     * The web controller.
     */
    public WebController() {
        this.sistema = new SistemaImpl(DB.getDefault());
        //FIXME: only populate in case of new database.
        this.sistema.populate();
    }

    /**
     * Configure the routes.
     *
     * @param app to use.
     */
    @Override
    public void configure(final Javalin app) {

        app.get("/", ctx -> {
            ctx.result("Welcome to Conserjeria API REST");
        });

        // the personas api

        // Se obtiene una persona especifica a través del rut
        app.get("/personas/rut/{rut}", ctx -> {

            String rut = ctx.pathParam("rut");
            List<Persona> personaEncontrada = this.sistema.getPersonas(rut);
            if (personaEncontrada.isEmpty()) {
                ctx.status(404);
                ctx.result("No se encontró ninguna persona con el rut especificado.");
            } else {
                ctx.json(personaEncontrada);
            }
        });

        // Se obtienen todas las personas dentro del sistema
        app.get("/personas/rut/", ctx -> {

            List<Persona> personas = this.sistema.getPersonas();
            if (personas.isEmpty()) {
                ctx.status(404);
                ctx.result("No se encontró ninguna persona en el sistema.");
            } else {
                ctx.json(personas);
            }
        });
    }
}
