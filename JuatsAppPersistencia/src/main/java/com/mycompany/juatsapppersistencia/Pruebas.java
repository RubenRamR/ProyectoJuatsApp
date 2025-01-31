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
import Utilerias.imagenABytes;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import excepciones.PersistenciaException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class Pruebas {

    public static void main(String[] args) throws PersistenciaException, IOException {
        // Configurar la conexión a la base de datos
        IConexionDB conexionDB = new ConexionDB();
        MongoDatabase database = conexionDB.conexion("mongodb://localhost:27017", "JuatssApp");

        // Crear instancia del DAO
        IUsuarioDAO usuarioDAO = new UsuarioDAO(database);
        IChatDAO chatDAO = new ChatDAO(database);
        MongoCollection<Document> coleccion;
        coleccion = database.getCollection("Chat");

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
// Obtener todos los usuarios
//        List<UsuarioColeccion> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
//
//        // Mostrar los usuarios obtenidos
//        for (UsuarioColeccion usuario : usuarios) {
//            System.out.println(usuario.toString());
//        }
////        // Supongamos que obtienes un usuario para actualizar
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
//         Crear un chat de prueba
//        
//        imagenABytes a = new imagenABytes();
//
//        byte[] imagen = a.convertirImagenABytes(new File("C:\\Users\\santi\\Desktop\\mudkip.jpg"));

//        ObjectId u = new ObjectId("6684d4c95e850f6424d9648e");
//        ChatColeccion chat = new ChatColeccion();
//        chat.setNombre("mudkip");
//        chat.setImagen(imagen); // Si tienes una imagen, conviértela a byte[] y asígnala aquí
//        chat.setIntegrantes(Arrays.asList(u));
//
        // Crear mensajes de prueba
//        Mensaje mensaje1 = new Mensaje("Hola, ¿cómo estás?", LocalDateTime.now(), imagen);
//        Mensaje mensaje2 = new Mensaje("¡Bien, gracias! ¿Y tú?", LocalDateTime.now(), null);
//        
//        chatDAO.crearMensaje(mensaje1);
//        chatDAO.crearMensaje(mensaje2);
//        

////        List<Mensaje> mensajes = Arrays.asList(mensaje1, mensaje2);
////        chat.setMensajes(mensajes);
//        try
//        {
//            chatDAO.crearChat(chat);
//            System.out.println("Chat creado exitosamente");
//        } catch (PersistenciaException e)
//        {
//            e.printStackTrace();
//        }
//        ObjectId id = new ObjectId("6684c54c9a389548b5e9d215");
//        try {
//            chat = chatDAO.obtenerChatPorId(id);
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
//        
//        ObjectId idU = new ObjectId("6684959b039e16078c7c274c");
//        try {
//            List<ChatColeccion> chats2 = chatDAO.obtenerChatPorIdUsuario(idU);
//            if (chats2 != null) {
//                System.out.println("Chat encontrado:");
//                System.out.println(chats2);
//            } else {
//                System.out.println("No se encontró ningún chat con el ID proporcionado.");
//            }
//        } catch (PersistenciaException e) {
//            System.out.println("Error al obtener el chat: " + e.getMessage());
//            e.printStackTrace();
//        }
//        
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
//                for (ChatColeccion chat2 : chats)
//                {
//                    System.out.println(chat2); // Esto llamará al método toString de ChatColeccion
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

        /*
*
*
*
         */
//        ObjectId idUsuario = new ObjectId("6684d4c95e850f6424d9648e"); // Reemplaza con el ObjectId del usuario real
//
//        try
//        {
//            // Llama al método obtenerTodosLosContactosDeUsuario
//            List<UsuarioColeccion> contactos = usuarioDAO.obtenerTodosLosContactosDeUsuario(idUsuario);
//
//            // Imprime la información de los contactos obtenidos utilizando toString()
//            for (UsuarioColeccion contacto : contactos)
//            {
//                System.out.println(contacto.toString());
//            }
//        } catch (PersistenciaException e)
//        {
//            e.printStackTrace();
//        } finally
//        {
//            // Cierra la conexión a MongoDB
//            conexionDB.close();
//
//    // Método para crear una lista de mensajes de ejemplo
//    private static List<Mensaje> crearMensajesEjemplo() {
//        List<Mensaje> mensajes = new ArrayList<>();
//        mensajes.add(new Mensaje("Hola", LocalDateTime.now(), null));
//        mensajes.add(new Mensaje("¿Cómo estás?", LocalDateTime.now().plusHours(1), new byte[] { 0x04, 0x05 }));
//        return mensajes;
//    }

        /*
*
*
         */
//        try {
//            // Mostrar solo los integrantes de los chats
//            List<Document> listaChats = coleccion.find().into(new ArrayList<>());
//            for (Document chat : listaChats) {
//                ObjectId chatId = chat.getObjectId("_id");
//                List<ObjectId> integrantes = (List<ObjectId>) chat.get("integrantes");
//
//                System.out.println("ID del Chat: " + chatId);
//                System.out.println("Integrantes:");
//                for (ObjectId integrante : integrantes) {
//                    System.out.println(integrante);
//                }
//                System.out.println(); // Separador entre chats
//            }
//
//        } finally {
//            // Cerrar la conexión a MongoDB al finalizar las pruebas
//            conexionDB.close();
//        }
/*
*
*
*
         */
//// ID del chat al que queremos agregar un integrante
//        ObjectId idChat = new ObjectId("66867de7df17b14b23769c5e");
//
//        // ID del usuario que queremos agregar como integrante
//        ObjectId idUsuario = new ObjectId("6684d58d4c46c47806701646");
//
//        // Agregamos el usuario como integrante al chat
//        try {
//            chatDAO.agregarIntegrante(idChat, idUsuario);
//            System.out.println("Integrante agregado correctamente al chat.");
//        } catch (PersistenciaException e) {
//            System.err.println("Error al agregar integrante al chat: " + e.getMessage());
//        }
//
//        // Mostramos el chat actualizado después de agregar el integrante
//        System.out.println("\nChat después de agregar integrante:");
//        chatDAO.obtenerChatPorId(idChat);
    }
}
