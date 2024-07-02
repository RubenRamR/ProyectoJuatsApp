/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import Colecciones.ChatColeccion;
import DTO.ChatDTO;
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

    public ChatColeccion obtenerChatPorId(ObjectId id) throws NegocioException;

    public List<ChatColeccion> obtenerTodosLosChats() throws NegocioException;

    public void actualizarChat(ChatDTO chat) throws NegocioException;

    public void eliminarChat(ObjectId id) throws NegocioException;
}
