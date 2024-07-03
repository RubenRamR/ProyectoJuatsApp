
import Conexion.ConexionDB;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import InterfacesDAO.IConexionDB;
import InterfacesDAO.IUsuarioDAO;
import Negocio.UsuarioNegocio;
import com.mongodb.client.MongoDatabase;
import excepciones.NegocioException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author rramirez
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IConexionDB conexionDB = new ConexionDB();
        MongoDatabase database = conexionDB.conexion("mongodb://localhost:27017", "JuatssApp");

        // Crear instancia del DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO(database);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(usuarioDAO);

        try
        {
            List<UsuarioDTO> usuarios = usuarioNegocio.obtenerTodosLosUsuarios();

            if (usuarios.isEmpty())
            {
                System.out.println("No se encontraron usuarios.");
            } else
            {
                System.out.println("Usuarios encontrados:");
                for (UsuarioDTO usuario : usuarios)
                {
                    System.out.println(usuario);
                }
            }
        } catch (NegocioException e)
        {
            System.err.println("Error en el negocio al obtener usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
