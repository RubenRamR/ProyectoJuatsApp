/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import Conexion.ConexionDB;
import DAO.ChatDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import InterfacesDAO.IChatDAO;
import InterfacesDAO.IConexionDB;
import InterfacesDAO.IUsuarioDAO;
import InterfacesNegocio.IUsuarioNegocio;
import Negocio.ChatNegocio;
import Negocio.UsuarioNegocio;
import com.mongodb.client.MongoDatabase;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author santi
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegocioException {
        // TODO code application logic here
                // Configurar la conexión a la base de datos
        IConexionDB conexionDB = new ConexionDB();
        MongoDatabase database = conexionDB.conexion("mongodb://localhost:27017", "JuatssApp"); 

        UsuarioDAO usuarioDAO = new UsuarioDAO(database);

        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(usuarioDAO);
        
        ChatDAO chatDAO = new ChatDAO(database);
        
        ChatNegocio chatNegocio = new ChatNegocio(chatDAO);

        
        List<UsuarioDTO> a = usuarioNegocio.obtenerTodosLosUsuarios();
        System.out.println(a.toString());
    }
    
}
