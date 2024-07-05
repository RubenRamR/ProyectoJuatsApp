/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Chat;

import DTO.ChatDTO;
import DTO.UsuarioDTO;
import DocsDTO.MensajeDTO;
import LogIn.*;
import Negocio.ChatNegocio;
import Utilerias.ImageRenderer;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import excepcion.ExcepcionPresentacion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import javax.swing.table.TableColumnModel;
import Negocio.UsuarioNegocio;
import Utilerias.RowImageRenderer;
import Utilerias.UpdateEverySecond;
import excepciones.NegocioException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.util.TimerTask;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author santi
 */
public class frmChat extends javax.swing.JFrame {

    UsuarioNegocio usuarioNegocio;
    ChatNegocio chatNegocio;
    UsuarioDTO u;
    private int paginaChat=1;
    private int LIMITEChat=3;    
    private int paginaMensaje=1;
    private int LIMITEMensaje=3;    
    public Timer timer;
    List<ChatDTO> chats;
    ChatDTO chatC;
    byte[] imagen;

    /**
     * Creates new form LogIn
     */
    public frmChat(UsuarioNegocio usuarioNegocio, ChatNegocio chatNegocio, UsuarioDTO u){
        this.u = u;       
        initComponents();
        cargarConfiguracionInicialTablaMiPerfil();
        this.setLocationRelativeTo(this);
        this.usuarioNegocio = usuarioNegocio;
        this.chatNegocio = chatNegocio;

        try {
            cargarEnTabla();
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(frmChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lectorTablaChats();
        lectorTablaChat();

    }

    public void lectorTablaChats(){
    
            tblChats.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Ignore extra messages
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tblChats.getSelectedRow();
                    if (selectedRow != -1) {
                        // React to the row selection
                        System.out.println("Selected Row: " + selectedRow);
                        try {
                            cargarEnTablaChat(chats.get(selectedRow));
                        } catch (ExcepcionPresentacion ex) {
                            Logger.getLogger(frmChat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });    
        
    }
    
    public void lectorTablaChat(){
    
            tblChat.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Ignore extra messages
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tblChat.getSelectedRow();
                    if (selectedRow != -1) {
                        // React to the row selection
                        if(chatC.getMensajes().get(selectedRow).getImagenOpcional() == null){return;}
                       
                        System.out.println(chatC.getMensajes().get(selectedRow).getImagenOpcional());
                        frmFoto frm = new frmFoto(chatC.getMensajes().get(selectedRow).getImagenOpcional());
                        frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        frm.show();
                        }
                    }
                }
            
        });    
        
    }    

    private void cargarConfiguracionInicialTablaMiPerfil() {
        tblMiFoto.setRowHeight(0, 67);
        byte[] a = u.getImagenPerfil();
        tblMiFoto.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(ByteAImagen(a)));
        
    }

    private void cargarEnTabla()  throws ExcepcionPresentacion{

        try {
            int indiceInicio = (paginaChat - 1) * LIMITEChat;
            List<ChatDTO> todas = chatNegocio.obtenerTodosLosChatsUsuario(u.getId());
            
            int indiceFin = Math.min(indiceInicio + LIMITEChat, todas.size());

            List<ChatDTO> enPagina = obtenerPagina(indiceInicio, indiceFin, todas);
            llenarTabla(enPagina);
            actualizarNumeroDePagina();
            this.chats = enPagina;
        } catch (NegocioException ex) {
            Logger.getLogger(frmChat.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }    
    
    private void cargarEnTablaChat(ChatDTO chatA)  throws ExcepcionPresentacion{    
    DefaultTableModel modeloTabla = (DefaultTableModel) this.tblChat.getModel();
         
    // Clear existing rows
    modeloTabla.setRowCount(0);
    if (chatA != null) {
        this.chatC = chatA;
        chatA.getMensajes().forEach(row -> {

                Object[] fila = new Object[3];

                fila[0] =row.getTextoMensaje();
                if (row.getImagenOpcional() != null){
                    fila[1] = "Click para ver la imagen";            
                }
                else
                {
                    fila[1] = "El mensaje no contiene imagen";
                }

                fila[2] =row.getFechaHoraRegistro();
                
                
                modeloTabla.addRow(fila);     

            
        });
    }
    }    
    
    private List<ChatDTO> obtenerPagina(int indiceInicio, int indiceFin, List<ChatDTO> todasaux) throws ExcepcionPresentacion {
        List<ChatDTO> todas = todasaux;
        List<ChatDTO> todasLasPaginas = new ArrayList<>();
        indiceFin = Math.min(indiceFin, todas.size());
        for (int i = indiceInicio; i < indiceFin; i++) {
            todasLasPaginas.add(todas.get(i));
        }
        return todasLasPaginas;
    }
    
    private void actualizarNumeroDePagina() {
    NumeroDePagina.setText(""+paginaChat);
    }
            
    
    private void llenarTabla(List<ChatDTO> lista) {
    DefaultTableModel modeloTabla = (DefaultTableModel) this.tblChats.getModel();
         
    // Clear existing rows
    tblChats.setRowHeight(235);
    modeloTabla.setRowCount(0);
        if (lista != null) {
                if(0 < lista.size()){tblChatsFotos3.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(ByteAImagen(lista.get(0).getImagen())));} else {tblChatsFotos3.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer("placeholder.jpg"));}
                if(1 < lista.size()){tblChatsFotos4.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(ByteAImagen(lista.get(1).getImagen())));} else {tblChatsFotos4.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer("placeholder.jpg"));}
                if(2 < lista.size()){tblChatsFotos2.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(ByteAImagen(lista.get(2).getImagen())));} else {tblChatsFotos2.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer("placeholder.jpg"));}
        lista.forEach(row -> {

                Object[] fila = new Object[2];

                fila[0] =row.getNombre();
                modeloTabla.addRow(fila);     

            
        });
    }
    }
        
    
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
    
    public byte[] convertirImagenABytes(File file) throws IOException {
        // Leer el archivo de imagen en un InputStream
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();
        return bytes;
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
        btnCerrar = new javax.swing.JButton();
        fldMensaje = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnPerfil = new javax.swing.JButton();
        btnContactos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnEncontrarImagen = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChats = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChat = new javax.swing.JTable();
        btnEnviarMensaje = new javax.swing.JButton();
        btnNuevoChat = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMiFoto = new javax.swing.JTable();
        btnDetalles = new javax.swing.JButton();
        btnAgregarContactos = new javax.swing.JButton();
        btnAgregarIntegrantes = new javax.swing.JButton();
        NumeroDePagina = new javax.swing.JTextField();
        btnSiguienteChat = new javax.swing.JButton();
        btnPrevioChat = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblChatsFotos2 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblChatsFotos3 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblChatsFotos4 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblFotoMensaje = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.setBackground(new java.awt.Color(153, 230, 153));

        btnCerrar.setBackground(Color.red);
        btnCerrar.setText("X");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        Color customColor = new Color(35, 135, 35);
        Border lineBorder = BorderFactory.createLineBorder(customColor, 2);
        fldMensaje.setBorder(lineBorder);
        fldMensaje.setBackground(new java.awt.Color(186, 219, 186));
        fldMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldMensajeActionPerformed(evt);
            }
        });
        jPanel1.add(fldMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 700, 350, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setText("Foto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 730, 180, 20));

        btnPerfil.setBorder(lineBorder);
        btnPerfil.setBackground(new java.awt.Color(66, 143, 66));
        btnPerfil.setText("Ver Perfil");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });
        jPanel1.add(btnPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 110, 30));

        btnContactos.setBorder(lineBorder);
        btnContactos.setBackground(new java.awt.Color(66, 143, 66));
        btnContactos.setText("Ver Contactos");
        btnContactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactosActionPerformed(evt);
            }
        });
        jPanel1.add(btnContactos, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 110, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Chats");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 20));

        Color customColor2 = new Color(0, 113, 219);
        Color customColor3 = new Color(78, 160, 237);
        Border lineBorder2 = BorderFactory.createLineBorder(customColor2, 2);
        btnEncontrarImagen.setBorder(lineBorder2);
        btnEncontrarImagen.setBackground(customColor3);
        btnEncontrarImagen.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnEncontrarImagen.setText("Encontrar Imagen");
        btnEncontrarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncontrarImagenActionPerformed(evt);
            }
        });
        jPanel1.add(btnEncontrarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 790, 110, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel4.setText("Mensaje *");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 680, 180, 20));

        tblChats.setTableHeader(null);
        tblChats.setAutoCreateRowSorter(true);
        tblChats.setBackground(new java.awt.Color(66, 143, 66));
        tblChats.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 143, 66)));
        tblChats.setForeground(new java.awt.Color(0, 0, 0));
        tblChats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {""},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChats.setToolTipText("");
        tblChats.setRequestFocusEnabled(false);
        tblChats.setSelectionForeground(new java.awt.Color(66, 143, 66));
        jScrollPane1.setViewportView(tblChats);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 120, 710));

        tblChat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mensaje", "Foto", "Fecha"
            }
        ));
        jScrollPane2.setViewportView(tblChat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 590, 580));

        btnEnviarMensaje.setBorder(lineBorder);
        btnEnviarMensaje.setBackground(new java.awt.Color(66, 143, 66));
        btnEnviarMensaje.setText("Enviar Mensaje");
        btnEnviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMensajeActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnviarMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 700, 230, 80));

        btnNuevoChat.setBorder(lineBorder);
        btnNuevoChat.setBackground(new java.awt.Color(66, 143, 66));
        btnNuevoChat.setText("Nuevo chat");
        btnNuevoChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoChatActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 140, 30));

        tblMiFoto        .setTableHeader(null);
        tblMiFoto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Mi foto de perfil"
            }
        ));
        jScrollPane3.setViewportView(tblMiFoto);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 140, 70));

        btnDetalles.setBorder(lineBorder);
        btnDetalles.setBackground(new java.awt.Color(66, 143, 66));
        btnDetalles.setText("Ver Detalles");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        jPanel1.add(btnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 160, 30));

        btnAgregarContactos.setBorder(lineBorder);
        btnAgregarContactos.setBackground(new java.awt.Color(66, 143, 66));
        btnAgregarContactos.setText("Agregar contactos");
        btnAgregarContactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarContactosActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarContactos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 140, 30));

        btnAgregarIntegrantes.setBorder(lineBorder);
        btnAgregarIntegrantes.setBackground(new java.awt.Color(66, 143, 66));
        btnAgregarIntegrantes.setText("Agregar Integrantes");
        btnAgregarIntegrantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarIntegrantesActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarIntegrantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 160, 30));

        NumeroDePagina.setEditable(false);
        NumeroDePagina.setBackground(new java.awt.Color(186, 219, 186));
        NumeroDePagina.setForeground(new java.awt.Color(0, 0, 0));
        NumeroDePagina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 820, 40, 30));

        btnSiguienteChat.setBackground(new java.awt.Color(186, 219, 186));
        btnSiguienteChat.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguienteChat.setText("->");
        btnSiguienteChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteChatActionPerformed(evt);
            }
        });
        jPanel1.add(btnSiguienteChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 820, 90, 30));

        btnPrevioChat.setBackground(new java.awt.Color(186, 219, 186));
        btnPrevioChat.setForeground(new java.awt.Color(0, 0, 0));
        btnPrevioChat.setText("<-");
        btnPrevioChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevioChatActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrevioChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 820, 90, 30));

        tblChatsFotos2.setTableHeader(null);
        tblChatsFotos2.setRowHeight(235);
        tblChatsFotos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblChatsFotos2);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 130, 240));

        tblChatsFotos3.setTableHeader(null);
        tblChatsFotos3.setRowHeight(227);
        tblChatsFotos3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblChatsFotos3);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 130, 230));

        tblChatsFotos4.setTableHeader(null);
        tblChatsFotos4.setRowHeight(235);
        tblChatsFotos4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblChatsFotos4);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 130, 240));

        tblFotoMensaje.setTableHeader(null);
        tblFotoMensaje.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Mi foto de perfil"
            }
        ));
        jScrollPane4.setViewportView(tblFotoMensaje);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 750, 210, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fldMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldMensajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldMensajeActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        frmMainMenu frm = new frmMainMenu(usuarioNegocio, chatNegocio);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        // TODO add your handling code here:
        frmPerfil frm = new frmPerfil(usuarioNegocio, chatNegocio, u);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnContactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactosActionPerformed
        // TODO add your handling code here:
        frmVerContactos frm = new frmVerContactos(usuarioNegocio, chatNegocio, u);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnContactosActionPerformed

    private void btnEncontrarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncontrarImagenActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();

        // Show the open dialog
        int result = fileChooser.showOpenDialog(this);

        // Check if a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();

                tblFotoMensaje.setRowHeight(0, 125);
                tblFotoMensaje.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(ByteAImagen(convertirImagenABytes(selectedFile))));
                this.imagen = convertirImagenABytes(selectedFile);
            } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al convertir la imagen a bytes.");
                                

            }
        }
    }//GEN-LAST:event_btnEncontrarImagenActionPerformed

    private void btnEnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMensajeActionPerformed

        try {
            if (chatC != null){
            ChatDTO chatNM = this.chatC;
            MensajeDTO mensaje = new MensajeDTO();
            List<MensajeDTO> mensajes = chatNM.getMensajes();
            
            mensaje.setTextoMensaje(fldMensaje.getText());
            mensaje.setFechaHoraRegistro(LocalDateTime.now());
            if (imagen != null){
            mensaje.setImagenOpcional(imagen);
            }
            mensajes.add(mensaje);
            
            chatNM.setMensajes(mensajes);
            
            chatNegocio.actualizarChat(chatNM);
            }
            else
            {
                      JOptionPane.showMessageDialog(this, "Selecciona un mensaje primero"); 
            }
            
        } catch (NegocioException ex) {
           JOptionPane.showMessageDialog(this, "Error al mandar mensaje :(");
        }
    }//GEN-LAST:event_btnEnviarMensajeActionPerformed

    private void btnNuevoChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoChatActionPerformed
        // TODO add your handling code here:
        frmNuevoChat frm = new frmNuevoChat(usuarioNegocio, chatNegocio, u);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnNuevoChatActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void btnAgregarContactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarContactosActionPerformed
        // TODO add your handling code here:
        frmAgregarContactos frm = new frmAgregarContactos(usuarioNegocio, chatNegocio, u);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnAgregarContactosActionPerformed

    private void btnAgregarIntegrantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarIntegrantesActionPerformed
        // TODO add your handling code here:
        frmAgregarIntegrantes frm = new frmAgregarIntegrantes(usuarioNegocio, chatNegocio, u);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnAgregarIntegrantesActionPerformed


    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_NumeroDePaginaActionPerformed

    private void btnSiguienteChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteChatActionPerformed
        // TODO add your handling code here:
        try{
            List<ChatDTO> todas = chatNegocio.obtenerTodosLosChatsUsuario(u.getId());

            int totalPaginas = (int) Math.ceil((double) todas.size() / LIMITEChat);

            if (paginaChat < totalPaginas) {
                DefaultTableModel model2 = (DefaultTableModel) tblChatsFotos2.getModel();
                model2.setColumnCount(0);
                model2.setColumnCount(1);
                DefaultTableModel model3 = (DefaultTableModel) tblChatsFotos3.getModel();
                model3.setColumnCount(0);
                model3.setColumnCount(1);
                DefaultTableModel model4 = (DefaultTableModel) tblChatsFotos4.getModel();
                model4.setColumnCount(0);
                model4.setColumnCount(1);

                paginaChat++;
                cargarEnTabla();
                actualizarNumeroDePagina();
            } else {

                JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
            }}
         catch (ExcepcionPresentacion ex) {
            JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (NegocioException ex) {
            Logger.getLogger(frmChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }//GEN-LAST:event_btnSiguienteChatActionPerformed

    private void btnPrevioChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevioChatActionPerformed
        // TODO add your handling code here:

                if (paginaChat > 1) {
            try {
                DefaultTableModel model2 = (DefaultTableModel) tblChatsFotos2.getModel();
                model2.setColumnCount(0);
                model2.setColumnCount(1);
                DefaultTableModel model3 = (DefaultTableModel) tblChatsFotos3.getModel();
                model3.setColumnCount(0);
                model3.setColumnCount(1);
                DefaultTableModel model4 = (DefaultTableModel) tblChatsFotos4.getModel();
                model4.setColumnCount(0);
                model4.setColumnCount(1);
                paginaChat--;
                cargarEnTabla();
                actualizarNumeroDePagina();
            } catch (ExcepcionPresentacion ex) {
                JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    
    }//GEN-LAST:event_btnPrevioChatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JButton btnAgregarContactos;
    private javax.swing.JButton btnAgregarIntegrantes;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnContactos;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btnEncontrarImagen;
    private javax.swing.JButton btnEnviarMensaje;
    private javax.swing.JButton btnNuevoChat;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JButton btnPrevioChat;
    private javax.swing.JButton btnSiguienteChat;
    private javax.swing.JTextField fldMensaje;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable tblChat;
    private javax.swing.JTable tblChats;
    private javax.swing.JTable tblChatsFotos2;
    private javax.swing.JTable tblChatsFotos3;
    private javax.swing.JTable tblChatsFotos4;
    private javax.swing.JTable tblFotoMensaje;
    private javax.swing.JTable tblMiFoto;
    // End of variables declaration//GEN-END:variables
}
