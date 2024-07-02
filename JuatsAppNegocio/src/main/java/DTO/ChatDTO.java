/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DocsDTO.MensajeDTO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class ChatDTO {
    
    private ObjectId id;
    private String nombre;
    private byte[] imagen;
    private List<ObjectId> integrantes;
    private List<MensajeDTO> mensajes;

    public ChatDTO() {
    }

    public ChatDTO(ObjectId id, String nombre, byte[] imagen, List<ObjectId> integrantes, List<MensajeDTO> mensajes) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.integrantes = integrantes;
        this.mensajes = mensajes;
    }

    public ChatDTO(String nombre, byte[] imagen, List<ObjectId> integrantes, List<MensajeDTO> mensajes) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.integrantes = integrantes;
        this.mensajes = mensajes;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public List<ObjectId> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<ObjectId> integrantes) {
        this.integrantes = integrantes;
    }

    public List<MensajeDTO> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public String toString() {
        return "ChatDTO{" + "id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", integrantes=" + integrantes + ", mensajes=" + mensajes + '}';
    }

}
