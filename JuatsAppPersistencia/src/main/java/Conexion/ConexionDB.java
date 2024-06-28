/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import InterfacesDAO.IConexionDB;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author rramirez
 */
public class ConexionDB implements IConexionDB {

    private final String connectionString;
    private final String dbName;

    public ConexionDB(String connectionString, String dbName) {
        this.connectionString = connectionString;
        this.dbName = dbName;
    }

    @Override
    public MongoDatabase crearConexion() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        ConnectionString cadenaConexion = new ConnectionString(connectionString);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(cadenaConexion)
                .codecRegistry(codecRegistry)
                .build();

        MongoClient dbServer = MongoClients.create(clientSettings);
        return dbServer.getDatabase(dbName);
    }
}
