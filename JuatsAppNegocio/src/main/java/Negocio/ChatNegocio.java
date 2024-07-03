/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Colecciones.ChatColeccion;
import Conexion.ConexionDB;
import DAO.ChatDAO;
import DTO.ChatDTO;
import DTO.UsuarioDTO;
import Docs.Mensaje;
import DocsDTO.MensajeDTO;
import InterfacesNegocio.IChatNegocio;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class ChatNegocio implements IChatNegocio {

    private ChatDAO chatDAO;
    

    public ChatNegocio(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    @Override
    public void crearChat(ChatDTO chat) throws NegocioException {
        try
        {
            ChatColeccion chatColeccion = this.convertirChatDTO(chat);
            chatDAO.crearChat(chatColeccion);
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public ChatColeccion obtenerChatPorId(ObjectId id) throws NegocioException {
        try
        {
            return chatDAO.obtenerChatPorId(id);
        } catch(Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<ChatColeccion> obtenerTodosLosChats() throws NegocioException {
        try
        {
            return chatDAO.obtenerTodosLosChats();
        } catch(Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void actualizarChat(ChatDTO chat) throws NegocioException {
        try
        {
            ChatColeccion chatA;
            chatA = convertirChatDTO(chat);
            chatDAO.actualizarChat(chatA);
        } catch(Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void eliminarChat(ObjectId id) throws NegocioException {
        try
        {
            chatDAO.eliminarChat(id);
        } catch(Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    // Método para convertir MensajeDTO a MensajeColeccion
    public List<Mensaje> convertirMensajeDTO(List<MensajeDTO> dtos) {
        List<Mensaje> mensajes = new ArrayList<>();
        
        // Asignar los valores simples
        for(MensajeDTO dto : dtos){
        Mensaje mensaje = new Mensaje();   
        mensaje.setFechaHoraRegistro(dto.getFechaHoraRegistro());
        mensaje.setImagenOpcional(dto.getImagenOpcional());
        mensaje.setTextoMensaje(dto.getTextoMensaje());
        mensajes.add(mensaje);
        }
        return mensajes;
    }    
    
    // Método para convertir ChatDTO a ChatColeccion
    public ChatColeccion convertirChatDTO(ChatDTO dto) {
        ChatColeccion chat = new ChatColeccion();
        
        // Asignar los valores simples
        chat.setId(dto.getId());
        chat.setImagen(dto.getImagen());
        chat.setIntegrantes(dto.getIntegrantes());
        chat.setNombre(dto.getNombre());
        
        // Convertir los mensajes si está presente en el DTO        
        if (dto.getMensajes() != null)
        {
        chat.setMensajes(convertirMensajeDTO(dto.getMensajes()));        
        }
        
        return chat;
        
    }
}
