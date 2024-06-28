/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author rramirez
 */
public interface IConexionDB {

    public MongoDatabase crearConexion();
}
