/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.juatsapppersistencia;

import Colecciones.UsuarioColeccion;
import Conexion.ConexionDB;
import DAO.UsuarioDAO;
import Docs.Contacto;
import Docs.Direccion;
import InterfacesDAO.IConexionDB;
import InterfacesDAO.IUsuarioDAO;
import com.mongodb.client.MongoDatabase;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class Pruebas {

    public static void main(String[] args) throws PersistenciaException {
        // Configurar la conexión a la base de datos
        IConexionDB conexionDB = new ConexionDB();
        MongoDatabase database = conexionDB.conexion("mongodb://localhost:27017", "JuatssApp");

        // Crear instancia del DAO
        IUsuarioDAO usuarioDAO = new UsuarioDAO(database);

//        // Crear un nuevo usuario
//        UsuarioColeccion nuevoUsuario = new UsuarioColeccion();
//        nuevoUsuario.setNombre("Pepe");
//        nuevoUsuario.setApellidoPaterno("Pérez");
//        nuevoUsuario.setApellidoMaterno("López");
//        nuevoUsuario.setSexo("Masculino");
//        nuevoUsuario.setFechaNacimiento(LocalDate.of(1990, 1, 1));
//        nuevoUsuario.setTelefono("1234567890");
//        nuevoUsuario.setContraseña("miContraseñaSegura");
//        nuevoUsuario.setImagenPerfil(new byte[]
//        {
//            0, 1, 2
//        });  // Imagen de perfil en bytes
//
//        // Configurar la dirección del usuario
//        Direccion direccion = new Direccion();
//        direccion.setCalle("Calle Falsa");
//        direccion.setNumero("123");
//        direccion.setCodigoPostal("12345");
//        nuevoUsuario.setDireccion(direccion);
//
//        // Configurar los contactos del usuario
//        List<Contacto> contactos = new ArrayList<>();
//        Contacto contacto1 = new Contacto();
//        contacto1.setNombre("Ana");
//        contacto1.setImagen(new byte[]
//        {
//            3, 4, 5
//        });  // Imagen del contacto en bytes
//        contactos.add(contacto1);
//
//        Contacto contacto2 = new Contacto();
//        contacto2.setNombre("Luis");
//        contacto2.setImagen(new byte[]
//        {
//            6, 7, 8
//        });  // Imagen del contacto en bytes
//        contactos.add(contacto2);
//
//        nuevoUsuario.setContactos(contactos);
//
//        // Agregar el nuevo usuario a la base de datos
//        usuarioDAO.crearUsuario(nuevoUsuario);
//
//        System.out.println("Usuario agregado con éxito");
//
//        // Cerrar la conexión a la base de datos
//        conexionDB.close();

         // Prueba de obtenerUsuarioPorId
        ObjectId idUsuario = new ObjectId("668354949d9153358d33aa06"); // Cambia por el ObjectId válido en tu base de datos
        UsuarioColeccion usuarioEncontrado = usuarioDAO.obtenerUsuarioPorId(idUsuario);

        if (usuarioEncontrado != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("ID: " + usuarioEncontrado.getId());
            System.out.println("Nombre: " + usuarioEncontrado.getNombre());
            System.out.println("Apellido Paterno: " + usuarioEncontrado.getApellidoPaterno());
            System.out.println("Apellido Materno: " + usuarioEncontrado.getApellidoMaterno());
            System.out.println("Sexo: " + usuarioEncontrado.getSexo());
            System.out.println("Fecha de Nacimiento: " + usuarioEncontrado.getFechaNacimiento());
            System.out.println("Teléfono: " + usuarioEncontrado.getTelefono());
            System.out.println("Contraseña: " + usuarioEncontrado.getContraseña());
            System.out.println("Imagen de Perfil: " + usuarioEncontrado.getImagenPerfil());
            System.out.println("Dirección: " + usuarioEncontrado.getDireccion());
            System.out.println("Contactos: " + usuarioEncontrado.getContactos());
        } else {
            System.out.println("No se encontró ningún usuario con el ID proporcionado.");
        }

        // Cierra la conexión a la base de datos al finalizar
        conexionDB.close();
    }
}
