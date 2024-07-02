/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Colecciones;

import Docs.Mensaje;
import org.bson.types.ObjectId;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class ChatColeccion {

    private ObjectId id;
    private String nombre;
    private byte[] imagen;
    private List<ObjectId> integrantes;
    private List<Mensaje> mensajes;

    public ChatColeccion() {
    }

    public ChatColeccion(String nombre, byte[] imagen, List<ObjectId> integrantes, List<Mensaje> mensajes) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.integrantes = integrantes;
        this.mensajes = mensajes;
    }

    public ChatColeccion(ObjectId id, String nombre, byte[] imagen, List<ObjectId> integrantes, List<Mensaje> mensajes) {
        this.id = id;
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

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
