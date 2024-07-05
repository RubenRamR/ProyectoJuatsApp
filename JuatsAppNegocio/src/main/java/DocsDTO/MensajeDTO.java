/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocsDTO;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class MensajeDTO {

    private String textoMensaje;
    private LocalDateTime fechaHoraRegistro;
    private byte[] imagenOpcional;
    private ObjectId emisor;

    public MensajeDTO() {
    }

    public MensajeDTO(String textoMensaje, LocalDateTime fechaHoraRegistro, byte[] imagenOpcional, ObjectId emisor) {
        this.textoMensaje = textoMensaje;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.imagenOpcional = imagenOpcional;
        this.emisor = emisor;
    }

//    // Constructor, getters y setters
//    public MensajeDTO(String textoMensaje, LocalDateTime fechaHoraRegistro, byte[] imagenOpcional) {
//        this.textoMensaje = textoMensaje;
//        this.fechaHoraRegistro = fechaHoraRegistro;
//        this.imagenOpcional = imagenOpcional;
//    }

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

    public ObjectId getEmisor() {
        return emisor;
    }

    public void setEmisor(ObjectId emisor) {
        this.emisor = emisor;
    }

    @Override
    public String toString() {
        return "MensajeDTO{" + "textoMensaje=" + textoMensaje + ", fechaHoraRegistro=" + fechaHoraRegistro + ", imagenOpcional=" + imagenOpcional + ", emisor=" + emisor + '}';
    }

}
