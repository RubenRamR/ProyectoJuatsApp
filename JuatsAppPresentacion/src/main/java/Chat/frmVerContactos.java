/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Chat;


import DTO.UsuarioDTO;
import LogIn.*;
import Negocio.ChatNegocio;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import Negocio.UsuarioNegocio;
import Utilerias.ImageRenderer;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import excepciones.NegocioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;

/**
 *
 * @author santi
 */
public class frmVerContactos extends javax.swing.JFrame {

    UsuarioNegocio usuarioNegocio;
    ChatNegocio chatNegocio;
    UsuarioDTO u;
    DefaultTableModel modeloTabla;
    List<UsuarioDTO> usuarios;
    
    
    /**
     * Creates new form LogIn
     */
    public frmVerContactos(UsuarioNegocio usuarioNegocio, ChatNegocio chatNegocio, UsuarioDTO u) {

        this.setLocationRelativeTo(this);
        this.usuarioNegocio = usuarioNegocio;
        this.chatNegocio = chatNegocio;
        this.u = u;
        

        initComponents();
        configurarTablaContactos();
        cargarDatosTablaContactos();
        
    }


    private void configurarTablaContactos() {
        modeloTabla = (DefaultTableModel) tblContactos.getModel();
        TableColumnModel modeloColumnas = tblContactos.getColumnModel();


        // Configurar renderizador y editor de botón
        modeloTabla.setRowCount(0);
        modeloColumnas.getColumn(1).setCellRenderer(new JButtonRenderer("Detalles", new Color(178, 218, 250)));
        modeloColumnas.getColumn(1).setCellEditor(new JButtonCellEditor("Detalles", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblContactos.getSelectedRow();
                if (row != -1)
                {
                    DefaultTableModel model3 = (DefaultTableModel) tblChatsFotos3.getModel();
                    model3.setColumnCount(0);
                    model3.setColumnCount(1);
                    UsuarioDTO usuariosel = usuarios.get(row);
                    tblChatsFotos3.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(ByteAImagen(usuariosel.getImagenPerfil())));
                    DefaultTableModel modeloTablaSel = (DefaultTableModel) tblContactoSel.getModel();
                    modeloTablaSel.setRowCount(0);
                    modeloTablaSel.addRow(new Object[]
                    {
                        
                        usuariosel.getNombre(),
                        usuariosel.getApellidoPaterno(),
                        usuariosel.getApellidoMaterno(),
                        usuariosel.getTelefono(),
                        usuariosel.getDireccion().getCalle() +", "+ usuariosel.getDireccion().getNumero() +", "+ usuariosel.getDireccion().getCodigoPostal()
                    });
                }
            }
        }));
    }

    private void cargarDatosTablaContactos() {
        
        List<UsuarioDTO> usuarios = new ArrayList<>();
        List<ObjectId> contactos = u.getContactos();
        

        if(contactos == null) 
        {
                JOptionPane.showMessageDialog(null, "No tiene contactos agregados.", "Información", JOptionPane.INFORMATION_MESSAGE); 

                this.dispose();
        }
        
        contactos.forEach((row) -> {
            try {
                UsuarioDTO usuaux = usuarioNegocio.obtenerUsuarioPorId(row);
                usuarios.add(usuaux);
            } catch (NegocioException ex) {
                Logger.getLogger(frmVerContactos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.usuarios = usuarios;
            
        });
                
                if (usuarios != null && !usuarios.isEmpty())
                {
                    for (UsuarioDTO usuario : usuarios)
                    {
                        
                        modeloTabla.addRow(new Object[]
                        {
                            usuario.getNombre(),
                            
                            "Agregar"
                        });
                        
                    }
                } else
                {
                    JOptionPane.showMessageDialog(null, "No se encontraron contactos para mostrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContactos = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChatsFotos3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblContactoSel = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.setBackground(new java.awt.Color(153, 230, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Contactos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        btnCerrar.setBackground(Color.red);
        btnCerrar.setText("X");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tblContactos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Detalles"
            }
        ));
        tblContactos.setToolTipText("");
        jScrollPane1.setViewportView(tblContactos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 680, 220));

        tblChatsFotos3.setTableHeader(null);
        tblChatsFotos3.setRowHeight(105);
        tblChatsFotos3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane7.setViewportView(tblChatsFotos3);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 130, 110));

        tblContactoSel.setRowHeight(85);
        tblContactoSel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Teléfono", "Dirección"
            }
        ));
        jScrollPane2.setViewportView(tblContactoSel);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 530, 110));

        btnEliminar.setBackground(new java.awt.Color(66, 143, 66));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 680, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        frmChat frm = new frmChat(usuarioNegocio, chatNegocio, u);
        frm.show();
        this.dispose();           
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            // TODO add your handling code here:
            int row = tblContactos.getSelectedRow();
            ObjectId idAE = usuarios.get(row).getId();
            
            List<ObjectId> contactosN = u.getContactos();
            
            if(contactosN.contains(idAE))
            {
                contactosN.remove(idAE);
                u.setContactos(contactosN);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "El contacto no existe");
            }
                
            usuarioNegocio.actualizarUsuario(u);
            UsuarioDTO a = usuarioNegocio.obtenerUsuarioPorId(u.getId());
            JOptionPane.showMessageDialog(this, "El contacto se eliminó satisfactoriamente");
            frmChat frm = new frmChat(usuarioNegocio, chatNegocio, a);
            frm.show();
            this.dispose();
        } catch (NegocioException ex) {
                    JOptionPane.showMessageDialog(this, "Error en la bd");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    public BufferedImage ByteAImagen (byte[] a){
        
        // Supongamos que tienes un array de bytes llamado imageBytes
        byte[] imageBytes =  a;

        // Convierte los bytes en una imagen BufferedImage
        BufferedImage img = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            img = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ahora puedes usar la imagen (BufferedImage)
        if (img != null) {
            return img;
        } else {
                    JOptionPane.showMessageDialog(this, "Error al leer la imagen de perfil");
            return null;
        }
    
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tblChatsFotos3;
    private javax.swing.JTable tblContactoSel;
    private javax.swing.JTable tblContactos;
    // End of variables declaration//GEN-END:variables
}
