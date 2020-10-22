package Presentación;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Cliente;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Producto;
import Negocio.Entidades.Reserva;
import Negocio.NegocioCliente;
import Negocio.UtilidadJFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Negocio.NegocioHabitacion;
import Negocio.NegocioProducto;
import Negocio.NegocioReserva;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJOptionPane;
import Negocio.UtilidadJTable;
import Negocio.sesionUsuario;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class frmPrincipal extends javax.swing.JFrame implements Runnable {

    private UtilidadJFrame utilidadJframe;
    private UtilidadJTable utilidadJtable;

    private DefaultTableModel dtmHabitaciones;
    private List<Habitacion> habitaciones;
    private NegocioHabitacion negocioHabitacion;

    private DefaultTableModel dtmReservas;
    private List<Reserva> reservas;
    private NegocioReserva negocioReserva;

    private DefaultTableModel dtmClientes;
    private List<Cliente> clientes;
    private NegocioCliente negocioCliente;

    private DefaultTableModel dtmProd;
    private List<Producto> productos;
    private NegocioProducto negocioProducto;

    Thread hilo;

    public frmPrincipal() {

        initComponents();

        iniciarHiloHora();

        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Gestión hotelera", this);

        utilidadJframe.guardarPanelesPrincipal(jpHabitaciones, jpClientes, jpReservas, jpProductos, jpInicio);

        utilidadJframe.activarPanelPrincipal(true, false, false, false, false);

        mostrarUsuariologueado();

        dtmHabitaciones = (DefaultTableModel) jtbHabitaciones.getModel();
        dtmReservas = (DefaultTableModel) jtbReservas.getModel();
        dtmClientes = (DefaultTableModel) jtbClientes.getModel();
        dtmProd = (DefaultTableModel) jtbProductos.getModel();

        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
        clientes = new ArrayList<>();
        productos = new ArrayList<>();

        negocioHabitacion = new NegocioHabitacion();
        negocioReserva = new NegocioReserva();
        negocioCliente = new NegocioCliente();
        negocioProducto = new NegocioProducto();

        utilidadJtable = new UtilidadJTable();

        utilidadJtable.centrarElementosTable(jtbHabitaciones);
        utilidadJtable.centrarElementosTable(jtbReservas);
        utilidadJtable.centrarElementosTable(jtbClientes);
        utilidadJtable.centrarElementosTable(jtbProductos);

        activarPanelInicio();
    }

    private void iniciarHiloHora() {
        hilo = new Thread(this);
        hilo.start();
    }

    private void activarPanelHabitaciones() {

        utilidadJframe.activarPanelPrincipal(true, false, false, false, false);
        activarBotonesPanelHabitaciones(false, false);
        actualizarTablaHabitaciones();
        mostrarCantEstadosHabitaciones();
    }

    private void activarPanelReservas() {
        utilidadJframe.activarPanelPrincipal(false, false, true, false, false);
        activarBotonesPanelReservas(false, false, false, false);
        actualizarTablaReservas();
        mostrarCantEstadosReservas();
    }

    private void activarPanelInicio() {
        try {
            utilidadJframe.activarPanelPrincipal(false, false, false, false, true);
            //String nombreUsuario = sesionUsuario.getSesionUsuario().getUsuario().getUsuario();
            String nombreUsuario = "temporal";
            lblBienvenida.setText("¡Bienvenido usuario " + nombreUsuario + "!");
            mostrarFechaHoraInicio();
        } catch (Exception e) {
            System.err.println("Error, el usuario todavía no ha iniciado sesión.");
            System.exit(0);
        }
    }

    private void activarPanelClientes() {
        utilidadJframe.activarPanelPrincipal(false, true, false, false, false);
        activarBotonesPanelCliente(false);
        actualizarTablaClientes();
        mostrarCantClientes();
    }

    private void activarPanelProd() {
        utilidadJframe.activarPanelPrincipal(false, false, false, true, false);
        activarBotonesPanelProd(false);
        actualizarTablaProd();
        mostrarCantProd();
    }

    private void mostrarFechaHoraInicio() {
        lblFechaHora.setText(UtilidadGeneral.getFechaActual() + ", " + UtilidadGeneral.getHoraActual());
        lblFechaHoraTitulo.setText(UtilidadGeneral.getFechaActual() + ", " + UtilidadGeneral.getHoraActual());
    }

    private void mostrarUsuariologueado() {
        try {
            //String nombreUsuario = sesionUsuario.getSesionUsuario().getUsuario().getUsuario();
            String nombreUsuario = "temporal";
            lblUsuarioLogueado.setText("Usuario: " + nombreUsuario);
        } catch (Exception e) {
            System.err.println("Error, el usuario todavía no ha iniciado sesión.");
            System.exit(0);
        }
    }

    private void activarBotonesPanelHabitaciones(boolean modificarHabitacion, boolean EliminarHabitacion) {
        jtbnModificarHabitacion.setEnabled(modificarHabitacion);
        jtbnEliminarHabitacion.setEnabled(EliminarHabitacion);
    }

    private void mostrarDatosHabitaciones(List<Habitacion> habitaciones) {

        if (habitaciones.isEmpty()) {
            habitaciones = negocioHabitacion.getHabitaciones();
        }

        dtmHabitaciones.setRowCount(0);

        for (Habitacion habitacion : habitaciones) {
            dtmHabitaciones.addRow(new Object[]{habitacion.getId(), habitacion.getNumero(), habitacion.getTipo(), habitacion.getEstado(), habitacion.getDetalles(), habitacion.getPrecioDia()});
        }
    }

    private void mostrarCantEstadosHabitaciones() {
        lblRegistradas.setText("Registradas (" + negocioHabitacion.getHabitaciones().size() + ")");
        lblDisponibles.setText("Disponibles (" + negocioHabitacion.getCountHabitacionesByEstado("Disponible") + ")");
        lblOcupadas.setText("Ocupadas (" + negocioHabitacion.getCountHabitacionesByEstado("Ocupada") + ")");
        lblLimpieza.setText("Limpieza (" + negocioHabitacion.getCountHabitacionesByEstado("Limpieza") + ")");
        lblReparación.setText("Reparación (" + negocioHabitacion.getCountHabitacionesByEstado("Reparación") + ")");
    }

    private void mostrarCantClientes() {
        lblClientes.setText("Clientes (" + negocioCliente.getClientes().size() + ")");
    }

    public void actualizarTablaHabitaciones() {
        mostrarDatosHabitaciones(negocioHabitacion.getHabitaciones());
    }

    private void mostrarDatosReservas(List<Reserva> reservas) {
        if (reservas.isEmpty()) {
            reservas = negocioReserva.getReservas();
        }
        dtmReservas.setRowCount(0);
        for (Reserva reserva : reservas) {
            dtmReservas.addRow(new Object[]{reserva.getId(), reserva.getCliente().getNombres(), reserva.getCliente().getApellidos(), reserva.getCliente().getDni(), reserva.getHabitacion().getNumero(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getHoraEntrada(), reserva.getHoraSalida(), reserva.getEstado(), reserva.getPrecioTotal()});
        }
    }

    private void actualizarTablaReservas() {
        mostrarDatosReservas(negocioReserva.getReservas());
    }

    private void mostrarCantEstadosReservas() {
        lblOcupadasReserva.setText("Ocupadas (" + negocioReserva.getReservas().size() + ")");
        lblCobradasReserva.setText("Cobradas (" + negocioReserva.getCountReservasByEstado("Cobrado") + ")");
        lblPendientesReserva.setText("Pendientes (" + negocioReserva.getCountReservasByEstado("Pendiente") + ")");
    }

    private void activarBotonesPanelReservas(boolean modificarReserva, boolean cobrar, boolean verDetalles, boolean venderProducto) {
        jtbnModificarReserva.setEnabled(modificarReserva);
        jtbnCobrar.setEnabled(cobrar);
        jtbnVerDetalles.setEnabled(verDetalles);
        jbtnVenderProducto.setEnabled(venderProducto);
    }

    private void mostrarDatosClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            clientes = negocioCliente.getClientes();
        }
        dtmClientes.setRowCount(0);
        for (Cliente cliente : clientes) {
            dtmClientes.addRow(new Object[]{cliente.getId(), cliente.getNombres(), cliente.getApellidos(),
                cliente.getDni(), cliente.getFechaNacimiento(), cliente.getNacionalidad(),
                cliente.getCorreo(), cliente.getNroTelefono()});
        }
    }

    private void actualizarTablaClientes() {
        mostrarDatosClientes(negocioCliente.getClientes());
    }

    private void mostrarDatosProd(List<Producto> productos) {
        if (productos.isEmpty()) {
            productos = negocioProducto.getProductos();
        }
        dtmProd.setRowCount(0);
        for (Producto prod : productos) {
            dtmProd.addRow(new Object[]{prod.getId(), prod.getNombre(), prod.getMarca(), prod.getCategoria(),
                prod.getStock(), prod.getProveedor(), prod.getPrecioCompra(), prod.getPrecioVenta()});
        }
    }

    private void actualizarTablaProd() {
        mostrarDatosProd(negocioProducto.getProductos());
    }

    private void mostrarCantProd() {
        lblCantProd.setText("Productos (" + negocioProducto.getProductos().size() + ")");
    }

    private void mostrarBusquedaClientes(String busqueda, int keyCode) {
        if (keyCode != KeyEvent.VK_CAPS_LOCK) {
            if (busqueda.equals("")) {
                actualizarTablaClientes();
                jcbFiltroClientes.setSelectedItem("Id");
            } else {
                clientes = negocioCliente.buscarClientes(busqueda);
                if (!clientes.isEmpty()) {
                    mostrarDatosClientes(clientes);
                }
            }
        }
    }

    private void mostrarBusquedaHabitacion(String busqueda, int keyCode) {
        // Por algún motivo se ejecutaba con bloq mayus
        if (keyCode != KeyEvent.VK_CAPS_LOCK) {
            if (busqueda.equals("")) {
                actualizarTablaHabitaciones();
                jcbFiltroHabitaciones.setSelectedItem("Id");
            } else {
                habitaciones = negocioHabitacion.buscarHabitacion(busqueda);
                if (!habitaciones.isEmpty()) {
                    mostrarDatosHabitaciones(habitaciones);
                }
            }
        }
    }

    private void activarBotonesPanelCliente(boolean modificarCliente) {
        jbtnModificarCliente.setEnabled(modificarCliente);
    }

    private void activarBotonesPanelProd(boolean modificarProd) {
        jbtnModificarProducto.setEnabled(modificarProd);
    }

    private void mostrarBusquedaReserva(String busqueda, int keyCode) {
        if (keyCode != KeyEvent.VK_CAPS_LOCK) {
            if (busqueda.equals("")) {
                actualizarTablaReservas();
                jcbFiltroReservas.setSelectedItem("Id");
            } else {
                reservas = negocioReserva.buscarReserva(busqueda);
                if (!reservas.isEmpty()) {
                    mostrarDatosReservas(reservas);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jpBotoneraNavegacion = new javax.swing.JPanel();
        jbtnClientes = new javax.swing.JButton();
        jtbnProductos = new javax.swing.JButton();
        jbtnReservas = new javax.swing.JButton();
        jbtnHabitaciones = new javax.swing.JButton();
        jbtnInicio = new javax.swing.JButton();
        jpHabitaciones = new javax.swing.JPanel();
        lblTituloHabitaciones = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jspHabitaciones = new javax.swing.JScrollPane();
        jtbHabitaciones = new javax.swing.JTable();
        lblDisponibles = new javax.swing.JLabel();
        lblOcupadas = new javax.swing.JLabel();
        lblRegistradas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcbFiltroHabitaciones = new javax.swing.JComboBox();
        jtbnCrearHabitacion = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        jtfBuscarHabitacion = new javax.swing.JTextField();
        jtbnModificarHabitacion = new javax.swing.JButton();
        jtbnEliminarHabitacion = new javax.swing.JButton();
        jbtnActualizarTablaHabitaciones = new javax.swing.JButton();
        lblLimpieza = new javax.swing.JLabel();
        lblReparación = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jpClientes = new javax.swing.JPanel();
        lblClientes = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jcbFiltroClientes = new javax.swing.JComboBox();
        jbtnModificarCliente = new javax.swing.JButton();
        lblBuscarCliente = new javax.swing.JLabel();
        lblTituloClientes = new javax.swing.JLabel();
        jtfBuscarCliente = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jbtnImprimirListaCliente = new javax.swing.JButton();
        jspClientes = new javax.swing.JScrollPane();
        jtbClientes = new javax.swing.JTable();
        jbtnActualizarTablaCliente = new javax.swing.JButton();
        jpReservas = new javax.swing.JPanel();
        lblTituloReservas = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jcbFiltroReservas = new javax.swing.JComboBox();
        jspReservas = new javax.swing.JScrollPane();
        jtbReservas = new javax.swing.JTable();
        jtbnCrearReserva = new javax.swing.JButton();
        jtbnModificarReserva = new javax.swing.JButton();
        jbtnActualizarTablaReservas = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtbnVerDetalles = new javax.swing.JButton();
        jtfBuscarReserva = new javax.swing.JTextField();
        lblBuscar1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jtbnCobrar = new javax.swing.JButton();
        lblOcupadasReserva = new javax.swing.JLabel();
        lblCobradasReserva = new javax.swing.JLabel();
        lblPendientesReserva = new javax.swing.JLabel();
        jbtnVenderProducto = new javax.swing.JButton();
        jpProductos = new javax.swing.JPanel();
        lblCantProd = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        lblFiltrarPor = new javax.swing.JLabel();
        jcbFiltroProductos = new javax.swing.JComboBox();
        jbtnAgregarProd = new javax.swing.JButton();
        lblBuscarProd = new javax.swing.JLabel();
        lblProductos = new javax.swing.JLabel();
        jtfBuscarProd = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jspProductos = new javax.swing.JScrollPane();
        jtbProductos = new javax.swing.JTable();
        jbtnActualizarTablaProductos = new javax.swing.JButton();
        jbtnModificarProducto = new javax.swing.JButton();
        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblUsuarioLogueado = new javax.swing.JLabel();
        lblFechaHoraTitulo = new javax.swing.JLabel();
        jpInicio = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        lblTituloInicio = new javax.swing.JLabel();
        lblBienvenida = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jcCalendarioInicio = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnSalir.setBackground(new java.awt.Color(255, 51, 51));
        jbtnSalir.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 18)); // NOI18N
        jbtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalir.setText("Salir");
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 650, 99, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 1360, 10));

        jpBotoneraNavegacion.setBackground(new java.awt.Color(0, 102, 153));

        jbtnClientes.setBackground(new java.awt.Color(0, 0, 0));
        jbtnClientes.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jbtnClientes.setForeground(new java.awt.Color(255, 255, 255));
        jbtnClientes.setText("Clientes");
        jbtnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClientesActionPerformed(evt);
            }
        });

        jtbnProductos.setBackground(new java.awt.Color(0, 0, 0));
        jtbnProductos.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jtbnProductos.setForeground(new java.awt.Color(255, 255, 255));
        jtbnProductos.setText("Productos");
        jtbnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnProductosActionPerformed(evt);
            }
        });

        jbtnReservas.setBackground(new java.awt.Color(0, 0, 0));
        jbtnReservas.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jbtnReservas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnReservas.setText("Reservas");
        jbtnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReservasActionPerformed(evt);
            }
        });

        jbtnHabitaciones.setBackground(new java.awt.Color(0, 0, 0));
        jbtnHabitaciones.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jbtnHabitaciones.setForeground(new java.awt.Color(255, 255, 255));
        jbtnHabitaciones.setText("Habitaciones");
        jbtnHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHabitacionesActionPerformed(evt);
            }
        });

        jbtnInicio.setBackground(new java.awt.Color(0, 0, 0));
        jbtnInicio.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jbtnInicio.setForeground(new java.awt.Color(255, 255, 255));
        jbtnInicio.setText("Inicio");
        jbtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBotoneraNavegacionLayout = new javax.swing.GroupLayout(jpBotoneraNavegacion);
        jpBotoneraNavegacion.setLayout(jpBotoneraNavegacionLayout);
        jpBotoneraNavegacionLayout.setHorizontalGroup(
            jpBotoneraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoneraNavegacionLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jpBotoneraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbtnHabitaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbnProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jpBotoneraNavegacionLayout.setVerticalGroup(
            jpBotoneraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBotoneraNavegacionLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jbtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jbtnHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jbtnReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jbtnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jtbnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        getContentPane().add(jpBotoneraNavegacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 116, -1, 526));

        jpHabitaciones.setBackground(new java.awt.Color(255, 255, 255));
        jpHabitaciones.setPreferredSize(new java.awt.Dimension(200, 200));

        lblTituloHabitaciones.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloHabitaciones.setFont(new java.awt.Font("Maiandra GD", 0, 24)); // NOI18N
        lblTituloHabitaciones.setText("Habitaciones");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jtbHabitaciones.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Número", "Tipo", "Estado", "Detalles", "Precio / día"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
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
        jtbHabitaciones.setGridColor(new java.awt.Color(0, 0, 0));
        jtbHabitaciones.setRowHeight(30);
        jtbHabitaciones.setRowMargin(5);
        jtbHabitaciones.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbHabitaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbHabitaciones.getTableHeader().setReorderingAllowed(false);
        jtbHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbHabitacionesMouseClicked(evt);
            }
        });
        jspHabitaciones.setViewportView(jtbHabitaciones);
        if (jtbHabitaciones.getColumnModel().getColumnCount() > 0) {
            jtbHabitaciones.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        lblDisponibles.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblDisponibles.setText("Disponibles:");

        lblOcupadas.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblOcupadas.setText("Ocupadas: ");

        lblRegistradas.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblRegistradas.setText("Registradas:");

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jLabel1.setText("Filtrar por ");

        jcbFiltroHabitaciones.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbFiltroHabitaciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Numero", "Tipo", "Estado", "Precio" }));
        jcbFiltroHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroHabitacionesActionPerformed(evt);
            }
        });

        jtbnCrearHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnCrearHabitacion.setText("Crear habitación");
        jtbnCrearHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCrearHabitacionActionPerformed(evt);
            }
        });

        lblBuscar.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblBuscar.setText("Buscar");

        jtfBuscarHabitacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfBuscarHabitacionFocusGained(evt);
            }
        });
        jtfBuscarHabitacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarHabitacionKeyReleased(evt);
            }
        });

        jtbnModificarHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnModificarHabitacion.setText("Modificar habitación");
        jtbnModificarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnModificarHabitacionActionPerformed(evt);
            }
        });

        jtbnEliminarHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnEliminarHabitacion.setText("Eliminar habitación");
        jtbnEliminarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnEliminarHabitacionActionPerformed(evt);
            }
        });

        jbtnActualizarTablaHabitaciones.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnActualizarTablaHabitaciones.setText("Actualizar tabla");
        jbtnActualizarTablaHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaHabitacionesActionPerformed(evt);
            }
        });

        lblLimpieza.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblLimpieza.setText("Limpieza:");

        lblReparación.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblReparación.setText("Reparación:");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpHabitacionesLayout = new javax.swing.GroupLayout(jpHabitaciones);
        jpHabitaciones.setLayout(jpHabitacionesLayout);
        jpHabitacionesLayout.setHorizontalGroup(
            jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jspHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtnActualizarTablaHabitaciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtbnCrearHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtbnModificarHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtbnEliminarHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblRegistradas)
                                .addGap(56, 56, 56)
                                .addComponent(lblDisponibles)
                                .addGap(56, 56, 56)
                                .addComponent(lblOcupadas)
                                .addGap(56, 56, 56)
                                .addComponent(lblLimpieza)
                                .addGap(56, 56, 56)
                                .addComponent(lblReparación)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                .addGap(461, 461, 461)
                                .addComponent(lblTituloHabitaciones))
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbFiltroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfBuscarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(127, 127, 127)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpHabitacionesLayout.setVerticalGroup(
            jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpHabitacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloHabitaciones)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBuscar)
                        .addComponent(jtfBuscarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jcbFiltroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisponibles)
                    .addComponent(lblOcupadas)
                    .addComponent(lblRegistradas)
                    .addComponent(lblLimpieza)
                    .addComponent(lblReparación))
                .addGap(18, 18, 18)
                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addComponent(jtbnCrearHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnModificarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnEliminarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnActualizarTablaHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jpHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 1185, 510));

        jpClientes.setBackground(new java.awt.Color(255, 255, 255));
        jpClientes.setPreferredSize(new java.awt.Dimension(200, 200));
        jpClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblClientes.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblClientes.setText("Clientes:");
        jpClientes.add(lblClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jpClientes.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 1020, 10));

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jLabel3.setText("Filtrar por ");
        jpClientes.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 75, -1, -1));

        jcbFiltroClientes.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbFiltroClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre", "Apellido", "DNI", "Fecha nacimiento", "País", "Correo", "Teléfono" }));
        jcbFiltroClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroClientesActionPerformed(evt);
            }
        });
        jpClientes.add(jcbFiltroClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 148, -1));
        jcbFiltroClientes.getAccessibleContext().setAccessibleDescription("");

        jbtnModificarCliente.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnModificarCliente.setText("Modificar cliente");
        jbtnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarClienteActionPerformed(evt);
            }
        });
        jpClientes.add(jbtnModificarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1005, 160, 160, 53));

        lblBuscarCliente.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblBuscarCliente.setText("Buscar");
        jpClientes.add(lblBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 78, -1, -1));

        lblTituloClientes.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloClientes.setFont(new java.awt.Font("Maiandra GD", 0, 24)); // NOI18N
        lblTituloClientes.setText("Clientes");
        jpClientes.add(lblTituloClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 5, -1, -1));

        jtfBuscarCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfBuscarClienteFocusGained(evt);
            }
        });
        jtfBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarClienteKeyReleased(evt);
            }
        });
        jpClientes.add(jtfBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 75, 227, -1));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jpClientes.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 1020, 10));

        jbtnImprimirListaCliente.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnImprimirListaCliente.setText("Imprimir lista");
        jbtnImprimirListaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnImprimirListaClienteActionPerformed(evt);
            }
        });
        jpClientes.add(jbtnImprimirListaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 450, 160, 53));

        jtbClientes.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellido", "DNI", "Fecha nacimiento", "País", "Correo", "Nro. teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbClientes.setGridColor(new java.awt.Color(0, 0, 0));
        jtbClientes.setRowHeight(30);
        jtbClientes.setRowMargin(5);
        jtbClientes.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbClientes.getTableHeader().setReorderingAllowed(false);
        jtbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbClientesMouseClicked(evt);
            }
        });
        jspClientes.setViewportView(jtbClientes);
        if (jtbClientes.getColumnModel().getColumnCount() > 0) {
            jtbClientes.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jpClientes.add(jspClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 810, 280));

        jbtnActualizarTablaCliente.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnActualizarTablaCliente.setText("Actualizar tabla");
        jbtnActualizarTablaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaClienteActionPerformed(evt);
            }
        });
        jpClientes.add(jbtnActualizarTablaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 160, 53));

        getContentPane().add(jpClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 1185, 510));

        jpReservas.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloReservas.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloReservas.setFont(new java.awt.Font("Maiandra GD", 0, 24)); // NOI18N
        lblTituloReservas.setText("Reservas");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jcbFiltroReservas.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbFiltroReservas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre", "Apellido", "DNI", "Nro. habitación", "Fecha entrada", "Fecha salida", "Hora entrada", "Hora salida", "Estado", "Precio total" }));
        jcbFiltroReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroReservasActionPerformed(evt);
            }
        });

        jtbReservas.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellido", "DNI", "Nro. habitación", "Fecha entrada", "Fecha salida", "Hora entrada", "Hora salida", "Estado", "Precio total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbReservas.setGridColor(new java.awt.Color(0, 0, 0));
        jtbReservas.setRowHeight(30);
        jtbReservas.setRowMargin(5);
        jtbReservas.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbReservas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbReservas.getTableHeader().setReorderingAllowed(false);
        jtbReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbReservasMouseClicked(evt);
            }
        });
        jspReservas.setViewportView(jtbReservas);
        if (jtbReservas.getColumnModel().getColumnCount() > 0) {
            jtbReservas.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jtbnCrearReserva.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnCrearReserva.setText("Crear reserva");
        jtbnCrearReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCrearReservaActionPerformed(evt);
            }
        });

        jtbnModificarReserva.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnModificarReserva.setText("Modificar reserva");
        jtbnModificarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnModificarReservaActionPerformed(evt);
            }
        });

        jbtnActualizarTablaReservas.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnActualizarTablaReservas.setText("Actualizar tabla");
        jbtnActualizarTablaReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaReservasActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jLabel2.setText("Filtrar por ");

        jtbnVerDetalles.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnVerDetalles.setText("Ver detalles");
        jtbnVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnVerDetallesActionPerformed(evt);
            }
        });

        jtfBuscarReserva.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfBuscarReservaFocusGained(evt);
            }
        });
        jtfBuscarReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarReservaKeyReleased(evt);
            }
        });

        lblBuscar1.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblBuscar1.setText("Buscar");

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jtbnCobrar.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        jtbnCobrar.setText("Cobrar");
        jtbnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCobrarActionPerformed(evt);
            }
        });

        lblOcupadasReserva.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblOcupadasReserva.setText("Ocupadas: ");

        lblCobradasReserva.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblCobradasReserva.setText("Cobradas:");

        lblPendientesReserva.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblPendientesReserva.setText("Pendientes:");

        jbtnVenderProducto.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnVenderProducto.setText("Vender producto");
        jbtnVenderProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnVenderProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpReservasLayout = new javax.swing.GroupLayout(jpReservas);
        jpReservas.setLayout(jpReservasLayout);
        jpReservasLayout.setHorizontalGroup(
            jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReservasLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addComponent(lblOcupadasReserva)
                        .addGap(56, 56, 56)
                        .addComponent(lblCobradasReserva)
                        .addGap(56, 56, 56)
                        .addComponent(lblPendientesReserva)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator7))
                        .addGap(78, 78, 78))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpReservasLayout.createSequentialGroup()
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(lblTituloReservas))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpReservasLayout.createSequentialGroup()
                                .addComponent(jbtnActualizarTablaReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(236, 236, 236)
                                .addComponent(jtbnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(236, 236, 236)
                                .addComponent(jtbnVerDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpReservasLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbFiltroReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblBuscar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfBuscarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jspReservas, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtbnModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtbnCrearReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jbtnVenderProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        jpReservasLayout.setVerticalGroup(
            jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReservasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloReservas)
                .addGap(18, 18, 18)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpReservasLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbFiltroReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBuscar1)
                                    .addComponent(jtfBuscarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblOcupadasReserva))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpReservasLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPendientesReserva))))
                    .addComponent(lblCobradasReserva))
                .addGap(18, 18, 18)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpReservasLayout.createSequentialGroup()
                        .addComponent(jtbnCrearReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnVenderProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpReservasLayout.createSequentialGroup()
                        .addComponent(jspReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnActualizarTablaReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnVerDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        getContentPane().add(jpReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 1185, 510));

        jpProductos.setBackground(new java.awt.Color(255, 255, 255));
        jpProductos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCantProd.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblCantProd.setText("Productos:");
        jpProductos.add(lblCantProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        jpProductos.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 1020, 10));

        lblFiltrarPor.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblFiltrarPor.setText("Filtrar por ");
        jpProductos.add(lblFiltrarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 75, -1, -1));

        jcbFiltroProductos.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbFiltroProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre", "Marca", "Categoria", "Stock", "Proveedor", "Precio de compra", "Precio de venta" }));
        jcbFiltroProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroProductosActionPerformed(evt);
            }
        });
        jpProductos.add(jcbFiltroProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 148, -1));

        jbtnAgregarProd.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnAgregarProd.setText("Agregar producto");
        jbtnAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarProdActionPerformed(evt);
            }
        });
        jpProductos.add(jbtnAgregarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(1005, 160, 160, 53));

        lblBuscarProd.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblBuscarProd.setText("Buscar");
        jpProductos.add(lblBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 78, -1, -1));

        lblProductos.setBackground(new java.awt.Color(255, 255, 255));
        lblProductos.setFont(new java.awt.Font("Maiandra GD", 0, 24)); // NOI18N
        lblProductos.setText("Productos");
        jpProductos.add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 5, -1, -1));

        jtfBuscarProd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfBuscarProdFocusGained(evt);
            }
        });
        jtfBuscarProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarProdKeyReleased(evt);
            }
        });
        jpProductos.add(jtfBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 75, 227, -1));

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        jpProductos.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 1020, 10));

        jtbProductos.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Marca", "Categoria", "Stock", "Proveedor", "Precio compra", "Precio venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbProductos.setGridColor(new java.awt.Color(0, 0, 0));
        jtbProductos.setRowHeight(30);
        jtbProductos.setRowMargin(5);
        jtbProductos.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtbProductos.getTableHeader().setReorderingAllowed(false);
        jtbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbProductosMouseClicked(evt);
            }
        });
        jspProductos.setViewportView(jtbProductos);
        if (jtbProductos.getColumnModel().getColumnCount() > 0) {
            jtbProductos.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jpProductos.add(jspProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 810, 280));

        jbtnActualizarTablaProductos.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnActualizarTablaProductos.setText("Actualizar tabla");
        jbtnActualizarTablaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaProductosActionPerformed(evt);
            }
        });
        jpProductos.add(jbtnActualizarTablaProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 160, 53));

        jbtnModificarProducto.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnModificarProducto.setText("Modificar producto");
        jbtnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarProductoActionPerformed(evt);
            }
        });
        jpProductos.add(jbtnModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1005, 230, 160, 53));

        getContentPane().add(jpProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 1185, 510));

        jpTitulo.setBackground(new java.awt.Color(0, 0, 0));

        lblTituloPrincipal.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("MyHotel");

        lblUsuarioLogueado.setBackground(new java.awt.Color(0, 0, 0));
        lblUsuarioLogueado.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblUsuarioLogueado.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioLogueado.setText("Usuario:");

        lblFechaHoraTitulo.setBackground(new java.awt.Color(0, 0, 0));
        lblFechaHoraTitulo.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblFechaHoraTitulo.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(623, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(385, 385, 385)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaHoraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuarioLogueado, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloPrincipal)
                    .addGroup(jpTituloLayout.createSequentialGroup()
                        .addComponent(lblUsuarioLogueado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaHoraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 1360, 120));

        jpInicio.setBackground(new java.awt.Color(255, 255, 255));
        jpInicio.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        lblTituloInicio.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloInicio.setFont(new java.awt.Font("Maiandra GD", 0, 24)); // NOI18N
        lblTituloInicio.setText("Inicio");
        lblTituloInicio.setPreferredSize(new java.awt.Dimension(133, 30));

        lblBienvenida.setBackground(new java.awt.Color(255, 255, 255));
        lblBienvenida.setFont(new java.awt.Font("Maiandra GD", 3, 24)); // NOI18N
        lblBienvenida.setForeground(new java.awt.Color(255, 153, 153));
        lblBienvenida.setText("¡Bienvenido usuario!");
        lblBienvenida.setPreferredSize(new java.awt.Dimension(133, 30));

        lblFechaHora.setBackground(new java.awt.Color(255, 255, 255));
        lblFechaHora.setFont(new java.awt.Font("Maiandra GD", 2, 26)); // NOI18N
        lblFechaHora.setText("FechaHora");
        lblFechaHora.setPreferredSize(new java.awt.Dimension(133, 30));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jcCalendarioInicio.setBackground(new java.awt.Color(255, 255, 255));
        jcCalendarioInicio.setDecorationBackgroundColor(new java.awt.Color(255, 255, 255));
        jcCalendarioInicio.setDecorationBordersVisible(true);
        jcCalendarioInicio.setFont(new java.awt.Font("Maiandra GD", 0, 12)); // NOI18N

        javax.swing.GroupLayout jpInicioLayout = new javax.swing.GroupLayout(jpInicio);
        jpInicio.setLayout(jpInicioLayout);
        jpInicioLayout.setHorizontalGroup(
            jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInicioLayout.createSequentialGroup()
                .addGroup(jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpInicioLayout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpInicioLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpInicioLayout.createSequentialGroup()
                                .addGap(418, 418, 418)
                                .addComponent(lblTituloInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpInicioLayout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpInicioLayout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpInicioLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jcCalendarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jpInicioLayout.setVerticalGroup(
            jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInicioLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblTituloInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jcCalendarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(jpInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 118, 1180, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jbtnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClientesActionPerformed
        activarPanelClientes();
    }//GEN-LAST:event_jbtnClientesActionPerformed

    private void jbtnHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHabitacionesActionPerformed
        activarPanelHabitaciones();
    }//GEN-LAST:event_jbtnHabitacionesActionPerformed

    private void jbtnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReservasActionPerformed
        activarPanelReservas();
    }//GEN-LAST:event_jbtnReservasActionPerformed

    private void jtbnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnProductosActionPerformed
        activarPanelProd();
    }//GEN-LAST:event_jtbnProductosActionPerformed

    private void jtbnCrearHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCrearHabitacionActionPerformed
        frmHabitacion jfrHabitacion = new frmHabitacion();
        jfrHabitacion.setVisible(true);
    }//GEN-LAST:event_jtbnCrearHabitacionActionPerformed

    private void jbtnActualizarTablaHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarTablaHabitacionesActionPerformed
        activarBotonesPanelHabitaciones(false, false);
        actualizarTablaHabitaciones();
        mostrarCantEstadosHabitaciones();
        jcbFiltroHabitaciones.setSelectedItem("Id");
    }//GEN-LAST:event_jbtnActualizarTablaHabitacionesActionPerformed

    private void jtbHabitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbHabitacionesMouseClicked
        activarBotonesPanelHabitaciones(true, true);
    }//GEN-LAST:event_jtbHabitacionesMouseClicked

    private void jtbnModificarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnModificarHabitacionActionPerformed

        frmHabitacion frmHabitacion = new frmHabitacion();
        int filaIndice = jtbHabitaciones.getSelectedRow();
        habitaciones = negocioHabitacion.getHabitaciones();

        Long habitacionId = Long.parseLong(dtmHabitaciones.getValueAt(filaIndice, 0).toString());

        Habitacion habitacion = negocioHabitacion.encontrarHabitacion(habitacionId);

        if (habitacion != null) {
            frmHabitacion.mostrarHabitacion(habitacion);
            frmHabitacion.setVisible(true);
        }
    }//GEN-LAST:event_jtbnModificarHabitacionActionPerformed

    private void jtbnEliminarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnEliminarHabitacionActionPerformed

        int filaIndice = jtbHabitaciones.getSelectedRow();
        Long habitacionId = (Long) dtmHabitaciones.getValueAt(filaIndice, 0);
        String mensaje = "¿Estás seguro que deseas eliminar la habitación con id " + habitacionId + "?";
        String titulo = "Confirmar eliminación";
        int confirmado = UtilidadJOptionPane.mostrarMensajeConfirmacion(mensaje, titulo);

        if (confirmado == JOptionPane.YES_OPTION) {
            try {
                negocioHabitacion.eliminarHabitacion(habitacionId);
                mensaje = "Habitación eliminada exitosamente.";
                titulo = "Habitacion eliminada";
                UtilidadJOptionPane.mostrarMensajeInformacion(mensaje, titulo);
                actualizarTablaHabitaciones();
            } catch (NonexistentEntityException ex) {
                System.out.println("Error, no existe la entidad. " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jtbnEliminarHabitacionActionPerformed

    private void jcbFiltroHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFiltroHabitacionesActionPerformed

        String campo = (String) jcbFiltroHabitaciones.getSelectedItem();

        if (campo.equals("Precio")) {
            campo = "precioDia";
        }

        habitaciones = negocioHabitacion.ordenarHabitaciones(campo);
        mostrarDatosHabitaciones(habitaciones);
        activarBotonesPanelHabitaciones(false, false);
    }//GEN-LAST:event_jcbFiltroHabitacionesActionPerformed

    private void jbtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInicioActionPerformed
        activarPanelInicio();
    }//GEN-LAST:event_jbtnInicioActionPerformed

    private void jcbFiltroReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFiltroReservasActionPerformed
        String campo = (String) jcbFiltroReservas.getSelectedItem();
        //Si el campo seleccionado corresponde a otra tabla (consulta diferente)
        boolean otraTabla = false;

        switch (campo) {
            case "Precio total":
                campo = "precioTotal";
                break;
            case "Fecha entrada":
                campo = "fechaEntrada";
                break;
            case "Fecha salida":
                campo = "fechaSalida";
                break;
            case "Hora entrada":
                campo = "horaEntrada";
                break;
            case "Hora salida":
                campo = "horaSalida";
                break;
            case "Nombre":
                campo = "nombres";
                otraTabla = true;
                break;
            case "Apellido":
                campo = "apellidos";
                otraTabla = true;
                break;
            case "DNI":
                campo = "dni";
                otraTabla = true;
                break;
            case "Nro. habitación":
                campo = "numero";
                otraTabla = true;
                break;
            default:
                break;
        }

        reservas = negocioReserva.ordenarReservas(campo, otraTabla);
        mostrarDatosReservas(reservas);
        activarBotonesPanelReservas(false, false, false, false);
    }//GEN-LAST:event_jcbFiltroReservasActionPerformed

    private void jtbReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbReservasMouseClicked
        activarBotonesPanelReservas(true, true, true, true);
    }//GEN-LAST:event_jtbReservasMouseClicked

    private void jtbnCrearReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCrearReservaActionPerformed
        frmReserva frmCrearReserva = new frmReserva();
        frmCrearReserva.setVisible(true);
    }//GEN-LAST:event_jtbnCrearReservaActionPerformed

    private void jtbnModificarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnModificarReservaActionPerformed
        frmReserva frmReserva = new frmReserva();
        int filaIndice = jtbReservas.getSelectedRow();
        Long reservaId = Long.parseLong(dtmReservas.getValueAt(filaIndice, 0).toString());

        Reserva reserva = negocioReserva.encontrarReserva(reservaId);

        if (reserva != null) {
            try {
                frmReserva.mostrarReserva(reserva);
            } catch (ParseException ex) {
                System.out.println("Error " + ex.getMessage());
            }
            frmReserva.setVisible(true);
        }
    }//GEN-LAST:event_jtbnModificarReservaActionPerformed

    private void jbtnActualizarTablaReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarTablaReservasActionPerformed
        activarBotonesPanelReservas(false, false, false, false);
        actualizarTablaReservas();
        mostrarCantEstadosReservas();
        jcbFiltroReservas.setSelectedItem("Id");
    }//GEN-LAST:event_jbtnActualizarTablaReservasActionPerformed

    private void jtbnVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnVerDetallesActionPerformed
        frmDetallesReserva frmDetallesReserva = new frmDetallesReserva();
        int filaIndice = jtbReservas.getSelectedRow();
        Long reservaId = Long.parseLong(dtmReservas.getValueAt(filaIndice, 0).toString());

        Reserva reserva = negocioReserva.encontrarReserva(reservaId);

        if (reserva != null) {
            frmDetallesReserva.mostrarDetallesReserva(reserva);
            frmDetallesReserva.setVisible(true);
        }
    }//GEN-LAST:event_jtbnVerDetallesActionPerformed

    private void jtbnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCobrarActionPerformed

    }//GEN-LAST:event_jtbnCobrarActionPerformed

    private void jbtnVenderProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVenderProductoActionPerformed

    }//GEN-LAST:event_jbtnVenderProductoActionPerformed

    private void jtfBuscarHabitacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarHabitacionKeyReleased
        mostrarBusquedaHabitacion(jtfBuscarHabitacion.getText(), evt.getKeyCode());
    }//GEN-LAST:event_jtfBuscarHabitacionKeyReleased

    private void jtfBuscarReservaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarReservaKeyReleased
        mostrarBusquedaReserva(jtfBuscarReserva.getText(), evt.getKeyCode());
    }//GEN-LAST:event_jtfBuscarReservaKeyReleased

    private void jtfBuscarHabitacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfBuscarHabitacionFocusGained
        if (!jtfBuscarHabitacion.getText().equals("")) {
            mostrarBusquedaHabitacion(jtfBuscarHabitacion.getText(), -1);
        }
    }//GEN-LAST:event_jtfBuscarHabitacionFocusGained

    private void jtfBuscarReservaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfBuscarReservaFocusGained
        if (!jtfBuscarReserva.getText().equals("")) {
            mostrarBusquedaReserva(jtfBuscarReserva.getText(), -1);
        }
    }//GEN-LAST:event_jtfBuscarReservaFocusGained

    private void jcbFiltroClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFiltroClientesActionPerformed

        String campo = (String) jcbFiltroClientes.getSelectedItem();

        switch (campo) {
            case "Nombre":
                campo = "nombres";
                break;
            case "Apellido":
                campo = "apellidos";
                break;
            case "Fecha nacimiento":
                campo = "fechaNacimiento";
                break;
            case "País":
                campo = "nacionalidad";
                break;
            case "Teléfono":
                campo = "nroTelefono";
                break;
        }
        clientes = negocioCliente.ordenarClientes(campo);
        mostrarDatosClientes(clientes);
        activarBotonesPanelCliente(false);
    }//GEN-LAST:event_jcbFiltroClientesActionPerformed

    private void jbtnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarClienteActionPerformed
        frmCliente frmCliente = new frmCliente();
        int filaIndice = jtbClientes.getSelectedRow();
        Long clienteId = Long.parseLong(dtmClientes.getValueAt(filaIndice, 0).toString());

        Cliente cliente = negocioCliente.encontrarCliente(clienteId);

        if (cliente != null) {
            try {
                frmCliente.mostrarCliente(cliente);
            } catch (ParseException ex) {
                System.out.println("Error " + ex.getMessage());
            }
            frmCliente.setVisible(true);
        }
    }//GEN-LAST:event_jbtnModificarClienteActionPerformed

    private void jtfBuscarClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfBuscarClienteFocusGained
        if (!jtfBuscarCliente.getText().equals("")) {
            mostrarBusquedaClientes(jtfBuscarCliente.getText(), -1);
        }
    }//GEN-LAST:event_jtfBuscarClienteFocusGained

    private void jtfBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarClienteKeyReleased
        mostrarBusquedaClientes(jtfBuscarCliente.getText(), evt.getKeyCode());
    }//GEN-LAST:event_jtfBuscarClienteKeyReleased

    private void jbtnImprimirListaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnImprimirListaClienteActionPerformed


    }//GEN-LAST:event_jbtnImprimirListaClienteActionPerformed

    private void jtbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbClientesMouseClicked
        activarBotonesPanelCliente(true);
    }//GEN-LAST:event_jtbClientesMouseClicked

    private void jbtnActualizarTablaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarTablaClienteActionPerformed
        activarBotonesPanelCliente(false);
        actualizarTablaClientes();
        mostrarCantClientes();
        jcbFiltroClientes.setSelectedItem("Id");
    }//GEN-LAST:event_jbtnActualizarTablaClienteActionPerformed

    private void jcbFiltroProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFiltroProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbFiltroProductosActionPerformed

    private void jbtnAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarProdActionPerformed
        frmProducto frmProducto = new frmProducto();
        frmProducto.setVisible(true);
    }//GEN-LAST:event_jbtnAgregarProdActionPerformed

    private void jtfBuscarProdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfBuscarProdFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfBuscarProdFocusGained

    private void jtfBuscarProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarProdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfBuscarProdKeyReleased

    private void jtbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbProductosMouseClicked
        activarBotonesPanelProd(true);
    }//GEN-LAST:event_jtbProductosMouseClicked

    private void jbtnActualizarTablaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarTablaProductosActionPerformed
        actualizarTablaProd();
        mostrarCantProd();
        activarBotonesPanelProd(false);
        jcbFiltroProductos.setSelectedItem("Id");
    }//GEN-LAST:event_jbtnActualizarTablaProductosActionPerformed

    private void jbtnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnModificarProductoActionPerformed
        frmProducto frmProducto = new frmProducto();
        int filaIndice = jtbProductos.getSelectedRow();
        Long productoId = Long.parseLong(dtmProd.getValueAt(filaIndice, 0).toString());

        Producto producto = negocioProducto.encontrarProd(productoId);

        if (producto != null) {
            frmProducto.mostrarProd(producto);
            frmProducto.setVisible(true);
        }
    }//GEN-LAST:event_jbtnModificarProductoActionPerformed

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
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton jbtnActualizarTablaCliente;
    private javax.swing.JButton jbtnActualizarTablaHabitaciones;
    private javax.swing.JButton jbtnActualizarTablaProductos;
    private javax.swing.JButton jbtnActualizarTablaReservas;
    private javax.swing.JButton jbtnAgregarProd;
    private javax.swing.JButton jbtnClientes;
    private javax.swing.JButton jbtnHabitaciones;
    private javax.swing.JButton jbtnImprimirListaCliente;
    private javax.swing.JButton jbtnInicio;
    private javax.swing.JButton jbtnModificarCliente;
    private javax.swing.JButton jbtnModificarProducto;
    private javax.swing.JButton jbtnReservas;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JButton jbtnVenderProducto;
    private com.toedter.calendar.JCalendar jcCalendarioInicio;
    private javax.swing.JComboBox jcbFiltroClientes;
    private javax.swing.JComboBox jcbFiltroHabitaciones;
    private javax.swing.JComboBox jcbFiltroProductos;
    private javax.swing.JComboBox jcbFiltroReservas;
    private javax.swing.JPanel jpBotoneraNavegacion;
    private javax.swing.JPanel jpClientes;
    private javax.swing.JPanel jpHabitaciones;
    private javax.swing.JPanel jpInicio;
    private javax.swing.JPanel jpProductos;
    private javax.swing.JPanel jpReservas;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JScrollPane jspClientes;
    private javax.swing.JScrollPane jspHabitaciones;
    private javax.swing.JScrollPane jspProductos;
    private javax.swing.JScrollPane jspReservas;
    private javax.swing.JTable jtbClientes;
    private javax.swing.JTable jtbHabitaciones;
    private javax.swing.JTable jtbProductos;
    private javax.swing.JTable jtbReservas;
    private javax.swing.JButton jtbnCobrar;
    private javax.swing.JButton jtbnCrearHabitacion;
    private javax.swing.JButton jtbnCrearReserva;
    private javax.swing.JButton jtbnEliminarHabitacion;
    private javax.swing.JButton jtbnModificarHabitacion;
    private javax.swing.JButton jtbnModificarReserva;
    private javax.swing.JButton jtbnProductos;
    private javax.swing.JButton jtbnVerDetalles;
    private javax.swing.JTextField jtfBuscarCliente;
    private javax.swing.JTextField jtfBuscarHabitacion;
    private javax.swing.JTextField jtfBuscarProd;
    private javax.swing.JTextField jtfBuscarReserva;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblBuscar1;
    private javax.swing.JLabel lblBuscarCliente;
    private javax.swing.JLabel lblBuscarProd;
    private javax.swing.JLabel lblCantProd;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblCobradasReserva;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblFechaHoraTitulo;
    private javax.swing.JLabel lblFiltrarPor;
    private javax.swing.JLabel lblLimpieza;
    private javax.swing.JLabel lblOcupadas;
    private javax.swing.JLabel lblOcupadasReserva;
    private javax.swing.JLabel lblPendientesReserva;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblRegistradas;
    private javax.swing.JLabel lblReparación;
    private javax.swing.JLabel lblTituloClientes;
    private javax.swing.JLabel lblTituloHabitaciones;
    private javax.swing.JLabel lblTituloInicio;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JLabel lblTituloReservas;
    private javax.swing.JLabel lblUsuarioLogueado;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();

        while (ct == hilo) {
            mostrarFechaHoraInicio();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido." + ie.getMessage());
            }
        }
    }
}
