/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DocsDTO;

/**
 *
 * @author rramirez
 */
public class ContactoDTO {

    private String nombre;
    private byte[] imagen;

    public ContactoDTO() {
    }

    public ContactoDTO(String nombre, byte[] imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
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

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", imagen=" + imagen + '}';
    }

}
