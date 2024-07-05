/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LogIn;

import DTO.UsuarioDTO;
import DocsDTO.DireccionDTO;
import Negocio.ChatNegocio;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import Negocio.UsuarioNegocio;
import excepciones.NegocioException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class frmRegistrar extends javax.swing.JFrame {

    UsuarioNegocio usuarioNegocio;
    ChatNegocio chatNegocio;

    /**
     * Creates new form LogIn
     */
    public frmRegistrar(UsuarioNegocio usuarioNegocio, ChatNegocio chatNegocio) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.usuarioNegocio = usuarioNegocio;
        this.chatNegocio = chatNegocio;
        
    }

    public byte[] convertirImagenABytes(File file) throws IOException {
        // Leer el archivo de imagen en un InputStream
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();
        return bytes;
    }

    private void limpiarCampos() {
        fldNombre.setText("");
        fldApellidoPaterno.setText("");
        fldApellidoMaterno.setText("");
        fldCalle.setText("");
        fldNumero.setText("");
        fldColonia.setText("");
        fldTelefono.setText("");
        fldContraseña.setText("");
        fldSexo.setSelectedIndex(0); // Selecciona el primer ítem del combo (Masculino)
        fldFecha.setDate(null); // Limpia la fecha seleccionada
        fldImagen.setText("");
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
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        fldApellidoPaterno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fldImagen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        fldColonia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fldSexo = new javax.swing.JComboBox<>();
        fldFecha = new com.toedter.calendar.JDateChooser();
        btnEncontrarImagen = new javax.swing.JButton();
        fldTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fldNombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fldApellidoMaterno = new javax.swing.JTextField();
        fldCalle = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        fldNumero = new javax.swing.JTextField();
        fldContraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.setBackground(new java.awt.Color(153, 230, 153));

        Color customColor = new Color(35, 135, 35);
        Border lineBorder = BorderFactory.createLineBorder(customColor, 2);
        btnGuardar.setBorder(lineBorder);
        btnGuardar.setBackground(new java.awt.Color(66, 143, 66));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 180, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrar");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 700, -1));

        btnCerrar.setBackground(Color.red);
        btnCerrar.setText("X");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        fldApellidoPaterno.setBorder(lineBorder);
        fldApellidoPaterno.setBackground(new java.awt.Color(186, 219, 186));
        fldApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldApellidoPaternoActionPerformed(evt);
            }
        });
        jPanel1.add(fldApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 230, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Apellido Paterno *");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 230, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Imagen de Perfil *");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 230, -1));

        fldImagen.setBorder(lineBorder);
        fldImagen.setBackground(new java.awt.Color(186, 219, 186));
        fldImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldImagenActionPerformed(evt);
            }
        });
        jPanel1.add(fldImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 230, 60));

        jLabel11.setText("Colonia");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 50, -1));

        fldColonia.setBorder(lineBorder);
        fldColonia.setBackground(new java.awt.Color(186, 219, 186));
        fldColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldColoniaActionPerformed(evt);
            }
        });
        jPanel1.add(fldColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 230, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Dirección *");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 230, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Contraseña *");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 230, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Sexo *");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 230, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Fecha  Nacimiento *");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 230, -1));

        fldSexo.setBorder(lineBorder);
        fldSexo.setBackground(new java.awt.Color(186, 219, 186));
        fldSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino", "Robot", "Ninja", "Otro" }));
        jPanel1.add(fldSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 230, 40));

        fldFecha.setBorder(lineBorder);
        fldFecha.setBackground(new java.awt.Color(186, 219, 186));
        fldFecha.setBackground(new java.awt.Color(186, 219, 186));
        fldFecha.setForeground(new java.awt.Color(186, 219, 186));
        fldFecha.setToolTipText("");
        jPanel1.add(fldFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 230, 40));

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
        jPanel1.add(btnEncontrarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 110, 20));

        fldTelefono.setBorder(lineBorder);
        fldTelefono.setBackground(new java.awt.Color(186, 219, 186));
        fldTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldTelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(fldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 230, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("Teléfono *");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 230, -1));

        fldNombre.setBorder(lineBorder);
        fldNombre.setBackground(new java.awt.Color(186, 219, 186));
        fldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldNombreActionPerformed(evt);
            }
        });
        jPanel1.add(fldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 230, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("Nombre *");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 230, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("Apellido Materno *");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 230, -1));

        jLabel12.setText("Calle");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 50, -1));

        fldApellidoMaterno.setBorder(lineBorder);
        fldApellidoMaterno.setBackground(new java.awt.Color(186, 219, 186));
        fldApellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldApellidoMaternoActionPerformed(evt);
            }
        });
        jPanel1.add(fldApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 230, 40));

        fldCalle.setBorder(lineBorder);
        fldCalle.setBackground(new java.awt.Color(186, 219, 186));
        fldCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldCalleActionPerformed(evt);
            }
        });
        jPanel1.add(fldCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 230, 40));

        jLabel13.setText("Numero");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 50, -1));

        fldNumero.setBorder(lineBorder);
        fldNumero.setBackground(new java.awt.Color(186, 219, 186));
        fldNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldNumeroActionPerformed(evt);
            }
        });
        jPanel1.add(fldNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 230, 40));
        jPanel1.add(fldContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, 230, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        // Obtener los datos del formulario
        String nombre = fldNombre.getText().trim();
        String apellidoPaterno = fldApellidoPaterno.getText().trim();
        String apellidoMaterno = fldApellidoMaterno.getText().trim(); // Si existe un campo para el apellido materno
        String calle = fldCalle.getText().trim();
        String numero = fldNumero.getText().trim();
        String colonia = fldColonia.getText().trim();
        String telefono = fldTelefono.getText().trim();
        String contraseña = fldContraseña.getText().trim();
        String sexo = (String) fldSexo.getSelectedItem();
        LocalDate fechaNacimiento = fldFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Validar campos obligatorios (ejemplo)
        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || calle.isEmpty() || numero.isEmpty() || contraseña.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.");
            return;
        }

        // Crear un objeto UsuarioDTO con los datos del formulario
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        DireccionDTO direccion = new DireccionDTO(calle, numero, colonia);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setContraseña(contraseña);
        usuario.setSexo(sexo);
        usuario.setFechaNacimiento(fechaNacimiento);

        // Convertir la imagen a byte[]
        try
        {
            byte[] imagenBytes = convertirImagenABytes(new File(fldImagen.getText()));
            usuario.setImagenPerfil(imagenBytes);
            System.out.println(imagenBytes);
        } catch (IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al convertir la imagen a bytes.");
            return;
        }

        try {
            // Llamar al método del negocio para crear el usuario en la base de datos
            
            usuarioNegocio.crearUsuarioSinContactos(usuario);
        } catch (NegocioException ex) {
            Logger.getLogger(frmRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
            JOptionPane.showMessageDialog(this, "Usuario creado exitosamente.");
            limpiarCampos();



        frmLogIn login = new frmLogIn(usuarioNegocio, chatNegocio);
        login.setVisible(true);
        this.dispose();
    
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        frmMainMenu frm = new frmMainMenu(usuarioNegocio, chatNegocio);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void fldApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldApellidoPaternoActionPerformed

    private void fldColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldColoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldColoniaActionPerformed

    private void fldImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldImagenActionPerformed

    private void btnEncontrarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncontrarImagenActionPerformed
        // TODO add your handling code here:
        // Mostrar el selector de archivos
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        // Verificar si se seleccionó un archivo
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            fldImagen.setText(imagePath);

            // Convertir la imagen a byte[]
            try
            {
                byte[] imageData = convertirImagenABytes(selectedFile);
                // Usar el byte[] para guardar o enviar según sea necesario
            } catch (IOException ex)
            {
                ex.printStackTrace();
                // Manejar el error según sea necesario
            }
        }


    }//GEN-LAST:event_btnEncontrarImagenActionPerformed

    private void fldTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldTelefonoActionPerformed

    private void fldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldNombreActionPerformed

    private void fldApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldApellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldApellidoMaternoActionPerformed

    private void fldCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldCalleActionPerformed

    private void fldNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldNumeroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEncontrarImagen;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField fldApellidoMaterno;
    private javax.swing.JTextField fldApellidoPaterno;
    private javax.swing.JTextField fldCalle;
    private javax.swing.JTextField fldColonia;
    private javax.swing.JPasswordField fldContraseña;
    private com.toedter.calendar.JDateChooser fldFecha;
    private javax.swing.JTextField fldImagen;
    private javax.swing.JTextField fldNombre;
    private javax.swing.JTextField fldNumero;
    private javax.swing.JComboBox<String> fldSexo;
    private javax.swing.JTextField fldTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
