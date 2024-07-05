/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Colecciones;

import Docs.Direccion;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class UsuarioColeccion {

    private ObjectId id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String contraseña;
    private byte[] imagenPerfil;
    private Direccion direccion;
    private List<ObjectId> contactos;

    public UsuarioColeccion() {
    }

    public UsuarioColeccion(String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, LocalDate fechaNacimiento, String telefono, String contraseña, byte[] imagenPerfil, Direccion direccion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.imagenPerfil = imagenPerfil;
        this.direccion = direccion;
    }

    public UsuarioColeccion(ObjectId id, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, LocalDate fechaNacimiento, String telefono, String contraseña, byte[] imagenPerfil, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.imagenPerfil = imagenPerfil;
        this.direccion = direccion;
    }

    public UsuarioColeccion(String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, LocalDate fechaNacimiento, String telefono, String contraseña, byte[] imagenPerfil, Direccion direccion, List<ObjectId> contactos) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.imagenPerfil = imagenPerfil;
        this.direccion = direccion;
        this.contactos = contactos;
    }

    public UsuarioColeccion(ObjectId id, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, LocalDate fechaNacimiento, String telefono, String contraseña, byte[] imagenPerfil, Direccion direccion, List<ObjectId> contactos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.imagenPerfil = imagenPerfil;
        this.direccion = direccion;
        this.contactos = contactos;
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public byte[] getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(byte[] imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<ObjectId> getContactos() {
        return contactos;
    }

    public void setContactos(List<ObjectId> contactos) {
        this.contactos = contactos;
    }

    @Override
    public String toString() {
        return "UsuarioColeccion{" + "id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento + ", telefono=" + telefono + ", contrase\u00f1a=" + contraseña + ", imagenPerfil=" + imagenPerfil + ", direccion=" + direccion + ", contactos=" + contactos + '}';
    }

}
