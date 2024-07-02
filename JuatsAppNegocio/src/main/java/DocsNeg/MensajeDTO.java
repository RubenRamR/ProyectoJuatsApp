/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocsNeg;

import java.time.LocalDateTime;

/**
 *
 * @author rramirez
 */
public class MensajeDTO {

    private String textoMensaje;
    private LocalDateTime fechaHoraRegistro;
    private byte[] imagenOpcional;

    // Constructor, getters y setters
    public MensajeDTO(String textoMensaje, LocalDateTime fechaHoraRegistro, byte[] imagenOpcional) {
        this.textoMensaje = textoMensaje;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.imagenOpcional = imagenOpcional;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public byte[] getImagenOpcional() {
        return imagenOpcional;
    }

    public void setImagenOpcional(byte[] imagenOpcional) {
        this.imagenOpcional = imagenOpcional;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "textoMensaje=" + textoMensaje + ", fechaHoraRegistro=" + fechaHoraRegistro + ", imagenOpcional=" + imagenOpcional + '}';
    }
    
    
}
