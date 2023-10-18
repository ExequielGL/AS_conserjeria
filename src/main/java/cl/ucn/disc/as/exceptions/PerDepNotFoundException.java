package cl.ucn.disc.as.exceptions;

public class PerDepNotFoundException extends RuntimeException{

    /**
     * Error al no encontrar persona o departamento.
     * @param message Mensaje a mostrar.
     */
    public PerDepNotFoundException(String message) { super(message); }
}
