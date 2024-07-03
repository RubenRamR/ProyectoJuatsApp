/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import Colecciones.UsuarioColeccion;
import DTO.UsuarioDTO;
import excepciones.NegocioException;
import org.bson.types.ObjectId;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IUsuarioNegocio {

    void crearUsuario(UsuarioDTO usuario) throws NegocioException;
    
    void crearUsuarioSinContactos(UsuarioDTO usuario) throws NegocioException;

    UsuarioDTO obtenerUsuarioPorId(ObjectId id) throws NegocioException;

    List<UsuarioDTO> obtenerTodosLosUsuarios() throws NegocioException;
    
    public UsuarioDTO obtenerUsuarioPorCredenciales(UsuarioDTO dto) throws NegocioException;

    void actualizarUsuario(UsuarioDTO usuario) throws NegocioException;

    void eliminarUsuario(ObjectId id) throws NegocioException;
    
    public void agregarContacto(ObjectId idUsuario, ObjectId idContacto) throws NegocioException;
}
