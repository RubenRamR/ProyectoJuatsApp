/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Colecciones.UsuarioColeccion;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import Docs.Direccion;
import DocsDTO.ContactoDTO;
import DocsDTO.DireccionDTO;
import InterfacesNegocio.IUsuarioNegocio;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class UsuarioNegocio implements IUsuarioNegocio {

    private final UsuarioDAO usuarioDAO;

    public UsuarioNegocio(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void crearUsuario(UsuarioDTO usuario) throws NegocioException {
        try
        {
            UsuarioColeccion usuarioColeccion = this.convertirUsuarioDTO(usuario);
            usuarioDAO.crearUsuario(usuarioColeccion);
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void crearUsuarioSinContactos(UsuarioDTO usuario) throws NegocioException {

        UsuarioColeccion usuarioColeccion = this.convertirUsuarioDTO(usuario);
        try
        {
            usuarioDAO.crearUsuarioSinContactos(usuarioColeccion);
        } catch (PersistenciaException ex)
        {
            Logger.getLogger(UsuarioNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(ObjectId id) throws NegocioException {
        try
        {
            return convertirUsuarioDTO(usuarioDAO.obtenerUsuarioPorId(id));
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorCredenciales(UsuarioDTO dto) throws NegocioException {
        try
        {
            String telefono = dto.getTelefono();
            String contraseña = dto.getContraseña();
            return convertirUsuarioDTO(usuarioDAO.obtenerUsuarioPorCredenciales(telefono, contraseña));
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() throws NegocioException {
        try
        {
            List<UsuarioColeccion> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
            System.out.println(usuarios.toString());
            List<UsuarioDTO> usuariosC = new ArrayList<>();

            AtomicInteger counter = new AtomicInteger(0);
            usuarios.forEach(row ->
            {
                int index = counter.getAndIncrement();
                usuariosC.add(convertirUsuarioDTO(usuarios.get(index)));

            });

            return usuariosC;
        } catch (PersistenciaException e)
        {
            throw new NegocioException("aqui");
        }
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuario) throws NegocioException {
        try
        {
            UsuarioColeccion usuarioA = new UsuarioColeccion();
            usuarioA = convertirUsuarioDTO(usuario);
            usuarioDAO.actualizarUsuario(usuarioA);
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void agregarContacto(UsuarioDTO usuario, UsuarioDTO contacto) throws NegocioException {
        try
        {
            UsuarioColeccion usuarioA = new UsuarioColeccion();
            usuarioA = convertirUsuarioDTO(usuario);

            UsuarioColeccion usuarioB = new UsuarioColeccion();
            usuarioB = convertirUsuarioDTO(contacto);
            usuarioDAO.agregarContacto(usuarioA, usuarioB);
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(ObjectId id) throws NegocioException {
        try
        {
            usuarioDAO.eliminarUsuario(id);
        } catch (Exception e)
        {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosContactosDeUsuario(ObjectId id) throws NegocioException{
        try{
            List<UsuarioColeccion> usuarios = usuarioDAO.obtenerTodosLosContactosDeUsuario(id);
            List<UsuarioDTO> usuariosC = new ArrayList<>();

            AtomicInteger counter = new AtomicInteger(0);
            usuarios.forEach(row ->
            {
                int index = counter.getAndIncrement();
                usuariosC.add(convertirUsuarioDTO(usuarios.get(index)));

            });

            return usuariosC;
        }catch(Exception e){
            throw new NegocioException(e.getMessage());
        }
    }

    // Método para convertir UsuarioDTO a UsuarioColeccion
    private UsuarioColeccion convertirUsuarioDTO(UsuarioDTO dto) {
        UsuarioColeccion usuario = new UsuarioColeccion();

        // Asignar los valores simples
        usuario.setId(dto.getId()); // Si es necesario convertir a ObjectId
        usuario.setNombre(dto.getNombre());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setSexo(dto.getSexo());
        usuario.setContraseña(dto.getContraseña());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setTelefono(dto.getTelefono());
        usuario.setImagenPerfil(dto.getImagenPerfil());
        usuario.setContactos(dto.getContactos());

        // Convertir la dirección si está presente en el DTO
        if (dto.getDireccion() != null)
        {
            usuario.setDireccion(convertirDireccionDTO(dto.getDireccion()));
        }

        return usuario;

    }

    // Método para convertir UsuarioColeccion a UsuarioDTO
    public UsuarioDTO convertirUsuarioDTO(UsuarioColeccion usuario) {
        UsuarioDTO u = new UsuarioDTO();

        // Asignar los valores simples
        u.setId(usuario.getId()); // Si es necesario convertir a ObjectId
        u.setNombre(usuario.getNombre());
        u.setApellidoPaterno(usuario.getApellidoPaterno());
        u.setApellidoMaterno(usuario.getApellidoMaterno());
        u.setSexo(usuario.getSexo());
        u.setContraseña(usuario.getContraseña());
        u.setFechaNacimiento(usuario.getFechaNacimiento());
        u.setTelefono(usuario.getTelefono());
        u.setImagenPerfil(usuario.getImagenPerfil());
        u.setContactos(usuario.getContactos());

        // Convertir la dirección si está presente en el DTO
        if (usuario.getDireccion() != null)
        {
            u.setDireccion(convertirDireccionDTO(usuario.getDireccion()));
        }

        return u;

    }

    // Método para convertir DireccionDTO a Direccion
    private Direccion convertirDireccionDTO(DireccionDTO dto) {
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        return direccion;
    }

    // Método para convertir Direccion a DireccionDTO
    private DireccionDTO convertirDireccionDTO(Direccion dto) {
        DireccionDTO direccion = new DireccionDTO();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        return direccion;
    }

}
