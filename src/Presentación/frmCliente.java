package Presentación;

import Negocio.Entidades.Cliente;
import Negocio.NegocioCliente;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class frmCliente extends javax.swing.JFrame {

    private UtilidadJFrame utilidadJframe;
    private NegocioCliente negocioCliente;
    private Cliente cliente;
    private SimpleDateFormat sdf;

    public frmCliente() {
        initComponents();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Cliente", this);
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        negocioCliente = new NegocioCliente();
        mostrarListaPaises();
    }

    private void mostrarListaPaises() {
        for (String pais : UtilidadGeneral.getPaises()) {
            jcbPaises.addItem(pais);
        }
    }

    public void mostrarCliente(Cliente cliente) throws ParseException {
        this.cliente = cliente;

        jtfNombre.setText(cliente.getNombres());
        jtfApellido.setText(cliente.getApellidos());
        jtfDni.setText(String.valueOf(cliente.getDni()));
        jtfCorreo.setText(cliente.getCorreo());
        jtfTelefono.setText(String.valueOf(cliente.getNroTelefono()));
        jcbPaises.setSelectedItem(cliente.getNacionalidad());

        Date fechaNacimiento = sdf.parse(cliente.getFechaNacimiento());
        jdcFechaNacimiento.setDate(fechaNacimiento);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        jtfApellido = new javax.swing.JTextField();
        jtfDni = new javax.swing.JTextField();
        lblDocumento = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblNacionalidad = new javax.swing.JLabel();
        jtfCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        lblNroTeléfono = new javax.swing.JLabel();
        jtfTelefono = new javax.swing.JTextField();
        jdcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jcbPaises = new javax.swing.JComboBox();
        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        jbtnModificarCliente = new javax.swing.JButton();
        jtbnSalirCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, -1, -1));
        getContentPane().add(jtfNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 270, 33));

        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblApellido.setText("Apellido");
        getContentPane().add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));
        getContentPane().add(jtfApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 270, 33));
        getContentPane().add(jtfDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 270, 33));

        lblDocumento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblDocumento.setText("DNI");
        getContentPane().add(lblDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, -1, -1));

        lblFechaNacimiento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaNacimiento.setText("Fecha de nacimiento");
        getContentPane().add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, -1, -1));

        lblNacionalidad.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNacionalidad.setText("Nacionalidad");
        getContentPane().add(lblNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, -1, -1));
        getContentPane().add(jtfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 470, 270, 33));

        lblCorreo.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblCorreo.setText("Correo electrónico");
        getContentPane().add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, -1, -1));

        lblNroTeléfono.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNroTeléfono.setText("Nro. teléfono");
        getContentPane().add(lblNroTeléfono, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, -1, -1));
        getContentPane().add(jtfTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 530, 270, 33));

        jdcFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jdcFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, 163, 33));

        getContentPane().add(jcbPaises, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, 163, 33));

        jpTitulo.setBackground(new java.awt.Color(0, 0, 0));

        lblTituloPrincipal.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("Cliente");

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(615, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(621, 621, 621))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(35, 35, 35))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, -1));

        jbtnModificarCliente.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnModificarCliente.setText("Modificar");
        jbtnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnModificarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 610, 146, 54));

        jtbnSalirCliente.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnSalirCliente.setText("Salir");
        jtbnSalirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnSalirClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jtbnSalirCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, 146, 54));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbnSalirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnSalirClienteActionPerformed
        this.dispose();
    }//GEN-LAST:event_jtbnSalirClienteActionPerformed

    private void jbtnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarClienteActionPerformed
        String nombre = "", apellido = "", nacionalidad = "", correo = "", mensaje = "", fechaNacimiento = "";
        String titulo = "";
        Long dni = -1L, nroTelefono = -1L;
        boolean datosValidos = true;

        try {
            mensaje = negocioCliente.validarCliente(jtfNombre.getText(), jtfApellido.getText(), jtfCorreo.getText(), jtfTelefono.getText(), jtfDni.getText(), jdcFechaNacimiento);

            if (!mensaje.equals("ok")) {
                datosValidos = false;
                titulo = "Error";
                UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            } else {
                nombre = jtfNombre.getText();
                apellido = jtfApellido.getText();
                nacionalidad = (String) jcbPaises.getSelectedItem();
                correo = jtfCorreo.getText();
                dni = Long.parseLong(jtfDni.getText());
                nroTelefono = Long.parseLong(jtfTelefono.getText());
                fechaNacimiento = UtilidadGeneral.DateToString(jdcFechaNacimiento.getDate());
            }
        } catch (NumberFormatException nfe) {
            datosValidos = false;
            mensaje = "Verifica de haber ingresado el formato correcto.";
            titulo = "Error";
            UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
        }

        if (datosValidos) {
            mensaje = "¿Estás seguro que deseas modificar el cliente con id " + cliente.getId() + "?";
            titulo = "Confirmar modificación";
            int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo(mensaje, titulo);

            if (confirmado == JOptionPane.YES_OPTION) {
                cliente.setNombres(nombre);
                cliente.setApellidos(apellido);
                cliente.setFechaNacimiento(fechaNacimiento);
                cliente.setCorreo(correo);
                cliente.setNacionalidad(nacionalidad);
                cliente.setNroTelefono(nroTelefono);

                if (cliente.getDni().equals(dni)) {
                    try {
                        negocioCliente.modificarCliente(cliente);
                        mensaje = "¡Cliente modificado exitosamente!";
                    } catch (Exception ex) {
                        mensaje = "";
                        System.out.println("Error " + ex.getMessage());
                    }
                } else {
                    Cliente clienteExistente = negocioCliente.verificarExistenciaCliente(dni);
                    if (clienteExistente == null) {
                        cliente.setDni(dni);
                        try {
                            negocioCliente.modificarCliente(cliente);
                        } catch (Exception ex) {
                            System.out.println("Error " + ex.getMessage());
                        }
                        mensaje = "¡Cliente modificado exitosamente!";
                    } else {
                        mensaje = "El cliente con el dni adjunto ya existe.";
                        titulo = "Error";
                        UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
                        mensaje = "";
                    }
                }
                if (!mensaje.equals("")) {
                    titulo = "Cliente";
                    UtilidadJOptionPane.mostrarMensajeInformacion(mensaje, titulo);
                }
                this.dispose();
            }
        }
    }//GEN-LAST:event_jbtnModificarClienteActionPerformed

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
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnModificarCliente;
    private javax.swing.JComboBox jcbPaises;
    private com.toedter.calendar.JDateChooser jdcFechaNacimiento;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JButton jtbnSalirCliente;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JTextField jtfCorreo;
    private javax.swing.JTextField jtfDni;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfTelefono;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNroTeléfono;
    private javax.swing.JLabel lblTituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
