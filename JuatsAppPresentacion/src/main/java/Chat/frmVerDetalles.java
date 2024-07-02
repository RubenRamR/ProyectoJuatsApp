/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Chat;


import LogIn.*;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import Negocio.UsuarioNegocio;

/**
 *
 * @author santi
 */
public class frmVerDetalles extends javax.swing.JFrame {

    UsuarioNegocio usuarioNegocio;
    
    /**
     * Creates new form LogIn
     */
    public frmVerDetalles(UsuarioNegocio usuarioNegocio) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.usuarioNegocio = usuarioNegocio;

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
        fldTelefono = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fldImagen = new javax.swing.JTextField();
        btnEncontrarImagen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.setBackground(new java.awt.Color(153, 230, 153));

        Color customColor = new Color(35, 135, 35);
        Border lineBorder = BorderFactory.createLineBorder(customColor, 2);
        Color customColor2 = new Color(0, 113, 219);
        Color customColor3 = new Color(78, 160, 237);
        Border lineBorder2 = BorderFactory.createLineBorder(customColor2, 2);
        btnGuardar.setBorder(lineBorder2);
        btnGuardar.setBackground(customColor3);
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 180, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Detalles");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, -1));

        btnCerrar.setBackground(Color.red);
        btnCerrar.setText("X");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        fldTelefono.setBorder(lineBorder);
        fldTelefono.setBackground(new java.awt.Color(186, 219, 186));
        fldTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldTelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(fldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 230, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 230, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Imagen de Perfil ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 230, -1));

        fldImagen.setBorder(lineBorder);
        fldImagen.setBackground(new java.awt.Color(186, 219, 186));
        fldImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldImagenActionPerformed(evt);
            }
        });
        jPanel1.add(fldImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 230, 40));

        btnEncontrarImagen.setBorder(lineBorder2);
        btnEncontrarImagen.setBackground(customColor3);
        btnEncontrarImagen.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnEncontrarImagen.setText("Encontrar Imagen");
        btnEncontrarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncontrarImagenActionPerformed(evt);
            }
        });
        jPanel1.add(btnEncontrarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 110, 20));

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        frmMainMenu frm = new frmMainMenu(usuarioNegocio);
        frm.show();
        this.dispose();           
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void fldTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldTelefonoActionPerformed

    private void fldImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldImagenActionPerformed

    private void btnEncontrarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncontrarImagenActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();

        // Show the open dialog
        int result = fileChooser.showOpenDialog(this);

        // Check if a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fldImagen.setText(selectedFile.getAbsolutePath());
        }

        
    }//GEN-LAST:event_btnEncontrarImagenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEncontrarImagen;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField fldImagen;
    private javax.swing.JTextField fldTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
