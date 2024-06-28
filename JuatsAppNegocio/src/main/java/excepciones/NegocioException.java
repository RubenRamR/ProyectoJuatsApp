/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Chris
 */
public class NegocioException extends Exception {

    /**
     * Constructor vacío.
     */
    public NegocioException() {
    }

    /**
     * Constructor que establece el mensaje de la excepción.
     *
     * @param message de la excepción.
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Constructor que establece el mensaje y la causa de la excepción.
     *
     * @param message de la excepción.
     * @param cause de la excepción.
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
