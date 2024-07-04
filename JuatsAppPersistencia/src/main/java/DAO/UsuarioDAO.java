/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Colecciones.UsuarioColeccion;
import Docs.Direccion;
import InterfacesDAO.IUsuarioDAO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

/**
 *
 * @author rramirez
 */
public class UsuarioDAO implements IUsuarioDAO {

    private final MongoCollection<Document> coleccion;

    public UsuarioDAO(MongoDatabase database) {
        this.coleccion = database.getCollection("Usuario");
    }

    @Override
    public void crearUsuario(UsuarioColeccion usuario) throws PersistenciaException {
        try
        {
            List<ObjectId> contactos = usuario.getContactos();

            Document doc = new Document()
                    .append("nombre", usuario.getNombre())
                    .append("apellidoPaterno", usuario.getApellidoPaterno())
                    .append("apellidoMaterno", usuario.getApellidoMaterno())
                    .append("sexo", usuario.getSexo())
                    .append("fechaNacimiento", java.sql.Date.valueOf(usuario.getFechaNacimiento()))
                    .append("telefono", usuario.getTelefono())
                    .append("contraseña", usuario.getContraseña())
                    .append("imagenPerfil", usuario.getImagenPerfil())
                    .append("direccion", convertirDireccionADocumento(usuario.getDireccion()))
                    .append("contactos", contactos);

            coleccion.insertOne(doc);

        } catch (Exception e)
        {
            throw new PersistenciaException("Error al crear el usuario en la base de datos", e);
        }
    }

    @Override
    public void crearUsuarioSinContactos(UsuarioColeccion usuario) throws PersistenciaException {

        Document doc = new Document()
                .append("nombre", usuario.getNombre())
                .append("apellidoPaterno", usuario.getApellidoPaterno())
                .append("apellidoMaterno", usuario.getApellidoMaterno())
                .append("sexo", usuario.getSexo())
                .append("fechaNacimiento", usuario.getFechaNacimiento())
                .append("telefono", usuario.getTelefono())
                .append("contraseña", usuario.getContraseña())
                .append("imagenPerfil", usuario.getImagenPerfil())
                .append("direccion", convertirDireccionADocumento(usuario.getDireccion()));
        try
        {
            coleccion.insertOne(doc);
        } catch (Exception e)
        {
            throw new PersistenciaException("Error al crear el usuario sin contactos", e);
        }
    }

    @Override
    public UsuarioColeccion obtenerUsuarioPorId(ObjectId id) throws PersistenciaException {
        try
        {
            Bson filtro = Filters.eq("_id", id);
            Document documentoUsuario = coleccion.find(filtro).first();

            if (documentoUsuario != null)
            {
                UsuarioColeccion usuario = new UsuarioColeccion();
                usuario.setId(documentoUsuario.getObjectId("_id"));
                usuario.setNombre(documentoUsuario.getString("nombre"));
                usuario.setApellidoPaterno(documentoUsuario.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(documentoUsuario.getString("apellidoMaterno"));
                usuario.setSexo(documentoUsuario.getString("sexo"));
                usuario.setFechaNacimiento(documentoUsuario.getDate("fechaNacimiento").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                usuario.setTelefono(documentoUsuario.getString("telefono"));
                usuario.setContraseña(documentoUsuario.getString("contraseña"));

                if (documentoUsuario.containsKey("imagenPerfil"))
                {
                    Binary imagenPerfil = documentoUsuario.get("imagenPerfil", Binary.class);
                    if (imagenPerfil != null)
                    {
                        usuario.setImagenPerfil(imagenPerfil.getData());
                    } else
                    {
                        usuario.setImagenPerfil(null);
                    }
                } else
                {
                    usuario.setImagenPerfil(null);
                }

                if (documentoUsuario.containsKey("direccion"))
                {
                    Document docDireccion = documentoUsuario.get("direccion", Document.class);
                    if (docDireccion != null)
                    {
                        Direccion direccion = new Direccion();
                        direccion.setCalle(docDireccion.getString("calle"));
                        direccion.setNumero(docDireccion.getString("numero"));
                        direccion.setCodigoPostal(docDireccion.getString("codigoPostal"));
                        usuario.setDireccion(direccion);
                    } else
                    {
                        usuario.setDireccion(null);
                    }
                } else
                {
                    usuario.setDireccion(null);
                }

                if (documentoUsuario.containsKey("contactos"))
                {
                    List<ObjectId> listaContactos = documentoUsuario.getList("contactos", ObjectId.class);
                    usuario.setContactos(listaContactos);
                } else
                {
                    usuario.setContactos(null);
                }

                return usuario;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UsuarioColeccion obtenerUsuarioPorCredenciales(String telefono, String contraseña) throws PersistenciaException {
        try
        {
            Bson filtro = Filters.eq("telefono", telefono);
            Bson filtro2 = Filters.eq("contraseña", contraseña);
            Bson combinedFilter = Filters.and(filtro, filtro2);
            Document documentoUsuario = coleccion.find(combinedFilter).first();

            if (documentoUsuario != null)
            {
                UsuarioColeccion usuario = new UsuarioColeccion();
                usuario.setId(documentoUsuario.getObjectId("_id"));
                usuario.setNombre(documentoUsuario.getString("nombre"));
                usuario.setApellidoPaterno(documentoUsuario.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(documentoUsuario.getString("apellidoMaterno"));
                usuario.setSexo(documentoUsuario.getString("sexo"));
                usuario.setFechaNacimiento(documentoUsuario.getDate("fechaNacimiento").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                usuario.setTelefono(documentoUsuario.getString("telefono"));
                usuario.setContraseña(documentoUsuario.getString("contraseña"));

                if (documentoUsuario.containsKey("imagenPerfil"))
                {
                    Binary imagenPerfil = documentoUsuario.get("imagenPerfil", Binary.class);
                    if (imagenPerfil != null)
                    {
                        usuario.setImagenPerfil(imagenPerfil.getData());
                    } else
                    {
                        usuario.setImagenPerfil(null);
                    }
                } else
                {
                    usuario.setImagenPerfil(null);
                }

                if (documentoUsuario.containsKey("direccion"))
                {
                    Document docDireccion = documentoUsuario.get("direccion", Document.class);
                    if (docDireccion != null)
                    {
                        Direccion direccion = new Direccion();
                        direccion.setCalle(docDireccion.getString("calle"));
                        direccion.setNumero(docDireccion.getString("numero"));
                        direccion.setCodigoPostal(docDireccion.getString("codigoPostal"));
                        usuario.setDireccion(direccion);
                    } else
                    {
                        usuario.setDireccion(null);
                    }
                } else
                {
                    usuario.setDireccion(null);
                }

                if (documentoUsuario.containsKey("contactos"))
                {
                    List<ObjectId> listaContactos = documentoUsuario.getList("contactos", ObjectId.class);
                    usuario.setContactos(listaContactos);
                } else
                {
                    usuario.setContactos(null);
                }

                return usuario;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UsuarioColeccion> obtenerTodosLosUsuarios() throws PersistenciaException {
        List<UsuarioColeccion> usuarios = new ArrayList<>();
        try
        {
            // Realiza la consulta para obtener todos los documentos de usuarios
            FindIterable<Document> documentosUsuarios = coleccion.find();

            // Itera sobre los documentos obtenidos
            for (Document documentoUsuario : documentosUsuarios)
            {
                UsuarioColeccion usuario = new UsuarioColeccion();
                usuario.setId(documentoUsuario.getObjectId("_id"));
                usuario.setNombre(documentoUsuario.getString("nombre"));
                usuario.setApellidoPaterno(documentoUsuario.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(documentoUsuario.getString("apellidoMaterno"));
                usuario.setSexo(documentoUsuario.getString("sexo"));
                usuario.setFechaNacimiento(documentoUsuario.getDate("fechaNacimiento").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                usuario.setTelefono(documentoUsuario.getString("telefono"));
                usuario.setContraseña(documentoUsuario.getString("contraseña"));

                // Manejo de imagenPerfil si existe en el documento
                if (documentoUsuario.containsKey("imagenPerfil") && documentoUsuario.get("imagenPerfil") != null)
                {
                    usuario.setImagenPerfil(documentoUsuario.get("imagenPerfil", Binary.class).getData());
                } else
                {
                    usuario.setImagenPerfil(null); // Asigna null si no hay imagenPerfil
                }

                // Manejo de dirección si existe en el documento
                if (documentoUsuario.containsKey("direccion"))
                {
                    Document docDireccion = documentoUsuario.get("direccion", Document.class);
                    Direccion direccion = new Direccion();
                    direccion.setCalle(docDireccion.getString("calle"));
                    direccion.setNumero(docDireccion.getString("numero"));
                    direccion.setCodigoPostal(docDireccion.getString("codigoPostal"));
                    usuario.setDireccion(direccion);
                } else
                {
                    usuario.setDireccion(null); // Asigna null si no hay dirección
                }

                // Manejo de contactos si existe en el documento
                if (documentoUsuario.containsKey("contactos"))
                {
                    List<ObjectId> listaIdContactos = documentoUsuario.getList("contactos", ObjectId.class);
                    usuario.setContactos(listaIdContactos);
                } else
                {
                    usuario.setContactos(null); // Asigna null si no hay contactos
                }

                usuarios.add(usuario);
            }
        } catch (Exception e)
        {
            e.printStackTrace(); // Manejo básico de excepciones, imprimirá el error en la consola
            // Puedes manejar la excepción de otra forma según tus necesidades
        }
        return usuarios;
    }

    @Override
    public void actualizarUsuario(UsuarioColeccion usuario) throws PersistenciaException {
        try
        {
            // Crea el filtro para encontrar el usuario por su ObjectId
            ObjectId idUsuario = usuario.getId();
            Bson filtro = Filters.eq("_id", idUsuario);

            // Crea el documento con los nuevos valores a actualizar
            Document docActualizacion = new Document()
                    .append("nombre", usuario.getNombre())
                    .append("apellidoPaterno", usuario.getApellidoPaterno())
                    .append("apellidoMaterno", usuario.getApellidoMaterno())
                    .append("sexo", usuario.getSexo())
                    .append("fechaNacimiento", java.sql.Date.valueOf(usuario.getFechaNacimiento()))
                    .append("telefono", usuario.getTelefono())
                    .append("contraseña", usuario.getContraseña())
                    .append("imagenPerfil", usuario.getImagenPerfil())
                    .append("direccion", convertirDireccionADocumento(usuario.getDireccion()))
                    .append("contactos", usuario.getContactos());

            // Realiza la actualización en la base de datos
            UpdateResult resultado = coleccion.updateOne(filtro, new Document("$set", docActualizacion));

            // Verifica si se actualizó correctamente
            if (resultado.getModifiedCount() > 0)
            {
                System.out.println("Usuario actualizado correctamente");
            } else
            {
                System.out.println("No se encontró ningún usuario para actualizar");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarUsuario(ObjectId id) throws PersistenciaException {
    public void agregarContacto(UsuarioColeccion usuario, UsuarioColeccion contacto)throws PersistenciaException {
        try
        {
            // Crea el filtro para encontrar el usuario por su ObjectId
            ObjectId idUsuario = usuario.getId();
            Bson filtro = Filters.eq("_id", idUsuario);

            // Crea el documento con los nuevos valores a actualizar
            ObjectId aux = contacto.getId();
            ObjectId aux2 = usuario.getId();
            List<ObjectId> contactosact = usuario.getContactos();
            if (aux.equals(aux2)){JOptionPane.showMessageDialog(null, "No te puedes agregar a ti mismo como contacto");return;}
            if (contactosact == null)
            {
                List<ObjectId> a = new ArrayList<>();
                a.add(contacto.getId());
                            Document docActualizacion = new Document()
                    .append("nombre", usuario.getNombre())
                    .append("apellidoPaterno", usuario.getApellidoPaterno())
                    .append("apellidoMaterno", usuario.getApellidoMaterno())
                    .append("sexo", usuario.getSexo())
                    .append("fechaNacimiento", java.sql.Date.valueOf(usuario.getFechaNacimiento()))
                    .append("telefono", usuario.getTelefono())
                    .append("contraseña", usuario.getContraseña())
                    .append("imagenPerfil", usuario.getImagenPerfil())
                    .append("direccion", convertirDireccionADocumento(usuario.getDireccion()))
                    .append("contactos", a);
            // Realiza la actualización en la base de datos
            UpdateResult resultado = coleccion.updateOne(filtro, new Document("$set", docActualizacion));

            // Verifica si se actualizó correctamente
            if (resultado.getModifiedCount() > 0)
            {
                System.out.println("Primer contacto agregado correctamente");
                return;
            } else
            {
                System.out.println("No se encontró ningún contacto nuevo");
                
            }
            }
            if (contactosact.contains(aux)){JOptionPane.showMessageDialog(null, "El contacto ya existe");return;}
            if (contactosact != null){
            contactosact.add(contacto.getId());
            Document docActualizacion = new Document()
                    .append("nombre", usuario.getNombre())
                    .append("apellidoPaterno", usuario.getApellidoPaterno())
                    .append("apellidoMaterno", usuario.getApellidoMaterno())
                    .append("sexo", usuario.getSexo())
                    .append("fechaNacimiento", java.sql.Date.valueOf(usuario.getFechaNacimiento()))
                    .append("telefono", usuario.getTelefono())
                    .append("contraseña", usuario.getContraseña())
                    .append("imagenPerfil", usuario.getImagenPerfil())
                    .append("direccion", convertirDireccionADocumento(usuario.getDireccion()))
                    .append("contactos", contactosact);

            // Realiza la actualización en la base de datos
            UpdateResult resultado = coleccion.updateOne(filtro, new Document("$set", docActualizacion));

            // Verifica si se actualizó correctamente
            if (resultado.getModifiedCount() > 0)
            {
                System.out.println("Contacto nuevo agregado correctamente");
            } else
            {
                System.out.println("No se encontró ningún contacto");
            }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void eliminarUsuario(ObjectId id)throws PersistenciaException {
        try
        {
            // Crea el filtro para encontrar el usuario por su ObjectId
            Bson filtro = Filters.eq("_id", id);

            // Elimina el documento que cumpla con el filtro
            coleccion.deleteOne(filtro);

            System.out.println("Usuario eliminado correctamente");
        } catch (Exception e)
        {
            e.printStackTrace(); // Manejo básico de excepciones, imprimirá el error en la consola
        }
    }

    @Override
    public void agregarContacto(ObjectId idUsuario, ObjectId idContacto) {
        Bson filtro = Filters.eq("_id", idUsuario);
        Bson actualizacion = Updates.addToSet("contactos", idContacto);
        coleccion.updateOne(filtro, actualizacion);
    }

    @Override
    public void eliminarContacto(ObjectId idUsuario, ObjectId idContacto) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Document convertirDireccionADocumento(Direccion direccion) {
        return new Document()
                .append("calle", direccion.getCalle())
                .append("numero", direccion.getNumero())
                .append("codigoPostal", direccion.getCodigoPostal());
    }

}
