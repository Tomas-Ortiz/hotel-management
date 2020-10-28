package Presentación;

import Negocio.Entidades.Cliente;
import Negocio.NegocioCliente;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadIcono;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJOptionPane;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class frmCliente extends javax.swing.JFrame {

    private final UtilidadJFrame utilidadJframe;
    private final NegocioCliente negocioCliente;
    private Cliente cliente;
    private final SimpleDateFormat sdf;

    public frmCliente() {
        initComponents();
        mostrarIconos();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Cliente", this);
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        negocioCliente = NegocioCliente.getNegocioCliente();
        mostrarListaPaises();
    }

    private void mostrarIconos() {
        UtilidadIcono.cargarIconoFrame(this);
        Image iconoCliente = new ImageIcon("src/Recursos/Iconos/iconoCliente2.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoCliente, lblIconoCliente, 50, 50);
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
        lblIconoCliente = new javax.swing.JLabel();
        jbtnModificarCliente = new javax.swing.JButton();
        jtbnSalirCliente = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNombre.setText("Nombre");

        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblApellido.setText("Apellido");

        lblDocumento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblDocumento.setText("DNI");

        lblFechaNacimiento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaNacimiento.setText("Fecha de nacimiento");

        lblNacionalidad.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNacionalidad.setText("Nacionalidad");

        lblCorreo.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblCorreo.setText("Correo electrónico");

        lblNroTeléfono.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNroTeléfono.setText("Nro. teléfono");

        jdcFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));

        jcbPaises.setBackground(new java.awt.Color(102, 204, 255));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIconoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(525, 525, 525))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblIconoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        jbtnModificarCliente.setBackground(new java.awt.Color(0, 102, 204));
        jbtnModificarCliente.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnModificarCliente.setForeground(new java.awt.Color(255, 255, 255));
        jbtnModificarCliente.setText("Modificar");
        jbtnModificarCliente.setBorder(null);
        jbtnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarClienteActionPerformed(evt);
            }
        });

        jtbnSalirCliente.setBackground(new java.awt.Color(153, 0, 51));
        jtbnSalirCliente.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnSalirCliente.setForeground(new java.awt.Color(255, 255, 255));
        jtbnSalirCliente.setText("Salir");
        jtbnSalirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnSalirClienteActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblNombre)
                        .addGap(104, 104, 104)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblApellido)
                        .addGap(103, 103, 103)
                        .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblDocumento)
                        .addGap(138, 138, 138)
                        .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblFechaNacimiento)
                        .addGap(8, 8, 8)
                        .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblNacionalidad)
                        .addGap(65, 65, 65)
                        .addComponent(jcbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblCorreo)
                        .addGap(19, 19, 19)
                        .addComponent(jtfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(lblNroTeléfono)
                        .addGap(61, 61, 61)
                        .addComponent(jtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(540, 540, 540)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jtbnSalirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(440, 474, Short.MAX_VALUE))
            .addComponent(jpTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNombre))
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblApellido))
                    .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDocumento))
                    .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblFechaNacimiento))
                    .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNacionalidad))
                    .addComponent(jcbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCorreo))
                    .addComponent(jtfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNroTeléfono))
                    .addComponent(jtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnSalirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
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
    private javax.swing.JLabel lblIconoCliente;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNroTeléfono;
    private javax.swing.JLabel lblTituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
