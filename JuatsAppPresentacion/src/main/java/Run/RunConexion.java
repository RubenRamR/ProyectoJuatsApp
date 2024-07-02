/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Run;

import Conexion.ConexionDB;
import DAO.UsuarioDAO;
import InterfacesDAO.IConexionDB;
import InterfacesDAO.IUsuarioDAO;
import LogIn.frmMainMenu;
import Negocio.UsuarioNegocio;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author rramirez
 */
public class RunConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IConexionDB conexionDB = new ConexionDB();
        MongoDatabase database = conexionDB.conexion("mongodb://localhost:27017", "JuatssApp");

        UsuarioDAO usuarioDAO = new UsuarioDAO(database);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(usuarioDAO);

        frmMainMenu mMenu = new frmMainMenu(usuarioNegocio);
        mMenu.setVisible(true);
    }

}
