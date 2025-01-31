/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Colecciones.ChatColeccion;
import Colecciones.UsuarioColeccion;
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
import java.util.concurrent.atomic.AtomicInteger;
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
    public ChatDTO obtenerChatPorId(ObjectId id) throws NegocioException {
        try
        {
            return convertirChatDTO(chatDAO.obtenerChatPorId(id));
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<ChatDTO> obtenerTodosLosChats() throws NegocioException {
        try
        {
            List<ChatColeccion> chat = chatDAO.obtenerTodosLosChats();

            List<ChatDTO> chatC = new ArrayList<>();
            AtomicInteger counter = new AtomicInteger(0);
            chat.forEach(row ->
            {
                int index = counter.getAndIncrement();
                chatC.add(convertirChatDTO(chat.get(index)));

            });

            return chatC;
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<ChatDTO> obtenerTodosLosChatsUsuario(ObjectId id) throws NegocioException {
        try
        {
            List<ChatColeccion> chat = chatDAO.obtenerChatPorIdUsuario(id);

            List<ChatDTO> chatC = new ArrayList<>();
            AtomicInteger counter = new AtomicInteger(0);
            chat.forEach(row ->
            {
                int index = counter.getAndIncrement();
                chatC.add(convertirChatDTO(chat.get(index)));

            });

            return chatC;
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void actualizarChat(ChatDTO chat) throws NegocioException {
        try
        {
            ChatColeccion chatA = convertirChatDTO(chat);
            chatDAO.actualizarChat(chatA);
        } catch (Exception e)
        {
            throw new NegocioException("Error al actualizar el chat en la base de datos", e);
        }
    }

    @Override
    public void eliminarChat(ObjectId id) throws NegocioException {
        try
        {
            chatDAO.eliminarChat(id);
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void agregarIntegrante(ObjectId idChat, ObjectId idUsuario) throws NegocioException {
        try
        {
            boolean esIntegrante = esIntegrante(idChat, idUsuario);

            if (esIntegrante)
            {
                throw new NegocioException("El usuario ya es integrante del chat.");
            }

            chatDAO.agregarIntegrante(idChat, idUsuario);
        } catch (NegocioException e)
        {
            throw e; // Propaga la excepción de negocio tal como está
        } catch (Exception e)
        {
            throw new NegocioException("Error al agregar integrante al chat: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean esIntegrante(ObjectId idChat, ObjectId idUsuario) throws NegocioException {
        try
        {
            return chatDAO.esIntegrante(idChat, idUsuario);
        } catch (Exception e)
        {
            throw new NegocioException("Error al verificar si el usuario es integrante del chat: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ChatDTO> obtenerChatsDeUsuario(ObjectId usuarioId) throws NegocioException {
        try
        {
            List<ChatColeccion> chatsColeccion = chatDAO.obtenerChatsDeUsuario(usuarioId);

            List<ChatDTO> chatsDTO = convertirListaChatColeccionAChatDTO(chatsColeccion);

            return chatsDTO;
        } catch (Exception e)
        {
            throw new NegocioException("Error al obtener y convertir los chats del usuario", e);
        }
    }

    private List<ChatDTO> convertirListaChatColeccionAChatDTO(List<ChatColeccion> chatsColeccion) {
        List<ChatDTO> chatsDTO = new ArrayList<>();
        for (ChatColeccion chatColeccion : chatsColeccion)
        {
            ChatDTO chatDTO = new ChatDTO();
            chatDTO.setId(chatColeccion.getId());
            chatDTO.setNombre(chatColeccion.getNombre());
            chatDTO.setImagen(chatColeccion.getImagen());
            chatDTO.setIntegrantes(chatColeccion.getIntegrantes());

            // Verificar si mensajes es null antes de asignarlo
            if (chatColeccion.getMensajes() != null)
            {
                List<MensajeDTO> mensajesDTO = new ArrayList<>();
                for (Mensaje mensaje : chatColeccion.getMensajes())
                {
                    MensajeDTO mensajeDTO = new MensajeDTO();
                    mensajeDTO.setTextoMensaje(mensaje.getTextoMensaje());
                    mensajeDTO.setFechaHoraRegistro(mensaje.getFechaHoraRegistro());
                    mensajeDTO.setImagenOpcional(mensaje.getImagenOpcional());
                    mensajeDTO.setEmisor(mensaje.getEmisor());
                    mensajesDTO.add(mensajeDTO);
                }
                chatDTO.setMensajes(mensajesDTO);
            } else
            {
                chatDTO.setMensajes(new ArrayList<>()); // Si mensajes es null, asignar una lista vacía
            }

            chatsDTO.add(chatDTO);
        }
        return chatsDTO;
    }

    // Método para convertir MensajeDTO a MensajeColeccion
    public List<Mensaje> convertirMensajeDTO(List<MensajeDTO> dtos) {
        List<Mensaje> mensajes = new ArrayList<>();

        // Asignar los valores simples
        for (MensajeDTO dto : dtos)
        {
            Mensaje mensaje = new Mensaje();
            mensaje.setFechaHoraRegistro(dto.getFechaHoraRegistro());
            mensaje.setImagenOpcional(dto.getImagenOpcional());
            mensaje.setTextoMensaje(dto.getTextoMensaje());
            mensaje.setEmisor(dto.getEmisor());
            mensajes.add(mensaje);
        }
        return mensajes;
    }

    // Método para convertir MensajeColeccion a MensajeDTO
    public List<MensajeDTO> convertirMensajeDAO(List<Mensaje> dtos) {
        List<MensajeDTO> mensajes = new ArrayList<>();

        // Asignar los valores simples
        for (Mensaje dto : dtos)
        {
            MensajeDTO mensaje = new MensajeDTO();
            mensaje.setFechaHoraRegistro(dto.getFechaHoraRegistro());
            mensaje.setImagenOpcional(dto.getImagenOpcional());
            mensaje.setTextoMensaje(dto.getTextoMensaje());
            mensaje.setEmisor(dto.getEmisor());
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

    // Método para convertir ChatColeccion a ChatDTO
    public ChatDTO convertirChatDTO(ChatColeccion dto) {
        ChatDTO chat = new ChatDTO();

        // Asignar los valores simples
        chat.setId(dto.getId());
        chat.setImagen(dto.getImagen());
        chat.setIntegrantes(dto.getIntegrantes());
        chat.setNombre(dto.getNombre());

        // Convertir los mensajes si está presente en el DTO        
        if (dto.getMensajes() != null)
        {
            chat.setMensajes(convertirMensajeDAO(dto.getMensajes()));
        }

        return chat;

    }
}
