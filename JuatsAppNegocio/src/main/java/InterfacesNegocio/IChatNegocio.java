/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import Colecciones.ChatColeccion;
import DTO.ChatDTO;
import DTO.UsuarioDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public interface IChatNegocio {

    public void crearChat(ChatDTO chat) throws NegocioException;

    public ChatDTO obtenerChatPorId(ObjectId id) throws NegocioException;

    public List<ChatDTO> obtenerTodosLosChats() throws NegocioException;

    public List<ChatDTO> obtenerTodosLosChatsUsuario(ObjectId id) throws NegocioException;

    public void actualizarChat(ChatDTO chat) throws NegocioException;

    public void eliminarChat(ObjectId id) throws NegocioException;

    public void agregarIntegrante(ObjectId idChat, ObjectId idUsuario) throws NegocioException;

    public boolean esIntegrante(ObjectId idChat, ObjectId idUsuario) throws NegocioException;

    public List<ChatDTO> obtenerChatsDeUsuario(ObjectId usuarioId) throws NegocioException;
}
