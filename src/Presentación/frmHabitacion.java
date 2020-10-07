package Presentación;

import Negocio.Entidades.Habitacion;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJFrame;
import Negocio.NegocioHabitacion;
import Negocio.UtilidadJTable;
import javax.swing.JOptionPane;

public class frmHabitacion extends javax.swing.JFrame {

    UtilidadJFrame utilidadJframe;
    UtilidadGeneral utilidadGeneral;
    UtilidadJTable utilidadJtable;
    NegocioHabitacion negocioHabitacion;
    Habitacion habitacionModificada;
    boolean modificarHabitacion = false;

    public frmHabitacion() {
        initComponents();

        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Habitación", this);
        negocioHabitacion = new NegocioHabitacion();
    }

    private void limpiarCamposHabitacion() {
        jcbTipoHabitacion.setSelectedIndex(0);
        jcbEstadoHabitacion.setSelectedIndex(0);
        jtfNumeroHabitacion.setText("");
        jtaDetallesHabitacion.setText("");
        jtfPrecioHabitacion.setText("");
    }

    public void setHabitacion(Habitacion habitacion) {
        modificarHabitacion = true;
        this.habitacionModificada = habitacion;
        jcbTipoHabitacion.setSelectedItem(habitacion.getTipo());
        jtfNumeroHabitacion.setText(String.valueOf(habitacion.getNumero()));
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
        jtfNumeroHabitacion = new javax.swing.JTextField();
        lblTipoHabitacion2 = new javax.swing.JLabel();
        jcbEstadoHabitacion = new javax.swing.JComboBox();
        lblDetalles = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDetallesHabitacion = new javax.swing.JTextArea();
        jtbnCancelarHabitacion = new javax.swing.JButton();
        jbtnGuardarHabitacion = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        jtfPrecioHabitacion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpTitulo.setBackground(new java.awt.Color(0, 0, 0));

        lblTituloPrincipal.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("Datos de habitación");

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(485, 485, 485))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTituloPrincipal)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        lblTipoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTipoHabitacion.setText("Tipo de habitación");

        jcbTipoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jcbTipoHabitacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Individual", "Doble", "Matrimonial" }));

        lblNumeroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNumeroHabitacion.setText("Número");

        jtfNumeroHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumeroHabitacionActionPerformed(evt);
            }
        });

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

        jtfPrecioHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPrecioHabitacionActionPerformed(evt);
            }
        });

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
                            .addComponent(jtfNumeroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbtnGuardarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtbnCancelarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(480, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoHabitacion)
                    .addComponent(jcbTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroHabitacion)
                    .addComponent(jtfNumeroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoHabitacion2)
                    .addComponent(jcbEstadoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDetalles)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPrecioHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtbnCancelarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGuardarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfNumeroHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumeroHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumeroHabitacionActionPerformed

    private void jtbnCancelarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCancelarHabitacionActionPerformed

        this.dispose();

    }//GEN-LAST:event_jtbnCancelarHabitacionActionPerformed

    private void jbtnGuardarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarHabitacionActionPerformed

        String tipo = "", estado = "", detalles = "";
        int numero = -1;
        float precio = -1;
        boolean datosValidos = true;

        try {
            String mensaje = negocioHabitacion.validarHabitacion(jtfNumeroHabitacion.getText(), jtaDetallesHabitacion.getText(), jtfPrecioHabitacion.getText());

            if (!mensaje.equals("ok")) {
                datosValidos = false;
                JOptionPane.showConfirmDialog(null, mensaje, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            } else {
                tipo = (String) jcbTipoHabitacion.getSelectedItem();
                numero = Integer.parseInt(jtfNumeroHabitacion.getText());
                estado = (String) jcbEstadoHabitacion.getSelectedItem();
                detalles = jtaDetallesHabitacion.getText();
                precio = Float.parseFloat(jtfPrecioHabitacion.getText());
            }

        } catch (NumberFormatException nfe) {
            datosValidos = false;
            JOptionPane.showConfirmDialog(null, "Verifica de haber ingresado el formato correcto.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }

        if (datosValidos) {
            String mensaje = "";

            if (modificarHabitacion) {
                try {

                    int confirmado = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas modificar la habitación con id " + habitacionModificada.getId() + "?", "Confirmar modificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (confirmado == JOptionPane.YES_OPTION) {
                        this.habitacionModificada.setTipo(tipo);
                        this.habitacionModificada.setNumero(numero);
                        this.habitacionModificada.setEstado(estado);
                        this.habitacionModificada.setDetalles(detalles);
                        this.habitacionModificada.setPrecioDia(precio);

                        negocioHabitacion.modificarHabitacion(this.habitacionModificada);
                        mensaje = "Habitación modificada exitosamente.";
                        modificarHabitacion = false;
                    }

                } catch (Exception ex) {
                    mensaje = "Error al modificar la habitación." + ex.getMessage();
                }

            } else {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas crear la habitación?", "Confirmar creación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirmado == JOptionPane.YES_OPTION) {
                    Habitacion nuevaHabitacion = new Habitacion(numero, tipo, estado, detalles, precio);
                    negocioHabitacion.crearHabitacion(nuevaHabitacion);
                    mensaje = "Habitación registrada exitosamente.";
                }
            }

            if (!mensaje.equals("")) {
                JOptionPane.showConfirmDialog(null, mensaje, "Habitacion", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }

            limpiarCamposHabitacion();

            this.dispose();
        }

    }//GEN-LAST:event_jbtnGuardarHabitacionActionPerformed

    private void jtfPrecioHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPrecioHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPrecioHabitacionActionPerformed

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
    private javax.swing.JTextArea jtaDetallesHabitacion;
    private javax.swing.JButton jtbnCancelarHabitacion;
    private javax.swing.JTextField jtfNumeroHabitacion;
    private javax.swing.JTextField jtfPrecioHabitacion;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblNumeroHabitacion;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTipoHabitacion;
    private javax.swing.JLabel lblTipoHabitacion2;
    private javax.swing.JLabel lblTituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
