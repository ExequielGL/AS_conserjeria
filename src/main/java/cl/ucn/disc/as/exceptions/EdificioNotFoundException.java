package cl.ucn.disc.as.exceptions;

public class EdificioNotFoundException extends RuntimeException {

    /**
     * Excepcion en caso de no encontrar edificio.
     * @param message Mensaje a mostrar.
     */
    public EdificioNotFoundException(String message) { super(message); }
}
