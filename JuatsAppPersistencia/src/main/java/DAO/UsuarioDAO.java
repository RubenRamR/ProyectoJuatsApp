/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Colecciones.UsuarioColeccion;
import Docs.Contacto;
import Docs.Direccion;
import InterfacesDAO.IUsuarioDAO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
                    .append("contactos", convertirContactosADocumentos(usuario.getContactos()));

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
    public UsuarioColeccion obtenerUsuarioPorId(ObjectId id) {
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

                // Manejo de imagenPerfil si existe en el documento
                if (documentoUsuario.containsKey("imagenPerfil"))
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
                    List<Document> listaDocumentosContactos = documentoUsuario.getList("contactos", Document.class);
                    List<Contacto> contactos = new ArrayList<>();
                    for (Document docContacto : listaDocumentosContactos)
                    {
                        Contacto contacto = new Contacto();
                        contacto.setNombre(docContacto.getString("nombre"));
                        // Aquí deberías manejar la imagen del contacto si también está presente
                        contacto.setImagen(docContacto.get("imagen", Binary.class).getData());
                        contactos.add(contacto);
                    }
                    usuario.setContactos(contactos);
                } else
                {
                    usuario.setContactos(null); // Asigna null si no hay contactos
                }

                return usuario;
            } else
            {
                return null; // Retorna null si no se encontró ningún documento con ese ID
            }
        } catch (Exception e)
        {
            e.printStackTrace(); // Manejo básico de excepciones, imprimirá el error en la consola
            return null; // Retorna null si ocurre algún error al consultar MongoDB
        }
    }

    @Override
    public List<UsuarioColeccion> obtenerTodosLosUsuarios() {
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
                if (documentoUsuario.containsKey("imagenPerfil"))
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
                    List<Document> listaDocumentosContactos = documentoUsuario.getList("contactos", Document.class);
                    List<Contacto> contactos = new ArrayList<>();
                    for (Document docContacto : listaDocumentosContactos)
                    {
                        Contacto contacto = new Contacto();
                        contacto.setNombre(docContacto.getString("nombre"));

                        // Manejo de imagen del contacto si existe en el documento
                        if (docContacto.containsKey("imagen"))
                        {
                            contacto.setImagen(docContacto.get("imagen", Binary.class).getData());
                        } else
                        {
                            contacto.setImagen(null); // Asigna null si no hay imagen para este contacto
                        }

                        contactos.add(contacto);
                    }
                    usuario.setContactos(contactos);
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

    public void actualizarUsuario(UsuarioColeccion usuario) {
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
                    .append("contactos", convertirContactosADocumentos(usuario.getContactos()));

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

    public void eliminarUsuario(ObjectId id) {
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

    private UsuarioColeccion convertirDocumentoAUsuario(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        String nombre = doc.getString("nombre");
        String apellidoPaterno = doc.getString("apellidoPaterno");
        String apellidoMaterno = doc.getString("apellidoMaterno");
        String sexo = doc.getString("sexo");
        LocalDate fechaNacimiento = doc.getDate("fechaNacimiento").toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        String telefono = doc.getString("telefono");
        String contraseña = doc.getString("contraseña");

        byte[] imagenPerfil = doc.get("imagenPerfil", byte[].class
        );
        Document direccionDoc = doc.get("direccion", Document.class
        );
        Direccion direccion = convertirDocumentoADireccion(direccionDoc);
        List<Document> contactosDocs = doc.getList("contactos", Document.class
        );

        List<Contacto> contactos = new ArrayList<>();
        for (Document contactoDoc : contactosDocs)
        {
            String nombreContacto = contactoDoc.getString("nombre");

            byte[] imagenContacto = contactoDoc.get("imagen", byte[].class
            );
            contactos.add(new Contacto(nombreContacto, imagenContacto));
        }

        return new UsuarioColeccion(id, nombre, apellidoPaterno, apellidoMaterno, sexo, fechaNacimiento, telefono, contraseña, imagenPerfil, direccion, contactos);
    }

    private Document convertirDireccionADocumento(Direccion direccion) {
        return new Document()
                .append("calle", direccion.getCalle())
                .append("numero", direccion.getNumero())
                .append("codigoPostal", direccion.getCodigoPostal());
    }

    private Direccion convertirDocumentoADireccion(Document doc) {
        String calle = doc.getString("calle");
        String numero = doc.getString("numero");
        String codigoPostal = doc.getString("codigoPostal");
        return new Direccion(calle, numero, codigoPostal);
    }

    private List<Document> convertirContactosADocumentos(List<Contacto> contactos) {
        List<Document> contactosDocs = new ArrayList<>();
        for (Contacto contacto : contactos)
        {
            Document doc = new Document()
                    .append("nombre", contacto.getNombre())
                    .append("imagen", contacto.getImagen());
            contactosDocs.add(doc);
        }
        return contactosDocs;
    }

}
