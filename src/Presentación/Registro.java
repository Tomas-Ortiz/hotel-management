package Presentación;

import Negocio.Entidades.Usuario;
import Negocio.NegocioUsuario;
import Negocio.UtilidadJFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Registro extends javax.swing.JFrame {

    UtilidadJFrame utilidadJframe;
    NegocioUsuario negocioUsuario;

    public Registro() {

        initComponents();

        negocioUsuario = new NegocioUsuario();
        //Singleton
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Registrarse", this);
        iniciarPanelTitulo();
        iniciarPanelRegistro();
    }

    private void iniciarPanelTitulo() {

        JPanel jpTitulo = new JPanel();

        this.lblTituloHotel.setHorizontalAlignment(SwingConstants.CENTER);
        jpTitulo.add(lblTituloHotel);

        utilidadJframe.setConfiguracionPanelTitulo(jpTitulo);

        this.getContentPane().add(jpTitulo, BorderLayout.PAGE_START);
    }

    private void iniciarPanelRegistro() {

        JPanel jpRegistro = new JPanel(new GridLayout(17, 1, 0, 0));

        List<JComponent> componentesPanel = new ArrayList<JComponent>();

        utilidadJframe.setTamañoPanelRegistro(jpRegistro);
        utilidadJframe.setConfiguracionPanel(jpRegistro);

        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblO.setHorizontalAlignment(SwingConstants.CENTER);

        agregarComponentesArray(componentesPanel);

        utilidadJframe.agregarComponentesPanelRegistro(jpRegistro, componentesPanel);

        this.getContentPane().add(jpRegistro, BorderLayout.CENTER);
    }

    private void agregarComponentesArray(List<JComponent> componentesPanel) {

        JSeparator separatorRegistro = new JSeparator(SwingConstants.HORIZONTAL);
        componentesPanel.add(lblRegistro);
        componentesPanel.add(separatorRegistro);
        componentesPanel.add(lblNombre);
        componentesPanel.add(jtfNombre);
        componentesPanel.add(lblApellido);
        componentesPanel.add(jtfApellido);
        componentesPanel.add(lblEmail);
        componentesPanel.add(jtfEmail);
        componentesPanel.add(lblUsuario);
        componentesPanel.add(jtfUsuario);
        componentesPanel.add(lblContraseña);
        componentesPanel.add(jtfContraseña);
        componentesPanel.add(lblConfirmarContraseña);
        componentesPanel.add(jtfConfirmarContraseña);
        componentesPanel.add(jbtnRegistrarse);
        componentesPanel.add(lblO);
        componentesPanel.add(jbtnIniciarSesion);
    }

    private void limpiarCamposForm() {
        jtfNombre.setText(null);
        jtfApellido.setText(null);
        jtfEmail.setText(null);
        jtfUsuario.setText(null);
    }

    private void limpiarContraseñas() {
        jtfContraseña.setText(null);
        jtfConfirmarContraseña.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloHotel = new javax.swing.JLabel();
        lblRegistro = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        lblConfirmarContraseña = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jtfEmail = new javax.swing.JTextField();
        jtfApellido = new javax.swing.JTextField();
        jtfUsuario = new javax.swing.JTextField();
        jbtnRegistrarse = new javax.swing.JButton();
        lblO = new javax.swing.JLabel();
        jbtnIniciarSesion = new javax.swing.JButton();
        jbtnSalir = new javax.swing.JButton();
        jtfContraseña = new javax.swing.JPasswordField();
        jtfConfirmarContraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTituloHotel.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 36)); // NOI18N
        lblTituloHotel.setText("MyHotel");

        lblRegistro.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 24)); // NOI18N
        lblRegistro.setText("Registrarse");

        lblNombre.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblNombre.setText("Nombre");

        lblApellido.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblApellido.setText("Apellido");

        lblEmail.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblEmail.setText("Correo electrónico");

        lblUsuario.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblUsuario.setText("Usuario");

        lblContraseña.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblContraseña.setText("Contraseña");

        lblConfirmarContraseña.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblConfirmarContraseña.setText("Confirmar contraseña");

        jtfUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfUsuarioActionPerformed(evt);
            }
        });

        jbtnRegistrarse.setBackground(new java.awt.Color(102, 153, 255));
        jbtnRegistrarse.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        jbtnRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        jbtnRegistrarse.setText("Registrarse");
        jbtnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistrarseActionPerformed(evt);
            }
        });

        lblO.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblO.setText("o");

        jbtnIniciarSesion.setBackground(new java.awt.Color(102, 153, 255));
        jbtnIniciarSesion.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        jbtnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        jbtnIniciarSesion.setText("Iniciar sesión");
        jbtnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIniciarSesionActionPerformed(evt);
            }
        });

        jbtnSalir.setBackground(new java.awt.Color(251, 51, 51));
        jbtnSalir.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 18)); // NOI18N
        jbtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalir.setText("Salir");
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(lblTituloHotel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(349, 349, 349)
                                .addComponent(lblRegistro))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblApellido)
                                    .addComponent(lblNombre)
                                    .addComponent(lblUsuario)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail)
                                    .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(357, 357, 357)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblConfirmarContraseña)
                                    .addComponent(jtfContraseña)
                                    .addComponent(jtfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(jtfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(lblContraseña)
                                    .addComponent(jtfConfirmarContraseña)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(426, 426, 426)
                                .addComponent(lblO))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(396, 396, 396)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtnIniciarSesion)
                                    .addComponent(jbtnRegistrarse))))
                        .addGap(0, 838, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloHotel)
                .addGap(74, 74, 74)
                .addComponent(lblRegistro)
                .addGap(44, 44, 44)
                .addComponent(lblNombre)
                .addGap(2, 2, 2)
                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApellido)
                .addGap(1, 1, 1)
                .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail)
                .addGap(1, 1, 1)
                .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblConfirmarContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfConfirmarContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jbtnRegistrarse)
                .addGap(18, 18, 18)
                .addComponent(lblO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnIniciarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfUsuarioActionPerformed

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jbtnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegistrarseActionPerformed

        String nombre = jtfNombre.getText();
        String apellido = jtfApellido.getText();
        String email = jtfEmail.getText();
        String nombreUsuario = jtfUsuario.getText();
        String contraseña = jtfContraseña.getText();
        String confirmacionContraseña = jtfConfirmarContraseña.getText();

        Usuario usuario = new Usuario(nombre, apellido, email, nombreUsuario, contraseña, confirmacionContraseña);

        String mensaje = negocioUsuario.validarRegistro(usuario);
        String titulo = "Registro de usuario";

        if (mensaje.equals("ok")) {

            String contraseñaCifrada = "";

            try {
                contraseñaCifrada = negocioUsuario.encriptarContraseñaUsuario(usuario.getContraseña());
                usuario.setContraseña(contraseñaCifrada);
                usuario.setActivo(1);
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex);
            }

            try {

                negocioUsuario.crearUsuario(usuario);
                JOptionPane.showConfirmDialog(null, "Usuario registrado exitosamente.", titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposForm();

            } catch (RollbackException rbe) {
                mensaje = "El email o usuario ingresado ya está registrado, pruebe con otro.";
                JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }

            limpiarContraseñas();

        } else {
            JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            limpiarContraseñas();
        }

    }//GEN-LAST:event_jbtnRegistrarseActionPerformed

    private void jbtnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIniciarSesionActionPerformed
        InicioSesion JframeInicioSesion = new InicioSesion();
        JframeInicioSesion.setVisible(true);
        //Este Jframe se destruye y elimina de memoria ram y SO
        this.dispose();
    }//GEN-LAST:event_jbtnIniciarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnIniciarSesion;
    private javax.swing.JButton jbtnRegistrarse;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JPasswordField jtfConfirmarContraseña;
    private javax.swing.JPasswordField jtfContraseña;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfUsuario;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblConfirmarContraseña;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblO;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JLabel lblTituloHotel;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
