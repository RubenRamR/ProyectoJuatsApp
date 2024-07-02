/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.juatsapppersistencia;

import Colecciones.ChatColeccion;
import Colecciones.UsuarioColeccion;
import Conexion.ConexionDB;
import DAO.ChatDAO;
import DAO.UsuarioDAO;
import Docs.Direccion;
import Docs.Mensaje;
import InterfacesDAO.IChatDAO;
import InterfacesDAO.IConexionDB;
import InterfacesDAO.IUsuarioDAO;
import com.mongodb.client.MongoDatabase;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
        IChatDAO chatDAO = new ChatDAO(database);

//        // Crear una instancia de Direccion
//        Direccion direccion = new Direccion("Calle Falsa", "123", "85064");
//
//        // Crear una lista de ObjectId para los contactos
//        List<ObjectId> contactos = Arrays.asList(new ObjectId("668468e0917a9c6e963d1de3"), new ObjectId("66846215d10bf7418f754c24"));
//
//        // Crear una instancia de UsuarioColeccion con datos de prueba
//        UsuarioColeccion usuario = new UsuarioColeccion(
//                "Ruben",
//                "Ramirez",
//                "López",
//                "M",
//                LocalDate.of(1990, 1, 1),
//                "1234567890",
//                "password123",
//                new byte[] { 0x01, 0x02, 0x03 }, // Asumimos que la imagen de perfil es nula para esta prueba
//                direccion,
//                contactos // Añadir los contactos
//        );
//
//        try {
//            // Intentar crear el usuario en la base de datos
//            usuarioDAO.crearUsuario(usuario);
//            System.out.println("Usuario creado exitosamente.");
//        } catch (PersistenciaException e) {
//            // Manejar la excepción en caso de error
//            e.printStackTrace();
//            System.out.println("Error al crear el usuario: " + e.getMessage());
//        }
//
//        // Cerrar la conexión a MongoDB
//        conexionDB.close();
//    
//       // ID del usuario que quieres buscar (cámbialo por un ID válido en tu base de datos)
//        ObjectId idUsuario = new ObjectId("66846215d10bf7418f754c24");
//
//        // Obtener el usuario por su ID
//        UsuarioColeccion usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
//
//        // Imprimir la información del usuario utilizando toString()
//        if (usuario != null) {
//            System.out.println(usuario.toString());
//        } else {
//            System.out.println("Usuario no encontrado.");
//        }
//// Obtener todos los usuarios
//        List<UsuarioColeccion> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
//
//        // Mostrar los usuarios obtenidos
//        for (UsuarioColeccion usuario : usuarios) {
//            System.out.println(usuario.toString());
//        }
//        // Supongamos que obtienes un usuario para actualizar
//        ObjectId idUsuario = new ObjectId("6684692875e706200ac57c40"); // Reemplaza con un ObjectId válido existente en tu base de datos
//        UsuarioColeccion usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);
//
//        if (usuario != null) {
//            // Modificar los datos del usuario (por ejemplo, cambiar el nombre)
//            usuario.setNombre("Nuevo Nombre");
//
//            // Llamar al método actualizarUsuario
//            usuarioDAO.actualizarUsuario(usuario);
//
//            // Verificar la actualización
//            UsuarioColeccion usuarioActualizado = usuarioDAO.obtenerUsuarioPorId(idUsuario);
//            if (usuarioActualizado != null) {
//                System.out.println("Usuario actualizado correctamente: " + usuarioActualizado.toString());
//            } else {
//                System.out.println("No se encontró ningún usuario actualizado.");
//            }
//        } else {
//            System.out.println("No se encontró ningún usuario con el ObjectId proporcionado.");
//        }
//        ObjectId idUsuario = new ObjectId("66846215d10bf7418f754c24");  // Reemplaza con el ID del usuario que deseas eliminar
//
//        // Eliminar el usuario por su ID
//        usuarioDAO.eliminarUsuario(idUsuario);
//
//        // Verificar si el usuario ha sido eliminado (debería devolver null)
//        UsuarioColeccion usuarioEliminado = usuarioDAO.obtenerUsuarioPorId(idUsuario);
//        if (usuarioEliminado == null)
//        {
//            System.out.println("Usuario eliminado correctamente");
//        } else
//        {
//            System.out.println("Error al eliminar el usuario");
//        }

        /*
*
*

         */
//
//        // Crear un chat de prueba
//        ChatColeccion chat = new ChatColeccion();
//        chat.setNombre("Chat de prueba22222");
//        chat.setImagen(new byte[]{7, 8, 9}); // Si tienes una imagen, conviértela a byte[] y asígnala aquí
//        chat.setIntegrantes(Arrays.asList(new ObjectId(), new ObjectId()));
//
//        // Crear mensajes de prueba
//        Mensaje mensaje1 = new Mensaje("Hola, ¿cómo estás?", LocalDateTime.now(), new byte[]{0, 4, 8});
//        Mensaje mensaje2 = new Mensaje("¡Bien, gracias! ¿Y tú?", LocalDateTime.now(), null);
//
//        List<Mensaje> mensajes = Arrays.asList(mensaje1, mensaje2);
//        chat.setMensajes(mensajes);
//
//        try
//        {
//            chatDAO.crearChat(chat);
//            System.out.println("Chat creado exitosamente");
//        } catch (PersistenciaException e)
//        {
//            e.printStackTrace();
//        }
//        ObjectId id = new ObjectId("6683790e62311b4a7c373a5b");
//        try {
//            ChatColeccion chat = chatDAO.obtenerChatPorId(id);
//            if (chat != null) {
//                System.out.println("Chat encontrado:");
//                System.out.println(chat);
//            } else {
//                System.out.println("No se encontró ningún chat con el ID proporcionado.");
//            }
//        } catch (PersistenciaException e) {
//            System.out.println("Error al obtener el chat: " + e.getMessage());
//            e.printStackTrace();
//        }
//        try
//        {
//            // Obtener todos los chats
//            List<ChatColeccion> chats = chatDAO.obtenerTodosLosChats();
//
//            // Mostrar los chats obtenidos
//            if (chats.isEmpty())
//            {
//                System.out.println("No se encontraron chats en la base de datos.");
//            } else
//            {
//                System.out.println("Chats encontrados:");
//                for (ChatColeccion chat : chats)
//                {
//                    System.out.println(chat); // Esto llamará al método toString de ChatColeccion
//                }
//            }
//        } catch (PersistenciaException e)
//        {
//            System.err.println("Error al obtener todos los chats: " + e.getMessage());
//            e.printStackTrace();
//        }
//        try {
//            // Paso 1: Obtener un chat existente por su ID (supongamos que el ID existe en la base de datos)
//            ObjectId idExistente = new ObjectId("6683790e62311b4a7c373a5b"); // Reemplaza con el ID real de un chat existente
//
//            ChatColeccion chatExistente = chatDAO.obtenerChatPorId(idExistente);
//            if (chatExistente == null) {
//                System.err.println("No se encontró el chat con ID: " + idExistente);
//                return;
//            }
//
//            // Paso 2: Modificar todos los datos del chat existente
//            chatExistente.setNombre("Nuevo nombre del chat actualizado");
//            chatExistente.setImagen(new byte[] { 0x01, 0x02, 0x03 }); // Ejemplo de datos de imagen
//            chatExistente.setIntegrantes(List.of(new ObjectId("6683790e62311b4a7c373a59"), new ObjectId("6683790e62311b4a7c373a5a"))); // Ejemplo de lista de integrantes
//            chatExistente.setMensajes(crearMensajesEjemplo()); // Ejemplo de lista de mensajes
//
//            // Paso 3: Actualizar el chat en la base de datos
//            chatDAO.actualizarChat(chatExistente);
//
//            // Mensaje de éxito
//            System.out.println("Chat actualizado exitosamente.");
//
//        } catch (PersistenciaException e) {
//            // Manejo de excepciones
//            System.err.println("Error al actualizar el chat: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//try {
//    ObjectId idEliminar = new ObjectId("6683779492f32a5cfbc1cea3"); // ID del chat a eliminar
//    chatDAO.eliminarChat(idEliminar);
//    System.out.println("Chat eliminado correctamente.");
//} catch (PersistenciaException e) {
//    System.err.println("Error al eliminar el chat: " + e.getMessage());
//    e.printStackTrace();
//}
//
    }
//
//    // Método para crear una lista de mensajes de ejemplo
//    private static List<Mensaje> crearMensajesEjemplo() {
//        List<Mensaje> mensajes = new ArrayList<>();
//        mensajes.add(new Mensaje("Hola", LocalDateTime.now(), null));
//        mensajes.add(new Mensaje("¿Cómo estás?", LocalDateTime.now().plusHours(1), new byte[] { 0x04, 0x05 }));
//        return mensajes;
//    }
}
