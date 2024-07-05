/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Colecciones.ChatColeccion;
import Colecciones.UsuarioColeccion;
import Docs.Mensaje;
import excepciones.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public interface IChatDAO {

    public void crearChat(ChatColeccion chat) throws PersistenciaException;

    public ChatColeccion obtenerChatPorId(ObjectId id) throws PersistenciaException;

    public List<ChatColeccion> obtenerChatPorIdUsuario(ObjectId id) throws PersistenciaException;

    public List<ChatColeccion> obtenerTodosLosChats() throws PersistenciaException;

    public void actualizarChat(ChatColeccion chat) throws PersistenciaException;

    public void eliminarChat(ObjectId id) throws PersistenciaException;

    public void agregarIntegrante(ObjectId idChat, ObjectId idUsuario) throws PersistenciaException;

    public boolean esIntegrante(ObjectId chatId, ObjectId usuarioId) throws PersistenciaException;

    public List<ChatColeccion> obtenerChatsDeUsuario(ObjectId usuarioId) throws PersistenciaException;
    
    public void crearMensaje(Mensaje mensaje) throws PersistenciaException;
    
}
