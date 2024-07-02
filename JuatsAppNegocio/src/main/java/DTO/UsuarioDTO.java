/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import DocsDTO.ContactoDTO;
import DocsDTO.DireccionDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class UsuarioDTO {

    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String contraseña;
    private byte[] imagenPerfil;
    private DireccionDTO direccion;
    private List<ContactoDTO> contactos;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, LocalDate fechaNacimiento, String telefono, String contraseña, byte[] imagenPerfil, DireccionDTO direccion, List<ContactoDTO> contactos) {
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

    public UsuarioDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, LocalDate fechaNacimiento, String telefono, String contraseña, byte[] imagenPerfil, DireccionDTO direccion) {
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

    public UsuarioDTO(String id, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, LocalDate fechaNacimiento, String telefono, String contraseña, byte[] imagenPerfil, DireccionDTO direccion, List<ContactoDTO> contactos) {
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public byte[] getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(byte[] imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public List<ContactoDTO> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactoDTO> contactos) {
        this.contactos = contactos;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", sexo=" + sexo + ", fechaNacimiento=" + fechaNacimiento + ", telefono=" + telefono + ", imagenPerfil=" + imagenPerfil + ", direccion=" + direccion + ", contactos=" + contactos + '}';
    }

}
