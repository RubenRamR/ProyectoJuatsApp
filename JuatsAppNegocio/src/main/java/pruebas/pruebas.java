/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import Conexion.ConexionDB;
import DAO.ChatDAO;
import DAO.UsuarioDAO;
import DTO.ChatDTO;
import DTO.UsuarioDTO;
import DocsDTO.MensajeDTO;
import InterfacesDAO.IChatDAO;
import InterfacesDAO.IConexionDB;
import InterfacesDAO.IUsuarioDAO;
import InterfacesNegocio.IUsuarioNegocio;
import Negocio.ChatNegocio;
import Negocio.UsuarioNegocio;
import com.mongodb.client.MongoDatabase;
import excepciones.NegocioException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author santi
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegocioException {
        // TODO code application logic here
        // Configurar la conexión a la base de datos
        IConexionDB conexionDB = new ConexionDB();
        MongoDatabase database = conexionDB.conexion("mongodb://localhost:27017", "JuatssApp");

        UsuarioDAO usuarioDAO = new UsuarioDAO(database);

        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(usuarioDAO);

        ChatDAO chatDAO = new ChatDAO(database);

        ChatNegocio chatNegocio = new ChatNegocio(chatDAO);

//        List<UsuarioDTO> b = usuarioNegocio.obtenerTodosLosUsuarios();
//        UsuarioDTO x = b.get(0);
//        
//        List<ChatDTO> a = chatNegocio.obtenerTodosLosChats();
//        System.out.println(a.size());
//        a.get(0).setMensajes(null);
//        chatNegocio.crearChat(a.get(0));
//        System.out.println(a.toString());
//        a = chatNegocio.obtenerTodosLosChats();
//        System.out.println(a.size());
/*
*
*
         */
//        ObjectId idUsuario = new ObjectId("6684d4c95e850f6424d9648e"); // Reemplaza con el ObjectId del usuario real
//
//        try
//        {
//            // Llama al método obtenerTodosLosContactosDeUsuario
//            List<UsuarioDTO> contactos = usuarioNegocio.obtenerTodosLosContactosDeUsuario(idUsuario);
//
//            // Imprime la información de los contactos usando el método toString de UsuarioDTO
//            for (UsuarioDTO contacto : contactos)
//            {
//                System.out.println(contacto.toString());
//            }
//        } catch (NegocioException e)
//        {
//            e.printStackTrace();
//        } finally
//        {
//            // Cierra la conexión a MongoDB
//            conexionDB.close();
//        }
/*
*
*
         */
//        ObjectId idChat = new ObjectId("66867de7df17b14b23769c5e"); // ID del chat existente en tu base de datos
//        ObjectId idUsuario = new ObjectId("6684d4c95e850f6424d9648e"); // ID del usuario que quieres agregar al chat
//
//        try
//        {
//            chatNegocio.agregarIntegrante(idChat, idUsuario);
//            System.out.println("Se ha agregado correctamente el usuario al chat.");
//        } catch (NegocioException e)
//        {
//            System.err.println("Error en la capa de negocio: " + e.getMessage());
//            e.printStackTrace();
//        }

try {
            // Simular ID de usuario
            ObjectId usuarioId = new ObjectId("6684d4c95e850f6424d9648e");

            // Crear instancia del negocio

            // Llamar al método obtenerChatsDeUsuario
            List<ChatDTO> chatsDTO = chatNegocio.obtenerChatsDeUsuario(usuarioId);

            // Mostrar los chats DTO obtenidos
            for (ChatDTO chatDTO : chatsDTO) {
                System.out.println("ChatDTO ID: " + chatDTO.getId());
                System.out.println("Nombre: " + chatDTO.getNombre());
                System.out.println("Integrantes: " + chatDTO.getIntegrantes());
//                System.out.println("Mensajes:");
//                for (MensajeDTO mensajeDTO : chatDTO.getMensajes()) {
//                    System.out.println("\tTexto: " + mensajeDTO.getTextoMensaje());
//                    System.out.println("\tFecha: " + mensajeDTO.getFechaHoraRegistro());
//                    System.out.println("\tImagen opcional: " + (mensajeDTO.getImagenOpcional() != null ? "Presente" : "N/A"));
//                }
//                System.out.println("--------------------------------------");
            }

        } catch (NegocioException e) {
            System.err.println("Error en la capa de negocio: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
