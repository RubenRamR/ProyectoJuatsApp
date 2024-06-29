/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LogIn;


import Chat.frmChat;
import java.awt.Color;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import negocio.JANegocio;

/**
 *
 * @author santi
 */
public class frmLogIn extends javax.swing.JFrame {

    JANegocio negocio;
    
    /**
     * Creates new form LogIn
     */
    public frmLogIn(JANegocio negocio) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(700, 520);
        this.negocio = negocio;

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
        fldContraseña = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.setBackground(new java.awt.Color(153, 230, 153));

        Color customColor = new Color(35, 135, 35);
        Border lineBorder = BorderFactory.createLineBorder(customColor, 2);
        btnGuardar.setBorder(lineBorder);
        btnGuardar.setBackground(new java.awt.Color(66, 143, 66));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnGuardar.setText("Iniciar Sesión");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 240, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 64)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Iniciar Sesión");
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
        jPanel1.add(fldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 230, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Teléfono *");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 230, -1));

        fldContraseña.setBorder(lineBorder);
        fldContraseña.setBackground(new java.awt.Color(186, 219, 186));
        fldContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldContraseñaActionPerformed(evt);
            }
        });
        jPanel1.add(fldContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 230, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Contraseña *");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 230, -1));

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

    private void fldContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldContraseñaActionPerformed

    private void fldTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldTelefonoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        frmMainMenu frm = new frmMainMenu(negocio);
        frm.show();
        this.dispose();           
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        frmChat frm = new frmChat(negocio);
        frm.show();
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField fldContraseña;
    private javax.swing.JTextField fldTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
