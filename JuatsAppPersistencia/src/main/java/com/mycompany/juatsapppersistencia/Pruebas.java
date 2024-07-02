/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.juatsapppersistencia;

import Colecciones.ChatColeccion;
import Colecciones.UsuarioColeccion;
import Conexion.ConexionDB;
import DAO.ChatDAO;
import DAO.UsuarioDAO;
import Docs.Contacto;
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

        // Crear un nuevo usuario
        UsuarioColeccion nuevoUsuario = new UsuarioColeccion();
        nuevoUsuario.setNombre("Pepe");
        nuevoUsuario.setApellidoPaterno("Pérez");
        nuevoUsuario.setApellidoMaterno("López");
        nuevoUsuario.setSexo("Masculino");
        nuevoUsuario.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        nuevoUsuario.setTelefono("1234567890");
        nuevoUsuario.setContraseña("miContraseñaSegura");
        nuevoUsuario.setImagenPerfil(new byte[]
        {
            0, 1, 2
        });  // Imagen de perfil en bytes

// Configurar la dirección del usuario
        Direccion direccion = new Direccion();
        direccion.setCalle("Calle Falsa");
        direccion.setNumero("123");
        direccion.setCodigoPostal("12345");
        nuevoUsuario.setDireccion(direccion);

// Configurar los contactos del usuario
        List<Contacto> contactos = new ArrayList<>();
        Contacto contacto1 = new Contacto();
        contacto1.setNombre("Ana");
        contacto1.setImagen(new byte[]
        {
            3, 4, 5
        });  // Imagen del contacto en bytes
        contactos.add(contacto1);

        Contacto contacto2 = new Contacto();
        contacto2.setNombre("Luis");
        contacto2.setImagen(new byte[]
        {
            6, 7, 8
        });  // Imagen del contacto en bytes
        contactos.add(contacto2);

        nuevoUsuario.setContactos(contactos);

// Agregar el nuevo usuario a la base de datos
        usuarioDAO.crearUsuario(nuevoUsuario);

        System.out.println("Usuario agregado con éxito");

// Cerrar la conexión a la base de datos
//        conexionDB.close();
        // Crear un ObjectId para buscar un usuario específico
        ObjectId idBusqueda = new ObjectId("668354949d9153358d33aa06");

        // Llamar al método para obtener el usuario por su ID
        UsuarioColeccion usuarioEncontrado = usuarioDAO.obtenerUsuarioPorId(idBusqueda);

        // Verificar si se encontró el usuario y manejar la salida de acuerdo a tus necesidades
        if (usuarioEncontrado != null)
        {
            // Mostrar los datos del usuario usando toString
            System.out.println("Usuario encontrado:\n" + usuarioEncontrado.toString());
        } else
        {
            System.out.println("No se encontró ningún usuario con el ID proporcionado.");
        }
        // Llamar al método para obtener todos los usuarios
        List<UsuarioColeccion> usuarios = usuarioDAO.obtenerTodosLosUsuarios();

        // Imprimir el resultado
        if (usuarios.isEmpty())
        {
            System.out.println("No se encontraron usuarios.");
        } else
        {
            System.out.println("Usuarios encontrados:");
            for (UsuarioColeccion usuario : usuarios)
            {
                System.out.println(usuario.toString()); // Usando el método toString() para imprimir el usuario
            }
        }
        // Obtener un usuario existente (aquí asumo que ya tienes el usuario que deseas actualizar)
        ObjectId idUsuario = new ObjectId("668354949d9153358d33aa06");  // Reemplaza con el ID del usuario existente
        UsuarioColeccion usuarioExistente = usuarioDAO.obtenerUsuarioPorId(idUsuario);

//        if (usuarioExistente != null) {
//            // Modificar los datos del usuario
//            usuarioExistente.setNombre("Nuevo Nombre");
//            usuarioExistente.setApellidoPaterno("Nuevo Apellido Paterno");
//            usuarioExistente.setApellidoMaterno("Nuevo Apellido Materno");
//            usuarioExistente.setSexo("Femenino");
//            usuarioExistente.setFechaNacimiento(LocalDate.of(1995, 5, 15));
//            usuarioExistente.setTelefono("9876543210");
//            usuarioExistente.setContraseña("nuevaContraseña");
//            usuarioExistente.setImagenPerfil(new byte[]{0, 1, 2});  // Nueva imagen en bytes
//
//            // Configurar la nueva dirección
//            Direccion nuevaDireccion = new Direccion();
//            nuevaDireccion.setCalle("Nueva Calle");
//            nuevaDireccion.setNumero("456");
//            nuevaDireccion.setCodigoPostal("54321");
//            usuarioExistente.setDireccion(nuevaDireccion);
//
//            // Configurar nuevos contactos
//            List<Contacto> nuevosContactos = new ArrayList<>();
//            Contacto contacto1 = new Contacto();
//            contacto1.setNombre("Nuevo Contacto 1");
//            contacto1.setImagen(new byte[]{3, 4, 5});  // Nueva imagen del contacto en bytes
//            nuevosContactos.add(contacto1);
//
//            Contacto contacto2 = new Contacto();
//            contacto2.setNombre("Nuevo Contacto 2");
//            contacto2.setImagen(new byte[]{6, 7, 8});  // Nueva imagen del contacto en bytes
//            nuevosContactos.add(contacto2);
//
//            usuarioExistente.setContactos(nuevosContactos);
//
//            // Actualizar el usuario en la base de datos
//            usuarioDAO.actualizarUsuario(usuarioExistente);
//        } else {
//            System.out.println("No se encontró el usuario con el ID especificado");
//        }
//        ObjectId idUsuario = new ObjectId("668361373fa19f1126e0a042");  // Reemplaza con el ID del usuario que deseas eliminar

        // Eliminar el usuario por su ID
        usuarioDAO.eliminarUsuario(idUsuario);

        // Verificar si el usuario ha sido eliminado (debería devolver null)
        UsuarioColeccion usuarioEliminado = usuarioDAO.obtenerUsuarioPorId(idUsuario);
        if (usuarioEliminado == null)
        {
            System.out.println("Usuario eliminado correctamente");
        } else
        {
            System.out.println("Error al eliminar el usuario");
        }

        // Crear un chat de prueba
        ChatColeccion chat = new ChatColeccion();
        chat.setNombre("Chat de prueba22222");
        chat.setImagen(new byte[]{7, 8, 9}); // Si tienes una imagen, conviértela a byte[] y asígnala aquí
        chat.setIntegrantes(Arrays.asList(new ObjectId(), new ObjectId()));

        // Crear mensajes de prueba
        Mensaje mensaje1 = new Mensaje("Hola, ¿cómo estás?", LocalDateTime.now(), new byte[]{0, 4, 8});
        Mensaje mensaje2 = new Mensaje("¡Bien, gracias! ¿Y tú?", LocalDateTime.now(), null);

        List<Mensaje> mensajes = Arrays.asList(mensaje1, mensaje2);
        chat.setMensajes(mensajes);

        try
        {
            chatDAO.crearChat(chat);
            System.out.println("Chat creado exitosamente");
        } catch (PersistenciaException e)
        {
            e.printStackTrace();
        }
        ObjectId id = new ObjectId("6683790e62311b4a7c373a5b");

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


        try {
            // Paso 1: Obtener un chat existente por su ID (supongamos que el ID existe en la base de datos)
            ObjectId idExistente = new ObjectId("6683790e62311b4a7c373a5b"); // Reemplaza con el ID real de un chat existente

            ChatColeccion chatExistente = chatDAO.obtenerChatPorId(idExistente);
            if (chatExistente == null) {
                System.err.println("No se encontró el chat con ID: " + idExistente);
                return;
            }

            // Paso 2: Modificar todos los datos del chat existente
            chatExistente.setNombre("Nuevo nombre del chat actualizado");
            chatExistente.setImagen(new byte[] { 0x01, 0x02, 0x03 }); // Ejemplo de datos de imagen
            chatExistente.setIntegrantes(List.of(new ObjectId("6683790e62311b4a7c373a59"), new ObjectId("6683790e62311b4a7c373a5a"))); // Ejemplo de lista de integrantes
            chatExistente.setMensajes(crearMensajesEjemplo()); // Ejemplo de lista de mensajes

            // Paso 3: Actualizar el chat en la base de datos
            chatDAO.actualizarChat(chatExistente);

            // Mensaje de éxito
            System.out.println("Chat actualizado exitosamente.");

        } catch (PersistenciaException e) {
            // Manejo de excepciones
            System.err.println("Error al actualizar el chat: " + e.getMessage());
            e.printStackTrace();
        }

try {
    ObjectId idEliminar = new ObjectId("6683779492f32a5cfbc1cea3"); // ID del chat a eliminar
    chatDAO.eliminarChat(idEliminar);
    System.out.println("Chat eliminado correctamente.");
} catch (PersistenciaException e) {
    System.err.println("Error al eliminar el chat: " + e.getMessage());
    e.printStackTrace();
}

    }

    // Método para crear una lista de mensajes de ejemplo
    private static List<Mensaje> crearMensajesEjemplo() {
        List<Mensaje> mensajes = new ArrayList<>();
        mensajes.add(new Mensaje("Hola", LocalDateTime.now(), null));
        mensajes.add(new Mensaje("¿Cómo estás?", LocalDateTime.now().plusHours(1), new byte[] { 0x04, 0x05 }));
        return mensajes;
    }
}
