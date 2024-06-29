/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcion;

/**
 * Clase que representa una excepción específica de la capa de presentación.
 * Esta excepción debe ser lanzada cuando ocurre un error en la lógica de presentación.
 * 
 * @author PC Gamer
 */
public class ExcepcionPresentacion extends Exception {

    /**
     * Constructor por defecto de la excepción de presentación.
     */
    public ExcepcionPresentacion() {
        super();
    }

    /**
     * Constructor de la excepción de presentación con un mensaje detallado.
     * 
     * @param message El mensaje detallado de la excepción.
     */
    public ExcepcionPresentacion(String message) {
        super(message);
    }

    /**
     * Constructor de la excepción de presentación con un mensaje detallado y una causa.
     * 
     * @param message El mensaje detallado de la excepción.
     * @param cause La causa de la excepción.
     */
    public ExcepcionPresentacion(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de la excepción de presentación con una causa.
     * 
     * @param cause La causa de la excepción.
     */
    public ExcepcionPresentacion(Throwable cause) {
        super(cause);
    }
}