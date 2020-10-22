package Presentación;

import Negocio.Entidades.Habitacion;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJFrame;
import Negocio.NegocioHabitacion;
import Negocio.UtilidadJOptionPane;
import Negocio.UtilidadJTable;
import javax.swing.JOptionPane;

public class frmHabitacion extends javax.swing.JFrame {

    private UtilidadJFrame utilidadJframe;
    private UtilidadGeneral utilidadGeneral;
    private UtilidadJTable utilidadJtable;
    private NegocioHabitacion negocioHabitacion;
    private Habitacion habitacionModificada;
    boolean modificarHabitacion = false;

    public frmHabitacion() {
        initComponents();
        negocioHabitacion = new NegocioHabitacion();

        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Habitación", this);
    }

    private void limpiarCamposHabitacion() {
        jcbTipoHabitacion.setSelectedIndex(0);
        jcbEstadoHabitacion.setSelectedIndex(0);
        jsNroHabitacion.setValue(1);
        jtaDetallesHabitacion.setText("");
        jtfPrecioHabitacion.setText("");
    }

    public void mostrarHabitacion(Habitacion habitacion) {
        modificarHabitacion = true;
        habitacionModificada = habitacion;

        jcbTipoHabitacion.setSelectedItem(habitacion.getTipo());
        jsNroHabitacion.setValue(habitacion.getNumero());
        jcbEstadoHabitacion.setSelectedItem(habitacion.getEstado());
        jtaDetallesHabitacion.setText(habitacion.getDetalles());
        jtfPrecioHabitacion.setText(String.valueOf(habitacion.getPrecioDia()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblTipoHabitacion = new javax.swing.JLabel();
        jcbTipoHabitacion = new javax.swing.JComboBox();
        lblNumeroHabitacion = new javax.swing.JLabel();
        lblTipoHabitacion2 = new javax.swing.JLabel();
        jcbEstadoHabitacion = new javax.swing.JComboBox();
        lblDetalles = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDetallesHabitacion = new javax.swing.JTextArea();
        jtbnCancelarHabitacion = new javax.swing.JButton();
        jbtnGuardarHabitacion = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        jtfPrecioHabitacion = new javax.swing.JTextField();
        jsNroHabitacion = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpTitulo.setBackground(new java.awt.Color(0, 0, 0));

        lblTituloPrincipal.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("Habitación");

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(591, 591, 591)
                .addComponent(lblTituloPrincipal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTituloPrincipal)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        lblTipoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTipoHabitacion.setText("Tipo de habitación");

        jcbTipoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jcbTipoHabitacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Individual", "Doble", "Matrimonial" }));

        lblNumeroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNumeroHabitacion.setText("Número");

        lblTipoHabitacion2.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTipoHabitacion2.setText("Estado");

        jcbEstadoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jcbEstadoHabitacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disponible", "Ocupada", "Reparación", "Limpieza" }));

        lblDetalles.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblDetalles.setText("Detalles");

        jtaDetallesHabitacion.setColumns(20);
        jtaDetallesHabitacion.setRows(5);
        jScrollPane1.setViewportView(jtaDetallesHabitacion);

        jtbnCancelarHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnCancelarHabitacion.setText("Salir");
        jtbnCancelarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCancelarHabitacionActionPerformed(evt);
            }
        });

        jbtnGuardarHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnGuardarHabitacion.setText("Guardar");
        jbtnGuardarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarHabitacionActionPerformed(evt);
            }
        });

        lblPrecio.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblPrecio.setText("Precio / día");

        jsNroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jsNroHabitacion.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfPrecioHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoHabitacion)
                            .addComponent(lblNumeroHabitacion)
                            .addComponent(lblTipoHabitacion2)
                            .addComponent(lblDetalles))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbEstadoHabitacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbTipoHabitacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbtnGuardarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtbnCancelarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jsNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(480, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoHabitacion)
                    .addComponent(jcbTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroHabitacion)
                    .addComponent(jsNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoHabitacion2)
                    .addComponent(jcbEstadoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDetalles)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPrecioHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtbnCancelarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGuardarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbnCancelarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCancelarHabitacionActionPerformed
        this.dispose();
    }//GEN-LAST:event_jtbnCancelarHabitacionActionPerformed

    private void jbtnGuardarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarHabitacionActionPerformed
        String tipo = "", estado = "", detalles = "", titulo = "", mensaje = "";
        int numero = -1;
        float precio = -1;
        boolean datosValidos = true;
        Habitacion habitacionExistente;

        try {
            mensaje = negocioHabitacion.validarHabitacion(jtaDetallesHabitacion.getText(), jtfPrecioHabitacion.getText());

            if (!mensaje.equals("ok")) {
                datosValidos = false;
                titulo = "Error";
                UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            } else {
                tipo = (String) jcbTipoHabitacion.getSelectedItem();
                numero = Integer.parseInt(jsNroHabitacion.getValue().toString());
                estado = (String) jcbEstadoHabitacion.getSelectedItem();
                detalles = jtaDetallesHabitacion.getText();
                precio = Float.parseFloat(jtfPrecioHabitacion.getText());
            }
        } catch (NumberFormatException nfe) {
            datosValidos = false;
            mensaje = "Verifica de haber ingresado el formato correcto.";
            titulo = "Error";
            UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
        }

        if (datosValidos) {
            if (modificarHabitacion) {
                try {
                    habitacionExistente = null;

                    if (habitacionModificada.getNumero() != numero) {
                        habitacionExistente = negocioHabitacion.verificarExistenciaHabitacion(numero);
                    }
                    if (habitacionExistente == null) {
                        mensaje = "¿Estás seguro que deseas modificar la habitación con id " + habitacionModificada.getId() + "?";
                        titulo = "Confirmar modificación";
                        int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo(mensaje, titulo);

                        if (confirmado == JOptionPane.YES_OPTION) {
                            this.habitacionModificada.setTipo(tipo);
                            this.habitacionModificada.setNumero(numero);
                            this.habitacionModificada.setEstado(estado);
                            this.habitacionModificada.setDetalles(detalles);
                            this.habitacionModificada.setPrecioDia(precio);

                            negocioHabitacion.modificarHabitacion(this.habitacionModificada);
                            mensaje = "¡Habitación modificada exitosamente!";
                            modificarHabitacion = false;
                        } else {
                            mensaje = "";
                        }
                    } else {
                        mensaje = "El número de habitación ingresado ya existe.";
                        titulo = "Error";
                        UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
                        mensaje = "";
                    }
                } catch (Exception e) {
                    mensaje = "Error al modificar la habitación." + e.getMessage();
                }
            } else {
                habitacionExistente = negocioHabitacion.verificarExistenciaHabitacion(numero);

                if (habitacionExistente == null) {
                    mensaje = "¿Estás seguro que deseas crear la habitación?";
                    titulo = "Confirmar creación";
                    int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo(mensaje, titulo);

                    if (confirmado == JOptionPane.YES_OPTION) {
                        Habitacion nuevaHabitacion = new Habitacion(numero, tipo, estado, detalles, precio);
                        negocioHabitacion.crearHabitacion(nuevaHabitacion);
                        mensaje = "¡Habitación registrada exitosamente!";
                    } else {
                        mensaje = "";
                    }
                } else {
                    mensaje = "El número de habitación ingresado ya existe.";
                    titulo = "Error";
                    UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
                    mensaje = "";
                }
            }
            if (!mensaje.equals("")) {
                titulo = "Habitacion";
                UtilidadJOptionPane.mostrarMensajeInformacion(mensaje, titulo);
                limpiarCamposHabitacion();
                this.dispose();
            }
        }
    }//GEN-LAST:event_jbtnGuardarHabitacionActionPerformed

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
            java.util.logging.Logger.getLogger(frmHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHabitacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHabitacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnGuardarHabitacion;
    private javax.swing.JComboBox jcbEstadoHabitacion;
    private javax.swing.JComboBox jcbTipoHabitacion;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JSpinner jsNroHabitacion;
    private javax.swing.JTextArea jtaDetallesHabitacion;
    private javax.swing.JButton jtbnCancelarHabitacion;
    private javax.swing.JTextField jtfPrecioHabitacion;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblNumeroHabitacion;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTipoHabitacion;
    private javax.swing.JLabel lblTipoHabitacion2;
    private javax.swing.JLabel lblTituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
