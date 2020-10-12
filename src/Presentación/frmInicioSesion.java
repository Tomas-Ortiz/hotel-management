package Presentación;

import Negocio.Entidades.Usuario;
import Negocio.NegocioUsuario;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJOptionPane;
import Negocio.sesionUsuario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class frmInicioSesion extends javax.swing.JFrame {

    UtilidadJFrame utilidadJframe;
    NegocioUsuario negocioUsuario;
    frmPrincipal frmPrincipal;
    sesionUsuario sesionUsuario;

    public frmInicioSesion() {

        initComponents();

        negocioUsuario = new NegocioUsuario();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Iniciar sesión", this);
        sesionUsuario = sesionUsuario.getSesionUsuario();

        iniciarPanelTitulo();
        iniciarPanelLogin();
    }

    private void iniciarPanelTitulo() {
        JPanel jpTitulo = new JPanel();
        utilidadJframe.setPanelTitulo(jpTitulo, lblTituloHotel, getContentPane());
    }

    private void iniciarPanelLogin() {
        JPanel jpInicioSesion = new JPanel(new GridLayout(9, 1, 0, 3));
        List<JComponent> componentesPanel = new ArrayList<>();

        utilidadJframe.setTamañoPanelLogin(jpInicioSesion);
        utilidadJframe.setConfiguracionPanel(jpInicioSesion);
        utilidadJframe.centrarHorizontalLabel(lblIniciarSesion);
        utilidadJframe.centrarHorizontalLabel(lblO);

        agregarComponentesArray(componentesPanel);

        utilidadJframe.agregarComponentesPanelLogin(jpInicioSesion, componentesPanel);
        this.getContentPane().add(jpInicioSesion, BorderLayout.CENTER);
    }

    private void agregarComponentesArray(List<JComponent> componentesPanel) {
        JSeparator separatorLogin = new JSeparator(SwingConstants.HORIZONTAL);

        componentesPanel.add(lblIniciarSesion);
        componentesPanel.add(separatorLogin);
        componentesPanel.add(lblUsuario);
        componentesPanel.add(jtfUsuario);
        componentesPanel.add(lblContraseña);
        componentesPanel.add(jtfContraseña);
        componentesPanel.add(jbtnIniciarSesion);
        componentesPanel.add(lblO);
        componentesPanel.add(jtbnRegistrarse);
    }

    private void limpiarCampos() {
        jtfUsuario.setText(null);
    }

    private void limpiarContraseña() {
        jtfContraseña.setText(null);
    }

    private void mostrarVentanaPrincipal() {
        frmPrincipal = new frmPrincipal();
        frmPrincipal.setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloHotel = new javax.swing.JLabel();
        lblIniciarSesion = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jtfContraseña = new javax.swing.JPasswordField();
        jbtnIniciarSesion = new javax.swing.JButton();
        jtbnRegistrarse = new javax.swing.JButton();
        lblO = new javax.swing.JLabel();
        jbtnSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTituloHotel.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloHotel.setText("MyHotel");

        lblIniciarSesion.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 24)); // NOI18N
        lblIniciarSesion.setText("Iniciar sesión");

        lblUsuario.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblUsuario.setText("Usuario");

        lblContraseña.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblContraseña.setText("Contraseña");

        jbtnIniciarSesion.setBackground(new java.awt.Color(102, 153, 255));
        jbtnIniciarSesion.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        jbtnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        jbtnIniciarSesion.setText("Iniciar sesión");
        jbtnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIniciarSesionActionPerformed(evt);
            }
        });

        jtbnRegistrarse.setBackground(new java.awt.Color(102, 153, 255));
        jtbnRegistrarse.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        jtbnRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        jtbnRegistrarse.setText("Registrarse");
        jtbnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnRegistrarseActionPerformed(evt);
            }
        });

        lblO.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 14)); // NOI18N
        lblO.setText("o");

        jbtnSalir.setBackground(new java.awt.Color(255, 51, 51));
        jbtnSalir.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 18)); // NOI18N
        jbtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalir.setText("Salir");
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
                .addGap(182, 182, 182)
                .addComponent(lblTituloHotel, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                .addGap(179, 179, 179))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtbnRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(573, 573, 573)
                        .addComponent(lblO, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(511, 511, 511)
                        .addComponent(lblIniciarSesion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(480, 480, 480)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblContraseña)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(lblUsuario))
                            .addComponent(jtfUsuario)
                            .addComponent(jtfContraseña))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1257, Short.MAX_VALUE)
                .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloHotel)
                .addGap(97, 97, 97)
                .addComponent(lblIniciarSesion)
                .addGap(38, 38, 38)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lblContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jbtnIniciarSesion)
                .addGap(2, 2, 2)
                .addComponent(lblO)
                .addGap(2, 2, 2)
                .addComponent(jtbnRegistrarse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed

        System.exit(0);

    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jbtnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIniciarSesionActionPerformed

        String usuario = jtfUsuario.getText();
        String contraseña = jtfContraseña.getText();

        String mensaje = negocioUsuario.validarInicioSesion(usuario, contraseña);
        String titulo = "Inicio de sesión";

        if (!mensaje.equals("ok")) {
            UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            limpiarContraseña();
        } else {
            String contraseñaCifrada = "";
            try {
                contraseñaCifrada = negocioUsuario.encriptarContraseña(contraseña);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Error en la encriptación de la contraseña" + ex.getMessage());
            }

            List<Usuario> usuarios = negocioUsuario.buscarUsuario(usuario, contraseñaCifrada);
            // Si existe el usuario
            if (usuarios.size() > 0) {
                Usuario usuarioIniciado = usuarios.get(0);
                limpiarCampos();
                sesionUsuario.setUsuario(usuarioIniciado);
                mostrarVentanaPrincipal();
            } else {
                mensaje = "Usuario o contraseña incorrectos.";
                UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            }
            limpiarContraseña();
        }
    }//GEN-LAST:event_jbtnIniciarSesionActionPerformed

    private void jtbnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnRegistrarseActionPerformed
        frmRegistro JframeRegistro = new frmRegistro();
        JframeRegistro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jtbnRegistrarseActionPerformed

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
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnIniciarSesion;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JButton jtbnRegistrarse;
    private javax.swing.JPasswordField jtfContraseña;
    private javax.swing.JTextField jtfUsuario;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblIniciarSesion;
    private javax.swing.JLabel lblO;
    private javax.swing.JLabel lblTituloHotel;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
