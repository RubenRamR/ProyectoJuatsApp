/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Colecciones.UsuarioColeccion;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import Docs.Contacto;
import Docs.Direccion;
import DocsDTO.ContactoDTO;
import DocsDTO.DireccionDTO;
import InterfacesNegocio.IUsuarioNegocio;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
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
    public UsuarioColeccion obtenerUsuarioPorId(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioColeccion> obtenerTodosLosUsuarios() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuario) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarUsuario(String id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Método para convertir UsuarioDTO a UsuarioColeccion
    private UsuarioColeccion convertirUsuarioDTO(UsuarioDTO dto) {
        UsuarioColeccion usuario = new UsuarioColeccion();
        
        // Asignar los valores simples
        usuario.setId(new ObjectId(dto.getId())); // Si es necesario convertir a ObjectId
        usuario.setNombre(dto.getNombre());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setSexo(dto.getSexo());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setTelefono(dto.getTelefono());
        usuario.setImagenPerfil(dto.getImagenPerfil());
        
        // Convertir la dirección si está presente en el DTO
        if (dto.getDireccion() != null) {
            usuario.setDireccion(convertirDireccionDTO(dto.getDireccion()));
        }
        
        // Convertir los contactos si están presentes en el DTO
        if (dto.getContactos() != null) {
            usuario.setContactos(convertirListaContactoDTO(dto.getContactos()));
        }
        
        return usuario;
    }
    
    // Método para convertir DireccionDTO a Direccion
    private Direccion convertirDireccionDTO(DireccionDTO dto) {
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        return direccion;
    }
    
    // Método para convertir una lista de ContactoDTO a una lista de Contacto
    private List<Contacto> convertirListaContactoDTO(List<ContactoDTO> dtos) {
        List<Contacto> contactos = new ArrayList<>();
        for (ContactoDTO dto : dtos) {
            Contacto contacto = new Contacto();
            contacto.setNombre(dto.getNombre());
            contacto.setImagen(dto.getImagen());

            contactos.add(contacto);
        }
        return contactos;
    }

}
