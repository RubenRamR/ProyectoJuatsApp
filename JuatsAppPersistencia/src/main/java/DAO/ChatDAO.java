/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Colecciones.ChatColeccion;
import Docs.Mensaje;
import InterfacesDAO.IChatDAO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import com.mongodb.client.result.DeleteResult;

import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class ChatDAO implements IChatDAO {

    private final MongoCollection<Document> coleccion;

    public ChatDAO(MongoDatabase database) {
        this.coleccion = database.getCollection("Chat");
    }

    @Override
    public void crearChat(ChatColeccion chat) throws PersistenciaException {
        try
        {
            Document docChat = new Document()
                    .append("nombre", chat.getNombre())
                    .append("imagen", chat.getImagen())
                    .append("integrantes", chat.getIntegrantes())
                    .append("mensajes", convertirMensajesADocumentos(chat.getMensajes()));

            coleccion.insertOne(docChat);
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al crear el chat en la base de datos", e);
        }
    }

    public ChatColeccion obtenerChatPorId(ObjectId id) throws PersistenciaException {
        try
        {
            Document documentoChat = coleccion.find(eq("_id", id)).first();
            if (documentoChat != null)
            {
                return convertirDocumentoAChat(documentoChat);
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al obtener el chat de la base de datos", e);
        }
    }

    @Override
    public List<ChatColeccion> obtenerChatPorIdUsuario(ObjectId id) throws PersistenciaException {
        List<ChatColeccion> chats = new ArrayList<>();

        try
        {
            Document query = new Document("integrantes", id);
            MongoCursor<Document> cursor = coleccion.find(query).iterator();
            while (cursor.hasNext())
            {
                Document documentoChat = cursor.next();
                ChatColeccion chat = convertirDocumentoAChat(documentoChat);
                if (chat != null)
                {
                    chats.add(chat);
                }
            }
            cursor.close();
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al obtener todos los chats de la base de datos", e);
        }

        return chats;
    }
    
    
    @Override
    public List<ChatColeccion> obtenerTodosLosChats() throws PersistenciaException {
        List<ChatColeccion> chats = new ArrayList<>();

        try
        {
            MongoCursor<Document> cursor = coleccion.find().iterator();
            while (cursor.hasNext())
            {
                Document documentoChat = cursor.next();
                ChatColeccion chat = convertirDocumentoAChat(documentoChat);
                if (chat != null)
                {
                    chats.add(chat);
                }
            }
            cursor.close();
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al obtener todos los chats de la base de datos", e);
        }

        return chats;
    }

    @Override
    public void actualizarChat(ChatColeccion chat) throws PersistenciaException {
        try
        {
            Document filtro = new Document("_id", chat.getId());
            Document docChatActualizado = new Document()
                    .append("nombre", chat.getNombre())
                    .append("imagen", chat.getImagen())
                    .append("integrantes", chat.getIntegrantes())
                    .append("mensajes", convertirMensajesADocumentos(chat.getMensajes()));

            coleccion.replaceOne(filtro, docChatActualizado);
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al actualizar el chat en la base de datos", e);
        }
    }

    @Override
    public void eliminarChat(ObjectId id) throws PersistenciaException {
        try
        {
            // Se elimina el documento del chat con el ID especificado
            DeleteResult result = coleccion.deleteOne(eq("_id", id));

            // Verificar si se eliminó exitosamente al menos un documento
            if (result.getDeletedCount() == 0)
            {
                throw new PersistenciaException("No se encontró el chat con ID: " + id + " para eliminar.");
            }

            // Éxito al eliminar el chat
            System.out.println("Chat con ID " + id + " eliminado exitosamente.");

        } catch (Exception e)
        {
            throw new PersistenciaException("Error al eliminar el chat de la base de datos", e);
        }
    }

    private List<Document> convertirMensajesADocumentos(List<Mensaje> mensajes) {
        List<Document> mensajesDocs = new ArrayList<>();
        for (Mensaje mensaje : mensajes)
        {
            Document doc = new Document()
                    .append("textoMensaje", mensaje.getTextoMensaje())
                    .append("fechaHoraRegistro", java.sql.Timestamp.valueOf(mensaje.getFechaHoraRegistro()))
                    .append("imagenOpcional", mensaje.getImagenOpcional());
            mensajesDocs.add(doc);
        }
        return mensajesDocs;
    }

    private ChatColeccion convertirDocumentoAChat(Document documentoChat) {
        if (documentoChat == null)
        {
            return null;
        }

        ObjectId id = documentoChat.getObjectId("_id");
        String nombre = documentoChat.getString("nombre");
        byte[] imagen = documentoChat.get("imagen", Binary.class) != null ? documentoChat.get("imagen", Binary.class).getData() : null;
        List<ObjectId> integrantes = documentoChat.getList("integrantes", ObjectId.class, Collections.emptyList());
        List<Mensaje> mensajes = convertirDocumentosAMensajes(documentoChat.getList("mensajes", Document.class, Collections.emptyList()));

        return new ChatColeccion(id, nombre, imagen, integrantes, mensajes);
    }

    private List<Mensaje> convertirDocumentosAMensajes(List<Document> documentosMensajes) {
        List<Mensaje> mensajes = new ArrayList<>();
        for (Document doc : documentosMensajes)
        {
            String textoMensaje = doc.getString("textoMensaje");
            LocalDateTime fechaHoraRegistro = doc.getDate("fechaHoraRegistro").toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();

            byte[] imagenOpcional = null;
            if (doc.get("imagenOpcional") != null)
            {
                imagenOpcional = doc.get("imagenOpcional", Binary.class).getData();
            }

            mensajes.add(new Mensaje(textoMensaje, fechaHoraRegistro, imagenOpcional));
        }
        return mensajes;
    }

}
