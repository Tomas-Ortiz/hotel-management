package Presentación;

import Negocio.Entidades.Usuario;
import Negocio.NegocioUsuario;
import Negocio.UtilidadIcono;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJOptionPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class frmRegistro extends javax.swing.JFrame {

    private final UtilidadJFrame utilidadJframe;
    private final NegocioUsuario negocioUsuario;

    public frmRegistro() {
        initComponents();
        negocioUsuario = NegocioUsuario.getNegocioUsuario();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Registrarse", this);
        iniciarPanelTitulo();
        iniciarPanelRegistro();
        mostrarIconos();
    }

    private void mostrarIconos() {
        Image iconoIniciarSesion = new ImageIcon("src/Recursos/Iconos/iconoInicioSesion.png").getImage();
        UtilidadIcono.cargarIconoButtonRedimensionable(iconoIniciarSesion, jbtnIniciarSesion, 17, 17);

        Image iconoRegistro = new ImageIcon("src/Recursos/Iconos/iconoRegistro.png").getImage();
        UtilidadIcono.cargarIconoButtonRedimensionable(iconoRegistro, jbtnRegistrarse, 20, 20);

        Image iconoSalir = new ImageIcon("src/Recursos/Iconos/iconoSalir.png").getImage();
        UtilidadIcono.cargarIconoButton(iconoSalir, jbtnSalir, 35, 35);

        UtilidadIcono.cargarIconoFrame(this);
    }

    private void iniciarPanelTitulo() {
        JPanel jpTitulo = new JPanel();
        utilidadJframe.setPanelTitulo(jpTitulo, lblTituloHotel, getContentPane());
    }

    private void iniciarPanelRegistro() {
        JPanel jpRegistro = new JPanel(new GridLayout(17, 1, 0, 0));
        List<JComponent> componentesPanel = new ArrayList<>();

        utilidadJframe.setTamañoPanelRegistro(jpRegistro);
        utilidadJframe.setConfiguracionPanel(jpRegistro);
        utilidadJframe.centrarHorizontalLabel(lblRegistro);
        utilidadJframe.centrarHorizontalLabel(lblO);

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
        jSeparator1 = new javax.swing.JSeparator();
        lblIconoHotel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTituloHotel.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloHotel.setText("MyHotel");

        lblRegistro.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 40)); // NOI18N
        lblRegistro.setText("Registrarse");

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        lblNombre.setText("Nombre");

        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        lblApellido.setText("Apellido");

        lblEmail.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        lblEmail.setText("Correo electrónico");

        lblUsuario.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        lblUsuario.setText("Usuario");

        lblContraseña.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        lblContraseña.setText("Contraseña");

        lblConfirmarContraseña.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        lblConfirmarContraseña.setText("Confirmar contraseña");

        jtfUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfUsuarioActionPerformed(evt);
            }
        });

        jbtnRegistrarse.setBackground(new java.awt.Color(0, 153, 102));
        jbtnRegistrarse.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 14)); // NOI18N
        jbtnRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        jbtnRegistrarse.setText("Registrarse");
        jbtnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistrarseActionPerformed(evt);
            }
        });

        lblO.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblO.setText("o");

        jbtnIniciarSesion.setBackground(new java.awt.Color(0, 102, 204));
        jbtnIniciarSesion.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 14)); // NOI18N
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
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail)
                                    .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(351, 351, 351)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblConfirmarContraseña)
                                    .addComponent(jtfContraseña)
                                    .addComponent(jtfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(jtfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(lblContraseña)
                                    .addComponent(jtfConfirmarContraseña)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(426, 426, 426)
                                .addComponent(lblO))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(396, 396, 396)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtnIniciarSesion)
                                    .addComponent(jbtnRegistrarse)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblApellido)
                                        .addComponent(lblNombre)
                                        .addComponent(lblUsuario))
                                    .addComponent(lblRegistro)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(lblTituloHotel)
                                .addGap(484, 484, 484)
                                .addComponent(lblIconoHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 479, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloHotel)
                    .addComponent(lblIconoHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(lblRegistro)
                .addGap(50, 50, 50)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        String rol = "user";

        Usuario usuario = new Usuario(nombre, apellido, email, nombreUsuario, contraseña, confirmacionContraseña, rol);
        String mensaje = negocioUsuario.validarRegistro(usuario);
        String titulo = "Registro de usuario";

        if (mensaje.equals("ok")) {
            String contraseñaCifrada = "";
            try {
                contraseñaCifrada = negocioUsuario.encriptarContraseña(usuario.getContraseña());
                usuario.setContraseña(contraseñaCifrada);
                usuario.setActivo(1);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Error en la encriptación de la contraseña" + ex.getMessage());
            }
            try {
                negocioUsuario.crearUsuario(usuario);
                mensaje = "Usuario registrado exitosamente.";
                UtilidadJOptionPane.mostrarMensajeInformacion(mensaje, titulo);
                limpiarCamposForm();

            } catch (RollbackException rbe) {
                mensaje = "El email o usuario ingresado ya está registrado, pruebe con otro.";
                UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            }
            limpiarContraseñas();
        } else {
            UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            limpiarContraseñas();
        }
    }//GEN-LAST:event_jbtnRegistrarseActionPerformed

    private void jbtnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIniciarSesionActionPerformed
        frmInicioSesion JframeInicioSesion = new frmInicioSesion();
        JframeInicioSesion.setVisible(true);
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
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
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
    private javax.swing.JLabel lblIconoHotel;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblO;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JLabel lblTituloHotel;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
