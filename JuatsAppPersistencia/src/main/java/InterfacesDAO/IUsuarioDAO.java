/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Colecciones.UsuarioColeccion;
import excepciones.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public interface IUsuarioDAO {

    public void crearUsuario(UsuarioColeccion usuario) throws PersistenciaException;

    public void crearUsuarioSinContactos(UsuarioColeccion usuario) throws PersistenciaException;

    public UsuarioColeccion obtenerUsuarioPorId(ObjectId id) throws PersistenciaException;

    public List<UsuarioColeccion> obtenerTodosLosUsuarios() throws PersistenciaException;

    public void actualizarUsuario(UsuarioColeccion usuario) throws PersistenciaException;

    public void eliminarUsuario(ObjectId id) throws PersistenciaException;
}
