package Presentacion;

import Negocio.Entidades.Cliente;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.Entidades.ReservaProducto;
import Negocio.HeaderRenderer;
import Negocio.NegocioReserva;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadIcono;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJTable;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class frmDetallesReserva extends javax.swing.JFrame {

    private final UtilidadJFrame utilidadJframe;
    private final NegocioReserva negocioReserva;
    private final DefaultTableModel dtmProductos;
    private final SimpleDateFormat sdf;

    public frmDetallesReserva() {
        initComponents();
        mostrarIconos();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Detalles de reserva", this);
        negocioReserva = NegocioReserva.getNegocioReserva();
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        dtmProductos = (DefaultTableModel) jtbProdutosReserva.getModel();
        configurarJTable();
    }

    private void configurarJTable() {
        UtilidadJTable.setCellRender(jtbProdutosReserva);
        jtbProdutosReserva.getTableHeader().setDefaultRenderer(new HeaderRenderer());
    }

    private void mostrarIconos() {
        UtilidadIcono.cargarIconoFrame(this);
        Image iconoReserva = new ImageIcon("Recursos\\Iconos\\iconoReserva2.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoReserva, lblIconoReserva, 50, 50);

        Image iconoSalir = new ImageIcon("Recursos\\Iconos\\iconoSalir2.png").getImage();
        UtilidadIcono.cargarIconoButtonRedimensionable(iconoSalir, jtbnSalir, 43, 43);

        Image iconoImpresora = new ImageIcon("Recursos\\Iconos\\iconoImpresora.png").getImage();
        UtilidadIcono.cargarIconoButtonRedimensionable(iconoImpresora, jtbnImprimir, 43, 43);
    }

    public void mostrarDetallesReserva(Reserva reserva) {
        mostrarCliente(reserva.getCliente());

        mostrarReserva(reserva);

        mostrarHabitacion(reserva.getHabitacion());

        mostrarProductosReserva(reserva.getProductos());

        mostrarInfoPrecios(reserva.getEstado());
    }

    private void mostrarCliente(Cliente cliente) {
        lblNombre.setText("Nombre: " + cliente.getNombres());
        lblApellido.setText("Apellido: " + cliente.getApellidos());
        lblDocumento.setText("DNI: " + cliente.getDni());
        lblFechaNacimiento.setText("Fecha de nacimiento: " + cliente.getFechaNacimiento());
        lblNacionalidad.setText("Nacionalidad: " + cliente.getNacionalidad());
        lblCorreo.setText("Correo electrónico: " + cliente.getCorreo());
        lblNroTeléfono.setText("Nro. de teléfono: " + cliente.getNroTelefono());
    }

    private void mostrarReserva(Reserva reserva) {
        Date fechaEntrada = null, fechaSalida = null;

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
        lblTipoPago.setText("Tipo de pago: " + reserva.getTipoPago());
        lblEstadoPago.setText("Estado: " + reserva.getEstado());

        mostrarPreciosReserva(reserva);
    }

    private void mostrarPreciosReserva(Reserva reserva) {
        lblTotalProductos.setText("Total productos: $" + reserva.getPrecioProductos());
        lblTotalAlojamiento.setText("Total alojamiento: $" + reserva.getPrecioAlojamiento());
        lblTotalReserva.setText("Total reserva: $" + reserva.getPrecioTotal());
    }

    private void mostrarHabitacion(Habitacion hab) {
        lblNroHabitacion.setText("Nro. habitación: " + hab.getNumero());
        lblTipoHabitacion.setText("Tipo: " + hab.getTipo());
        lblPrecioDia.setText("Precio/Día: $" + hab.getPrecioDia());
        lblEstadoHabitacion.setText("Estado: " + hab.getEstado());
        lblDetalles.setText("Detalles: " + hab.getDetalles());
    }

    private void mostrarInfoPrecios(String estadoReserva) {
        if (estadoReserva.equals("Cobrado")) {
            lblInfoPrecios.setVisible(true);
        } else {
            lblInfoPrecios.setVisible(false);
        }
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

        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblIconoReserva = new javax.swing.JLabel();
        jtbnSalir = new javax.swing.JButton();
        jtbnImprimir = new javax.swing.JButton();
        jpDetallesReserva = new javax.swing.JPanel();
        lblEstadoPago = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblTipoPago = new javax.swing.JLabel();
        lblInfoPrecios = new javax.swing.JLabel();
        lblNroTeléfono = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblNacionalidad = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        lblDatosCliente = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblProductosConsumidos = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        lblCantProductos = new javax.swing.JLabel();
        jspProductosReserva = new javax.swing.JScrollPane();
        jtbProdutosReserva = new javax.swing.JTable();
        jSeparator17 = new javax.swing.JSeparator();
        lblTotalProductos = new javax.swing.JLabel();
        lblProductosConsumidos1 = new javax.swing.JLabel();
        lblTotalAlojamiento = new javax.swing.JLabel();
        lblTotalReserva = new javax.swing.JLabel();
        lblHabitacion = new javax.swing.JLabel();
        lblTotalDias = new javax.swing.JLabel();
        lblHoraSalida = new javax.swing.JLabel();
        lblAlojamiento = new javax.swing.JLabel();
        lblPago = new javax.swing.JLabel();
        lblEstadoHabitacion = new javax.swing.JLabel();
        lblFechaEntrada = new javax.swing.JLabel();
        lblDetalles = new javax.swing.JLabel();
        lblNroHabitacion = new javax.swing.JLabel();
        lblFechaSalida = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        lblPrecioDia = new javax.swing.JLabel();
        lblTipoHabitacion = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        lblHoraEntrada = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIconoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(421, 421, 421))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblIconoReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jtbnSalir.setBackground(new java.awt.Color(153, 0, 51));
        jtbnSalir.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnSalir.setForeground(new java.awt.Color(255, 255, 255));
        jtbnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jtbnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 640, 146, 54));

        jtbnImprimir.setBackground(new java.awt.Color(0, 102, 204));
        jtbnImprimir.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        jtbnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jtbnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 640, 146, 54));

        jpDetallesReserva.setBackground(new java.awt.Color(164, 221, 234));

        lblEstadoPago.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblEstadoPago.setText("Estado:");

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOpaque(true);

        lblTipoPago.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTipoPago.setText("Tipo de pago:");

        lblInfoPrecios.setFont(new java.awt.Font("Maiandra GD", 2, 14)); // NOI18N
        lblInfoPrecios.setText("<html><b>Nota:</b> Si el precio total de productos, alojamiento y reserva no coinciden con los precios unitarios, se debe a que la modificación de los precios (habitacion y productos) no afectan a las reservas ya cobradas.</html>");

        lblNroTeléfono.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNroTeléfono.setText("Nro. de teléfono:");

        lblCorreo.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblCorreo.setText("Correo electrónico:");

        lblNacionalidad.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNacionalidad.setText("Nacionalidad:");

        lblFechaNacimiento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaNacimiento.setText("Fecha de nacimiento:");

        lblDocumento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblDocumento.setText("DNI:");

        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblApellido.setText("Apellido:");

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNombre.setText("Nombre:");

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOpaque(true);

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator10.setOpaque(true);

        lblDatosCliente.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblDatosCliente.setText("Cliente");

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setOpaque(true);

        lblProductosConsumidos.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblProductosConsumidos.setText("Productos consumidos");

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator13.setOpaque(true);

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator12.setOpaque(true);

        lblCantProductos.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblCantProductos.setText("Productos ()");

        jtbProdutosReserva.setFont(new java.awt.Font("Arial Narrow", 0, 20)); // NOI18N
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
        jtbProdutosReserva.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtbProdutosReserva.setRowHeight(21);
        jtbProdutosReserva.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbProdutosReserva.setSelectionForeground(new java.awt.Color(102, 102, 102));
        jtbProdutosReserva.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbProdutosReserva.getTableHeader().setReorderingAllowed(false);
        jspProductosReserva.setViewportView(jtbProdutosReserva);

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator17.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator17.setOpaque(true);

        lblTotalProductos.setFont(new java.awt.Font("Maiandra GD", 0, 20)); // NOI18N
        lblTotalProductos.setText("Total productos:");

        lblProductosConsumidos1.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblProductosConsumidos1.setText("Totales");

        lblTotalAlojamiento.setFont(new java.awt.Font("Maiandra GD", 0, 20)); // NOI18N
        lblTotalAlojamiento.setText("Total alojamiento:");

        lblTotalReserva.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        lblTotalReserva.setText("Total reserva:");

        lblHabitacion.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblHabitacion.setText("Habitación");

        lblTotalDias.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTotalDias.setText("Total días:");

        lblHoraSalida.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblHoraSalida.setText("Hora de salida:");

        lblAlojamiento.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblAlojamiento.setText("Alojamiento");

        lblPago.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        lblPago.setText("Pago");

        lblEstadoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblEstadoHabitacion.setText("Estado:");

        lblFechaEntrada.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaEntrada.setText("Fecha de entrada:");

        lblDetalles.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblDetalles.setText("Detalles:");

        lblNroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblNroHabitacion.setText("Nro. habitación:");

        lblFechaSalida.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblFechaSalida.setText("Fecha de salida:");

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setOpaque(true);

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setOpaque(true);

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator8.setOpaque(true);

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator9.setOpaque(true);

        lblPrecioDia.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblPrecioDia.setText("Precio por día:");

        lblTipoHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblTipoHabitacion.setText("Tipo:");

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator11.setOpaque(true);

        lblHoraEntrada.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        lblHoraEntrada.setText("Hora de entrada:");

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator14.setOpaque(true);

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator15.setOpaque(true);

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator16.setOpaque(true);

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator18.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator18.setOpaque(true);

        javax.swing.GroupLayout jpDetallesReservaLayout = new javax.swing.GroupLayout(jpDetallesReserva);
        jpDetallesReserva.setLayout(jpDetallesReservaLayout);
        jpDetallesReservaLayout.setHorizontalGroup(
            jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductosConsumidos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addComponent(lblFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addComponent(lblFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalDias, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addComponent(lblNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblPrecioDia, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addComponent(lblTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblEstadoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspProductosReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNroTeléfono, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPago, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductosConsumidos1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(920, 920, 920)
                .addComponent(lblTotalReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(920, 920, 920)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpDetallesReservaLayout.setVerticalGroup(
            jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDatosCliente)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlojamiento)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductosConsumidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addComponent(lblCantProductos)
                        .addGap(12, 12, 12)
                        .addComponent(jspProductosReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(17, 17, 17)
                                .addComponent(lblApellido)
                                .addGap(17, 17, 17)
                                .addComponent(lblDocumento)
                                .addGap(17, 17, 17)
                                .addComponent(lblFechaNacimiento)
                                .addGap(17, 17, 17)
                                .addComponent(lblNacionalidad)
                                .addGap(17, 17, 17)
                                .addComponent(lblCorreo))
                            .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaEntrada)
                                    .addComponent(lblHoraEntrada))
                                .addGap(17, 17, 17)
                                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaSalida)
                                    .addComponent(lblHoraSalida))
                                .addGap(17, 17, 17)
                                .addComponent(lblTotalDias)
                                .addGap(7, 7, 7)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(lblHabitacion)
                                .addGap(7, 7, 7)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNroHabitacion)
                                    .addComponent(lblPrecioDia))
                                .addGap(17, 17, 17)
                                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipoHabitacion)
                                    .addComponent(lblEstadoHabitacion))))))
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblDetalles))
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNroTeléfono)))
                .addGap(17, 17, 17)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblInfoPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoPago)
                            .addComponent(lblTotalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstadoPago)
                            .addComponent(lblTotalAlojamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpDetallesReservaLayout.createSequentialGroup()
                        .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductosConsumidos1))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jpDetallesReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(lblTotalReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jpDetallesReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 1340, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnSalirActionPerformed
        dispose();

    }//GEN-LAST:event_jtbnSalirActionPerformed

    private void jtbnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnImprimirActionPerformed
        negocioReserva.imprimirDetallesReserva(jpDetallesReserva);
    }//GEN-LAST:event_jtbnImprimirActionPerformed

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
    private javax.swing.JPanel jpDetallesReserva;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JScrollPane jspProductosReserva;
    private javax.swing.JTable jtbProdutosReserva;
    private javax.swing.JButton jtbnImprimir;
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
    private javax.swing.JLabel lblIconoReserva;
    private javax.swing.JLabel lblInfoPrecios;
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
