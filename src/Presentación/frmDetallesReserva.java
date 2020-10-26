package Presentación;

import Negocio.Entidades.Reserva;
import Negocio.Entidades.ReservaProducto;
import Negocio.NegocioReserva;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class frmDetallesReserva extends javax.swing.JFrame {

    private final UtilidadJFrame utilidadJframe;
    private final UtilidadJTable utilidadJtable;

    private final DefaultTableModel dtmProductos;

    private NegocioReserva negocioReserva;

    private final SimpleDateFormat sdf;

    public frmDetallesReserva() {
        initComponents();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Detalles de reserva", this);
        utilidadJtable = UtilidadJTable.getUtilidadJTable();
        negocioReserva = NegocioReserva.getNegocioReserva();
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        dtmProductos = (DefaultTableModel) jtbProdutosReserva.getModel();
        utilidadJtable.centrarElementosTable(jtbProdutosReserva);
    }

    public void mostrarDetallesReserva(Reserva reserva) {
        Date fechaEntrada = null, fechaSalida = null;

        lblNombre.setText("Nombre: " + reserva.getCliente().getNombres());
        lblApellido.setText("Apellido: " + reserva.getCliente().getApellidos());
        lblDocumento.setText("DNI: " + reserva.getCliente().getDni());
        lblFechaNacimiento.setText("Fecha de nacimiento: " + reserva.getCliente().getFechaNacimiento());
        lblNacionalidad.setText("Nacionalidad: " + reserva.getCliente().getNacionalidad());
        lblCorreo.setText("Correo electrónico: " + reserva.getCliente().getCorreo());
        lblNroTeléfono.setText("Nro. teléfono: " + reserva.getCliente().getNroTelefono());

        lblFechaEntrada.setText("Fecha de entrada: " + reserva.getFechaEntrada());
        lblFechaSalida.setText("Fecha de salida: " + reserva.getFechaSalida());
        lblHoraEntrada.setText("Hora de entrada: " + reserva.getHoraEntrada());
        lblHoraSalida.setText("Hora de entrada: " + reserva.getHoraSalida());

        try {
            fechaEntrada = sdf.parse(reserva.getFechaEntrada());
            fechaSalida = sdf.parse(reserva.getFechaSalida());
        } catch (ParseException ex) {
            System.out.println("Error en la conversión de fechas " + ex.getMessage());
        }
        lblTotalDias.setText("Días: " + UtilidadGeneral.calcularDiasEntreFechas(fechaEntrada, fechaSalida));

        lblNroHabitacion.setText("Nro. habitación: " + reserva.getHabitacion().getNumero());
        lblTipoHabitacion.setText("Tipo: " + reserva.getHabitacion().getTipo());
        lblPrecioDia.setText("Precio/Día: $" + reserva.getHabitacion().getPrecioDia());
        lblEstadoHabitacion.setText("Estado: " + reserva.getHabitacion().getEstado());
        lblDetalles.setText("Detalles: " + reserva.getHabitacion().getDetalles());

        lblTipoPago.setText("Tipo de pago: " + reserva.getTipoPago());
        lblEstadoPago.setText("Estado: " + reserva.getEstado());

        float precioTotalProductos = negocioReserva.calcularPrecioTotalProductos(reserva.getProductos());
        float precioTotalAlojamiento = negocioReserva.calcularPrecioTotal(reserva.getHabitacion(), fechaEntrada, fechaSalida);
        float precioTotalReserva = precioTotalProductos + precioTotalAlojamiento;

        lblTotalProductos.setText("Total productos: $" + precioTotalProductos);
        lblTotalAlojamiento.setText("Total alojamiento: $" + precioTotalAlojamiento);
        lblTotalReserva.setText("Total reserva: $" + precioTotalReserva);

        mostrarProductosReserva(reserva.getProductos());
    }

    private void mostrarProductosReserva(List<ReservaProducto> productosReserva) {
        dtmProductos.setRowCount(0);
        if (productosReserva.size() > 0) {
            for (ReservaProducto prod : productosReserva) {
                dtmProductos.addRow(new Object[]{prod.getId().getProductoId(), prod.getProducto().getNombre(),
                    prod.getProducto().getMarca(), prod.getCantProducto(), prod.getProducto().getPrecioVenta(),
                    prod.getPrecioTotal()});
            }
        } else {
            dtmProductos.addRow(new Object[]{"Esta", "reserva", "no", "tiene", "productos", "consumidos."});
        }
        lblCantProductos.setText("Productos (" + productosReserva.size() + ")");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblDatosCliente = new javax.swing.JLabel();
        lblNacionalidad = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblNroTeléfono = new javax.swing.JLabel();
        lblAlojamiento = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblHoraSalida = new javax.swing.JLabel();
        lblTotalDias = new javax.swing.JLabel();
        lblHabitacion = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblEstadoHabitacion = new javax.swing.JLabel();
        lblPago = new javax.swing.JLabel();
        lblTotalProductos = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblFechaEntrada = new javax.swing.JLabel();
        lblTipoPago = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblDetalles = new javax.swing.JLabel();
        lblEstadoPago = new javax.swing.JLabel();
        lblNroHabitacion = new javax.swing.JLabel();
        lblFechaSalida = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jtbnSalir = new javax.swing.JButton();
        lblPrecioDia = new javax.swing.JLabel();
        lblTipoHabitacion = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        lblProductosConsumidos = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jspProductosReserva = new javax.swing.JScrollPane();
        jtbProdutosReserva = new javax.swing.JTable();
        lblHoraEntrada = new javax.swing.JLabel();
        lblCantProductos = new javax.swing.JLabel();
        lblTotalReserva = new javax.swing.JLabel();
        lblTotalAlojamiento = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        lblProductosConsumidos1 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNombre.setText("Nombre:");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 360, -1));

        jpTitulo.setBackground(new java.awt.Color(0, 0, 0));

        lblTituloPrincipal.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("Detalles de reserva");

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(526, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(502, 502, 502))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(32, 32, 32))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, -1));

        lblDocumento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblDocumento.setText("DNI:");
        getContentPane().add(lblDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 360, -1));

        lblFechaNacimiento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaNacimiento.setText("Fecha de nacimiento:");
        getContentPane().add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 360, -1));

        lblDatosCliente.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblDatosCliente.setText("Cliente");
        getContentPane().add(lblDatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, -1));

        lblNacionalidad.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNacionalidad.setText("Nacionalidad:");
        getContentPane().add(lblNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 280, -1));

        lblCorreo.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblCorreo.setText("Correo electrónico:");
        getContentPane().add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 360, -1));

        lblNroTeléfono.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNroTeléfono.setText("Nro. teléfono:");
        getContentPane().add(lblNroTeléfono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 360, -1));

        lblAlojamiento.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblAlojamiento.setText("Alojamiento");
        getContentPane().add(lblAlojamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 180, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 360, 10));

        lblHoraSalida.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblHoraSalida.setText("Hora de salida:");
        getContentPane().add(lblHoraSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 220, -1));

        lblTotalDias.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTotalDias.setText("Total días:");
        getContentPane().add(lblTotalDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 230, -1));

        lblHabitacion.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblHabitacion.setText("Habitación");
        getContentPane().add(lblHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 150, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 590, 480, 20));

        lblEstadoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblEstadoHabitacion.setText("Estado:");
        getContentPane().add(lblEstadoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 400, 230, -1));

        lblPago.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblPago.setText("Pago");
        getContentPane().add(lblPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 150, -1));

        lblTotalProductos.setFont(new java.awt.Font("Maiandra GD", 0, 20)); // NOI18N
        lblTotalProductos.setText("Total productos:");
        getContentPane().add(lblTotalProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 510, 360, 40));

        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblApellido.setText("Apellido:");
        getContentPane().add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 360, -1));

        lblFechaEntrada.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaEntrada.setText("Fecha de entrada:");
        getContentPane().add(lblFechaEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 250, -1));

        lblTipoPago.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTipoPago.setText("Tipo de pago:");
        getContentPane().add(lblTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, 360, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 360, 10));

        lblDetalles.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblDetalles.setText("Detalles:");
        getContentPane().add(lblDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 480, -1));

        lblEstadoPago.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblEstadoPago.setText("Estado:");
        getContentPane().add(lblEstadoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 560, 360, -1));

        lblNroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNroHabitacion.setText("Nro. habitación:");
        getContentPane().add(lblNroHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 230, -1));

        lblFechaSalida.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaSalida.setText("Fecha de salida:");
        getContentPane().add(lblFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 250, -1));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 480, 10));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 480, 10));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 480, 20));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 480, 10));
        getContentPane().add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 360, 10));

        jtbnSalir.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnSalir.setText("Salir");
        jtbnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jtbnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 620, 146, 54));

        lblPrecioDia.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblPrecioDia.setText("Precio por día:");
        getContentPane().add(lblPrecioDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 360, 230, -1));

        lblTipoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTipoHabitacion.setText("Tipo:");
        getContentPane().add(lblTipoHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 230, -1));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 480, 20));

        lblProductosConsumidos.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblProductosConsumidos.setText("Productos consumidos");
        getContentPane().add(lblProductosConsumidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 150, 270, 30));
        getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 180, 410, 10));
        getContentPane().add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 150, 410, 10));

        jtbProdutosReserva.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbProdutosReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Producto", "Marca", "Cantidad", "Precio unitario", "Precio total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbProdutosReserva.setRowHeight(21);
        jtbProdutosReserva.setRowMargin(5);
        jtbProdutosReserva.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbProdutosReserva.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbProdutosReserva.getTableHeader().setReorderingAllowed(false);
        jspProductosReserva.setViewportView(jtbProdutosReserva);

        getContentPane().add(jspProductosReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 220, 410, 210));

        lblHoraEntrada.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblHoraEntrada.setText("Hora de entrada:");
        getContentPane().add(lblHoraEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 220, -1));

        lblCantProductos.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblCantProductos.setText("Productos ()");
        getContentPane().add(lblCantProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 190, 120, -1));

        lblTotalReserva.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        lblTotalReserva.setText("Total reserva:");
        getContentPane().add(lblTotalReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 595, 360, 30));

        lblTotalAlojamiento.setFont(new java.awt.Font("Maiandra GD", 0, 20)); // NOI18N
        lblTotalAlojamiento.setText("Total alojamiento:");
        getContentPane().add(lblTotalAlojamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 550, 360, 40));
        getContentPane().add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 480, 20));

        lblProductosConsumidos1.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblProductosConsumidos1.setText("Totales");
        getContentPane().add(lblProductosConsumidos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 470, 270, 30));
        getContentPane().add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 630, 410, 20));
        getContentPane().add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 470, 410, 20));
        getContentPane().add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 500, 410, 20));
        getContentPane().add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 590, 410, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jtbnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(frmDetallesReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDetallesReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDetallesReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDetallesReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDetallesReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JScrollPane jspProductosReserva;
    private javax.swing.JTable jtbProdutosReserva;
    private javax.swing.JButton jtbnSalir;
    private javax.swing.JLabel lblAlojamiento;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCantProductos;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDatosCliente;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblEstadoHabitacion;
    private javax.swing.JLabel lblEstadoPago;
    private javax.swing.JLabel lblFechaEntrada;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFechaSalida;
    private javax.swing.JLabel lblHabitacion;
    private javax.swing.JLabel lblHoraEntrada;
    private javax.swing.JLabel lblHoraSalida;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNroHabitacion;
    private javax.swing.JLabel lblNroTeléfono;
    private javax.swing.JLabel lblPago;
    private javax.swing.JLabel lblPrecioDia;
    private javax.swing.JLabel lblProductosConsumidos;
    private javax.swing.JLabel lblProductosConsumidos1;
    private javax.swing.JLabel lblTipoHabitacion;
    private javax.swing.JLabel lblTipoPago;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JLabel lblTotalAlojamiento;
    private javax.swing.JLabel lblTotalDias;
    private javax.swing.JLabel lblTotalProductos;
    private javax.swing.JLabel lblTotalReserva;
    // End of variables declaration//GEN-END:variables
}
