/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Chat;

import DTO.ChatDTO;
import DTO.UsuarioDTO;
import LogIn.*;
import Negocio.ChatNegocio;
import Negocio.UsuarioNegocio;
import Utilerias.ImageRenderer;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.bson.types.ObjectId;

/**
 *
 * @author santi
 */
public class frmAgregarIntegrantes extends javax.swing.JFrame {

    UsuarioNegocio usuarioNegocio;
    ChatNegocio chatNegocio;
    UsuarioDTO u;
    DefaultTableModel modeloTabla;
    private ObjectId chatIdSel;
    List<UsuarioDTO> usu;

    /**
     * Creates new form LogIn
     */
    public frmAgregarIntegrantes(UsuarioNegocio usuarioNegocio, ChatNegocio chatNegocio, UsuarioDTO u) {
        this.setLocationRelativeTo(this);
        this.usuarioNegocio = usuarioNegocio;
        this.chatNegocio = chatNegocio;
        this.u = u;
        initComponents();
        configurarTablaIntegrantes();
        cargarDatosTablaIntegrantes();
        configurarComboBoxChats();
    }

    private void configurarTablaIntegrantes() {
        modeloTabla = (DefaultTableModel) tblIntegrantes.getModel();
        TableColumnModel modeloColumnas = tblIntegrantes.getColumnModel();

        // Configurar renderizador y editor de botón
        modeloTabla.setRowCount(0);
        modeloColumnas.getColumn(1).setCellRenderer(new JButtonRenderer("Detalles", new Color(178, 218, 250)));
        modeloColumnas.getColumn(1).setCellEditor(new JButtonCellEditor("Detalles", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblIntegrantes.getSelectedRow();
                if (row != -1)
                {
                    DefaultTableModel model3 = (DefaultTableModel) tblChatsFotos4.getModel();
                    model3.setColumnCount(0);
                    model3.setColumnCount(1);
                    List<UsuarioDTO> usuarios =  usu;
                    UsuarioDTO usuariosel = usuarios.get(row);
                    tblChatsFotos4.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(ByteAImagen(usuariosel.getImagenPerfil())));
                    DefaultTableModel modeloTablaSel = (DefaultTableModel) tblIntegranteSel.getModel();
                    modeloTablaSel.setRowCount(0);
                    modeloTablaSel.addRow(new Object[]
                    {
                        
                        usuariosel.getNombre(),
                        usuariosel.getTelefono(),
                    });
                }
            }
        }));
    }

    private void cargarDatosTablaIntegrantes() {
        try
        {
            List<UsuarioDTO> usuarios = usuarioNegocio.obtenerTodosLosContactosDeUsuario(u.getId());

            if (usuarios != null && !usuarios.isEmpty())
            {
                this.usu = usuarios;
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
                JOptionPane.showMessageDialog(null, "No se encontraron usuarios para mostrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NegocioException ex)
        {
            Logger.getLogger(frmAgregarContactos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al cargar contactos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIntegrantes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIntegranteSel = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        ChatsComboBox = new javax.swing.JComboBox<>();
        tbl = new javax.swing.JScrollPane();
        tblChatsFotos4 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.setBackground(new java.awt.Color(153, 230, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Integrantes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        btnCerrar.setBackground(Color.red);
        btnCerrar.setText("X");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tblIntegrantes.setModel(new javax.swing.table.DefaultTableModel(
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblIntegrantes.setToolTipText("");
        jScrollPane1.setViewportView(tblIntegrantes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 530, 220));

        tblIntegranteSel.setRowHeight(85);
        tblIntegranteSel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Nombre", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblIntegranteSel);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 530, 110));

        btnAgregar.setBackground(new java.awt.Color(66, 143, 66));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 680, 40));

        ChatsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChatsComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(ChatsComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 130, 40));

        tblChatsFotos4.setTableHeader(null);
        tblChatsFotos4.setRowHeight(105);
        tblChatsFotos4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        tbl.setViewportView(tblChatsFotos4);

        jPanel1.add(tbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 130, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Chats:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 86, 50, 30));

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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try
        {
            // Verificar si se ha seleccionado un chat
            if (chatIdSel != null)
            {
                int row = tblIntegrantes.getSelectedRow();
                if (row != -1)
                {
                    // Obtener el usuario seleccionado desde la tabla
                    List<UsuarioDTO> usuarios = usu;
                    UsuarioDTO usuariosel = usuarios.get(row);

                    // Verificar si el usuario ya es integrante del chat
                    boolean esIntegrante = chatNegocio.esIntegrante(chatIdSel, usuariosel.getId());
                    if (esIntegrante)
                    {
                        JOptionPane.showMessageDialog(this, "El usuario ya es integrante del chat.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else
                    {
                        // Llamar al método para agregar integrante al chat usando el ID del chat y el usuario seleccionado
                        chatNegocio.agregarIntegrante(chatIdSel, usuariosel.getId());

                        JOptionPane.showMessageDialog(this, "Integrante agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        // Actualizar la ventana del chat con el usuario actualizado
                        UsuarioDTO a = usuarioNegocio.obtenerUsuarioPorId(u.getId());
                        frmChat frm = new frmChat(usuarioNegocio, chatNegocio, a);
                        frm.setVisible(true);
                        this.dispose();
                    }
                } else
                {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario antes de agregarlo al chat.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else
            {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un chat antes de agregar un integrante.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NegocioException ex)
        {
            Logger.getLogger(frmAgregarContactos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al agregar integrante al chat: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    // En el constructor u otro método donde configures el JComboBox
    private void configurarComboBoxChats() {
        try
        {
            // Obtener todos los chats en los que está el usuario y agregar sus nombres al JComboBox
            List<ChatDTO> chats = chatNegocio.obtenerChatsDeUsuario(u.getId());
            ChatsComboBox.removeAllItems(); // Limpiar elementos previos del ComboBox
            for (ChatDTO chat : chats)
            {
                ChatsComboBox.addItem(chat.getNombre());
            }
        } catch (NegocioException ex)
        {
            Logger.getLogger(frmAgregarIntegrantes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ChatsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChatsComboBoxActionPerformed
        // TODO add your handling code here:
        // Obtener el índice seleccionado
        int selectedIndex = ChatsComboBox.getSelectedIndex();
        if (selectedIndex != -1)
        {
            try
            {
                // Obtener el ID del chat seleccionado usando el índice
                List<ChatDTO> chats = chatNegocio.obtenerTodosLosChats();
                ChatDTO chatSeleccionado = chats.get(selectedIndex);
                chatIdSel = chatSeleccionado.getId(); // Aquí obtienes el ID del chat seleccionado
            } catch (NegocioException ex)
            {
                Logger.getLogger(frmAgregarIntegrantes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ChatsComboBoxActionPerformed

    public BufferedImage ByteAImagen(byte[] a) {
        if (a == null || a.length == 0)
        {
            JOptionPane.showMessageDialog(this, "El arreglo de bytes de la imagen está vacío.");
            return null;
        }

        // Convierte los bytes en una imagen BufferedImage
        BufferedImage img = null;
        try
        {
            ByteArrayInputStream bis = new ByteArrayInputStream(a);
            img = ImageIO.read(bis);
        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "Error al leer la imagen de perfil: " + e.getMessage());
            e.printStackTrace();
        }

        // Ahora puedes usar la imagen (BufferedImage)
        return img;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ChatsComboBox;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane tbl;
    private javax.swing.JTable tblChatsFotos4;
    private javax.swing.JTable tblIntegranteSel;
    private javax.swing.JTable tblIntegrantes;
    // End of variables declaration//GEN-END:variables
}
