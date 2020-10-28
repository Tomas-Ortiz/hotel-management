package Presentación;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Cliente;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Producto;
import Negocio.Entidades.Reserva;
import Negocio.Entidades.ReservaProducto;
import Negocio.NegocioCliente;
import Negocio.UtilidadJFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Negocio.NegocioHabitacion;
import Negocio.NegocioProducto;
import Negocio.NegocioReserva;
import Negocio.CellRenderer;
import Negocio.HeaderRenderer;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadIcono;
import Negocio.UtilidadJDialog;
import Negocio.UtilidadJOptionPane;
import Negocio.UtilidadJTable;
import Negocio.sesionUsuario;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class frmPrincipal extends javax.swing.JFrame implements Runnable {

    private final UtilidadJFrame utilidadJframe;

    private final DefaultTableModel dtmHabitaciones;
    private List<Habitacion> habitaciones;
    private final NegocioHabitacion negocioHabitacion;

    private final DefaultTableModel dtmReservas;
    private List<Reserva> reservas;
    private final NegocioReserva negocioReserva;

    private final DefaultTableModel dtmClientes;
    private List<Cliente> clientes;
    private final NegocioCliente negocioCliente;

    private final DefaultTableModel dtmProd;
    private List<Producto> productos;
    private List<Producto> productosDisp;
    private final NegocioProducto negocioProducto;

    CellRenderer renderTable;

    Thread hilo;

    public frmPrincipal() {
        initComponents();
        iniciarHiloHora();

        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("MyHotel", this);

        utilidadJframe.guardarPanelesPrincipal(jpHabitaciones, jpClientes, jpReservas, jpProductos, jpInicio);

        mostrarUsuariologueado();

        dtmHabitaciones = (DefaultTableModel) jtbHabitaciones.getModel();
        dtmReservas = (DefaultTableModel) jtbReservas.getModel();
        dtmClientes = (DefaultTableModel) jtbClientes.getModel();
        dtmProd = (DefaultTableModel) jtbProductos.getModel();

        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
        clientes = new ArrayList<>();
        productos = new ArrayList<>();

        negocioHabitacion = NegocioHabitacion.getNegocioHabitacion();
        negocioReserva = NegocioReserva.getNegocioReserva();
        negocioCliente = NegocioCliente.getNegocioCliente();
        negocioProducto = NegocioProducto.getNegocioProducto();

        configurarJTables();
        activarPanelInicio();
        mostrarIconos();
    }

    private void iniciarHiloHora() {
        hilo = new Thread(this);
        hilo.start();
    }

    private void configurarJTables() {
        UtilidadJTable.setCellRender(jtbClientes);
        UtilidadJTable.setCellRender(jtbHabitaciones);
        UtilidadJTable.setCellRender(jtbReservas);
        UtilidadJTable.setCellRender(jtbProductos);

        jtbHabitaciones.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        jtbClientes.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        jtbReservas.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        jtbProductos.getTableHeader().setDefaultRenderer(new HeaderRenderer());
    }

    private void mostrarIconos() {
        Image iconoHotel = new ImageIcon("src/Recursos/Iconos/iconoHotel.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoHotel, lblIconoHotelTitulo, 90, 90);

        Image iconoUsuario = new ImageIcon("src/Recursos/Iconos/iconoUsuario.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoUsuario, lblIconoUsuario, 53, 53);

        Image iconoInicio = new ImageIcon("src/Recursos/Iconos/iconoInicio.jpg").getImage();
        UtilidadIcono.cargarIconoLabel(iconoInicio, lblIconoInicio, 53, 53);

        Image iconoHab = new ImageIcon("src/Recursos/Iconos/iconoHabitacion.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoHab, lblIconoHabitaciones, 53, 53);

        Image iconoCliente = new ImageIcon("src/Recursos/Iconos/iconoCliente.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoCliente, lblIconoClientes, 45, 45);

        Image iconoReserva = new ImageIcon("src/Recursos/Iconos/iconoReserva.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoReserva, lblIconoReserva, 40, 40);

        Image iconoProd = new ImageIcon("src/Recursos/Iconos/iconoProducto.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoProd, lblIconoProductos, 40, 40);

        Image iconoSalir = new ImageIcon("src/Recursos/Iconos/iconoSalir.png").getImage();

        UtilidadIcono.cargarIconoButton(iconoSalir, jbtnSalir, 35, 35);

        UtilidadIcono.cargarIconoFrame(this);
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
        UtilidadJDialog.configurarJDialog(jdMostrarProductos);
        mostrarProdJDialog();
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
        activarBotonesPanelHabitaciones(false, false);
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
        activarBotonesPanelReservas(false, false, false, false);
    }

    private void mostrarCantEstadosReservas() {
        lblCantReservas.setText("Reservas (" + negocioReserva.getReservas().size() + ")");
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
        activarBotonesPanelCliente(false);
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
        activarBotonesPanelProd(false);
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

    private void mostrarBusquedaProd(String busqueda, int keyCode) {
        if (keyCode != KeyEvent.VK_CAPS_LOCK) {
            if (busqueda.equals("")) {
                actualizarTablaProd();
                jcbFiltroProductos.setSelectedItem("Id");
            } else {
                productos = negocioProducto.buscarProducto(busqueda);
                if (!productos.isEmpty()) {
                    mostrarDatosProd(productos);
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

    private void mostrarProdJDialog() {
        productosDisp = negocioProducto.getProductosDisponibles();
        jcbProductos.removeAllItems();
        for (Producto prod : productosDisp) {
            jcbProductos.addItem(prod.getNombre());
        }
    }

    private void mostrarMarca(String nombreProd) {
        for (Producto prod : productosDisp) {
            if (nombreProd.equals(prod.getNombre())) {
                lblMarca.setText(prod.getMarca());
            }
        }
    }

    private void reiniciarJDialogProd() {
        jcbProductos.setSelectedIndex(0);
        String nombreProd = (String) jcbProductos.getSelectedItem();
        mostrarMarca(nombreProd);
        jspCantProd.setValue(1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdMostrarProductos = new javax.swing.JDialog();
        jcbProductos = new javax.swing.JComboBox();
        jspCantProd = new javax.swing.JSpinner();
        jbtnAgregarProdReserva = new javax.swing.JButton();
        jbtnCancelarProdReserva = new javax.swing.JButton();
        seleccionarProd = new javax.swing.JLabel();
        lblSeleccionarCant = new javax.swing.JLabel();
        lblMarcaProd = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
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
        lblIconoHabitaciones = new javax.swing.JLabel();
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
        lblIconoClientes = new javax.swing.JLabel();
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
        lblCantReservas = new javax.swing.JLabel();
        lblCobradasReserva = new javax.swing.JLabel();
        lblPendientesReserva = new javax.swing.JLabel();
        jbtnVenderProducto = new javax.swing.JButton();
        lblIconoReserva = new javax.swing.JLabel();
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
        lblIconoProductos = new javax.swing.JLabel();
        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblUsuarioLogueado = new javax.swing.JLabel();
        lblFechaHoraTitulo = new javax.swing.JLabel();
        lblIconoHotelTitulo = new javax.swing.JLabel();
        lblIconoUsuario = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jpInicio = new javax.swing.JPanel();
        lblIconoInicio = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblTituloInicio = new javax.swing.JLabel();
        lblBienvenida = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jcCalendarioInicio = new com.toedter.calendar.JCalendar();

        jdMostrarProductos.setBackground(new java.awt.Color(255, 255, 255));

        jcbProductos.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProductosActionPerformed(evt);
            }
        });

        jspCantProd.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jspCantProd.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        jbtnAgregarProdReserva.setBackground(new java.awt.Color(0, 102, 204));
        jbtnAgregarProdReserva.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnAgregarProdReserva.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAgregarProdReserva.setText("Agregar");
        jbtnAgregarProdReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarProdReservaActionPerformed(evt);
            }
        });

        jbtnCancelarProdReserva.setBackground(new java.awt.Color(153, 0, 51));
        jbtnCancelarProdReserva.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnCancelarProdReserva.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCancelarProdReserva.setText("Cancelar");
        jbtnCancelarProdReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarProdReservaActionPerformed(evt);
            }
        });

        seleccionarProd.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        seleccionarProd.setText("Seleccionar producto");

        lblSeleccionarCant.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblSeleccionarCant.setText("Seleccionar cantidad");

        lblMarcaProd.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblMarcaProd.setText("Marca");

        lblMarca.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N

        javax.swing.GroupLayout jdMostrarProductosLayout = new javax.swing.GroupLayout(jdMostrarProductos.getContentPane());
        jdMostrarProductos.getContentPane().setLayout(jdMostrarProductosLayout);
        jdMostrarProductosLayout.setHorizontalGroup(
            jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdMostrarProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jdMostrarProductosLayout.createSequentialGroup()
                        .addGroup(jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarProd)
                            .addComponent(lblSeleccionarCant))
                        .addGap(18, 18, 18)
                        .addGroup(jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jdMostrarProductosLayout.createSequentialGroup()
                                .addComponent(jbtnAgregarProdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jbtnCancelarProdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jspCantProd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jdMostrarProductosLayout.createSequentialGroup()
                        .addComponent(lblMarcaProd)
                        .addGap(18, 18, 18)
                        .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 82, Short.MAX_VALUE))
        );
        jdMostrarProductosLayout.setVerticalGroup(
            jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdMostrarProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seleccionarProd)
                    .addComponent(jcbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarcaProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSeleccionarCant)
                    .addComponent(jspCantProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdMostrarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAgregarProdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCancelarProdReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnSalir.setBackground(new java.awt.Color(255, 51, 51));
        jbtnSalir.setFont(new java.awt.Font("Microsoft JhengHei Light", 0, 18)); // NOI18N
        jbtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 650, 99, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 1360, 10));

        jpBotoneraNavegacion.setBackground(new java.awt.Color(102, 102, 102));

        jbtnClientes.setBackground(new java.awt.Color(0, 0, 0));
        jbtnClientes.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 24)); // NOI18N
        jbtnClientes.setForeground(new java.awt.Color(255, 255, 255));
        jbtnClientes.setText("Clientes");
        jbtnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClientesActionPerformed(evt);
            }
        });

        jtbnProductos.setBackground(new java.awt.Color(0, 0, 0));
        jtbnProductos.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 24)); // NOI18N
        jtbnProductos.setForeground(new java.awt.Color(255, 255, 255));
        jtbnProductos.setText("Productos");
        jtbnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnProductosActionPerformed(evt);
            }
        });

        jbtnReservas.setBackground(new java.awt.Color(0, 0, 0));
        jbtnReservas.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 24)); // NOI18N
        jbtnReservas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnReservas.setText("Reservas");
        jbtnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReservasActionPerformed(evt);
            }
        });

        jbtnHabitaciones.setBackground(new java.awt.Color(0, 0, 0));
        jbtnHabitaciones.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 24)); // NOI18N
        jbtnHabitaciones.setForeground(new java.awt.Color(255, 255, 255));
        jbtnHabitaciones.setText("Habitaciones");
        jbtnHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHabitacionesActionPerformed(evt);
            }
        });

        jbtnInicio.setBackground(new java.awt.Color(0, 0, 0));
        jbtnInicio.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 24)); // NOI18N
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
        lblTituloHabitaciones.setFont(new java.awt.Font("OCR A Extended", 1, 26)); // NOI18N
        lblTituloHabitaciones.setText("Habitaciones");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jtbHabitaciones.setFont(new java.awt.Font("Arial Narrow", 0, 20)); // NOI18N
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
        jtbHabitaciones.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtbHabitaciones.setRowHeight(30);
        jtbHabitaciones.setSelectionBackground(new java.awt.Color(102, 204, 255));
        jtbHabitaciones.setSelectionForeground(new java.awt.Color(102, 102, 102));
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

        lblDisponibles.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblDisponibles.setText("Disponibles:");

        lblOcupadas.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblOcupadas.setText("Ocupadas: ");

        lblRegistradas.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblRegistradas.setText("Registradas:");

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel1.setText("Filtrar por ");

        jcbFiltroHabitaciones.setBackground(new java.awt.Color(102, 204, 255));
        jcbFiltroHabitaciones.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jcbFiltroHabitaciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Numero", "Tipo", "Estado", "Precio" }));
        jcbFiltroHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroHabitacionesActionPerformed(evt);
            }
        });

        jtbnCrearHabitacion.setBackground(new java.awt.Color(0, 102, 153));
        jtbnCrearHabitacion.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnCrearHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        jtbnCrearHabitacion.setText("Crear habitación");
        jtbnCrearHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCrearHabitacionActionPerformed(evt);
            }
        });

        lblBuscar.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
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

        jtbnModificarHabitacion.setBackground(new java.awt.Color(153, 153, 0));
        jtbnModificarHabitacion.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnModificarHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        jtbnModificarHabitacion.setText("Actualizar habitación");
        jtbnModificarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnModificarHabitacionActionPerformed(evt);
            }
        });

        jtbnEliminarHabitacion.setBackground(new java.awt.Color(153, 0, 51));
        jtbnEliminarHabitacion.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnEliminarHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        jtbnEliminarHabitacion.setText("Eliminar habitación");
        jtbnEliminarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnEliminarHabitacionActionPerformed(evt);
            }
        });

        jbtnActualizarTablaHabitaciones.setBackground(new java.awt.Color(0, 0, 0));
        jbtnActualizarTablaHabitaciones.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnActualizarTablaHabitaciones.setForeground(new java.awt.Color(255, 255, 255));
        jbtnActualizarTablaHabitaciones.setText("Actualizar tabla");
        jbtnActualizarTablaHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaHabitacionesActionPerformed(evt);
            }
        });

        lblLimpieza.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblLimpieza.setText("Limpieza:");

        lblReparación.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblReparación.setText("Reparación:");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        lblIconoHabitaciones.setBackground(new java.awt.Color(255, 255, 255));

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
                                .addComponent(lblTituloHabitaciones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblIconoHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTituloHabitaciones))
                    .addComponent(lblIconoHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
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

        lblClientes.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblClientes.setText("Clientes:");

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel3.setText("Filtrar por ");

        jcbFiltroClientes.setBackground(new java.awt.Color(102, 204, 255));
        jcbFiltroClientes.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jcbFiltroClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre", "Apellido", "DNI", "Fecha nacimiento", "País", "Correo", "Teléfono" }));
        jcbFiltroClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroClientesActionPerformed(evt);
            }
        });

        jbtnModificarCliente.setBackground(new java.awt.Color(153, 153, 0));
        jbtnModificarCliente.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnModificarCliente.setForeground(new java.awt.Color(255, 255, 255));
        jbtnModificarCliente.setText("Actualizar cliente");
        jbtnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarClienteActionPerformed(evt);
            }
        });

        lblBuscarCliente.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        lblBuscarCliente.setText("Buscar");

        lblTituloClientes.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloClientes.setFont(new java.awt.Font("OCR A Extended", 1, 26)); // NOI18N
        lblTituloClientes.setText("Clientes");

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

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));

        jbtnImprimirListaCliente.setBackground(new java.awt.Color(153, 204, 255));
        jbtnImprimirListaCliente.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnImprimirListaCliente.setForeground(new java.awt.Color(255, 255, 255));
        jbtnImprimirListaCliente.setText("Imprimir lista");
        jbtnImprimirListaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnImprimirListaClienteActionPerformed(evt);
            }
        });

        jtbClientes.setFont(new java.awt.Font("Arial Narrow", 0, 20)); // NOI18N
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
        jtbClientes.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtbClientes.setRowHeight(30);
        jtbClientes.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbClientes.setSelectionForeground(new java.awt.Color(102, 102, 102));
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

        jbtnActualizarTablaCliente.setBackground(new java.awt.Color(0, 0, 0));
        jbtnActualizarTablaCliente.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnActualizarTablaCliente.setForeground(new java.awt.Color(255, 255, 255));
        jbtnActualizarTablaCliente.setText("Actualizar tabla");
        jbtnActualizarTablaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaClienteActionPerformed(evt);
            }
        });

        lblIconoClientes.setBackground(new java.awt.Color(255, 255, 255));
        lblIconoClientes.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpClientesLayout = new javax.swing.GroupLayout(jpClientes);
        jpClientes.setLayout(jpClientesLayout);
        jpClientesLayout.setHorizontalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClientesLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jcbFiltroClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBuscarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
            .addGroup(jpClientesLayout.createSequentialGroup()
                .addGroup(jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClientesLayout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(lblTituloClientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIconoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpClientesLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpClientesLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpClientesLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblClientes))
                    .addGroup(jpClientesLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jspClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jbtnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpClientesLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jbtnActualizarTablaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(490, 490, 490)
                        .addComponent(jbtnImprimirListaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpClientesLayout.setVerticalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClientesLayout.createSequentialGroup()
                .addGroup(jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClientesLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblTituloClientes))
                    .addComponent(lblIconoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBuscarCliente))
                    .addGroup(jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbFiltroClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblClientes)
                .addGap(12, 12, 12)
                .addGroup(jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnActualizarTablaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnImprimirListaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jcbFiltroClientes.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jpClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 1185, 510));

        jpReservas.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloReservas.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloReservas.setFont(new java.awt.Font("OCR A Extended", 1, 26)); // NOI18N
        lblTituloReservas.setText("Reservas");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jcbFiltroReservas.setBackground(new java.awt.Color(102, 204, 255));
        jcbFiltroReservas.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jcbFiltroReservas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre", "Apellido", "DNI", "Nro. habitación", "Fecha entrada", "Fecha salida", "Hora entrada", "Hora salida", "Estado", "Precio total" }));
        jcbFiltroReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroReservasActionPerformed(evt);
            }
        });

        jtbReservas.setFont(new java.awt.Font("Arial Narrow", 0, 20)); // NOI18N
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
        jtbReservas.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtbReservas.setRowHeight(30);
        jtbReservas.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbReservas.setSelectionForeground(new java.awt.Color(102, 102, 102));
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

        jtbnCrearReserva.setBackground(new java.awt.Color(0, 102, 153));
        jtbnCrearReserva.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnCrearReserva.setForeground(new java.awt.Color(255, 255, 255));
        jtbnCrearReserva.setText("Crear reserva");
        jtbnCrearReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCrearReservaActionPerformed(evt);
            }
        });

        jtbnModificarReserva.setBackground(new java.awt.Color(153, 153, 0));
        jtbnModificarReserva.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnModificarReserva.setForeground(new java.awt.Color(255, 255, 255));
        jtbnModificarReserva.setText("Actualizar reserva");
        jtbnModificarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnModificarReservaActionPerformed(evt);
            }
        });

        jbtnActualizarTablaReservas.setBackground(new java.awt.Color(0, 0, 0));
        jbtnActualizarTablaReservas.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnActualizarTablaReservas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnActualizarTablaReservas.setText("Actualizar tabla");
        jbtnActualizarTablaReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaReservasActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel2.setText("Filtrar por ");

        jtbnVerDetalles.setBackground(new java.awt.Color(102, 153, 0));
        jtbnVerDetalles.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnVerDetalles.setForeground(new java.awt.Color(255, 255, 255));
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

        lblBuscar1.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        lblBuscar1.setText("Buscar");

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jtbnCobrar.setBackground(new java.awt.Color(255, 255, 255));
        jtbnCobrar.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnCobrar.setText("Cobrar");
        jtbnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCobrarActionPerformed(evt);
            }
        });

        lblCantReservas.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblCantReservas.setText("Reservas:");

        lblCobradasReserva.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblCobradasReserva.setText("Cobradas:");

        lblPendientesReserva.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblPendientesReserva.setText("Pendientes:");

        jbtnVenderProducto.setBackground(new java.awt.Color(0, 102, 102));
        jbtnVenderProducto.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnVenderProducto.setForeground(new java.awt.Color(255, 255, 255));
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
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jcbFiltroReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBuscar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBuscarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(jpReservasLayout.createSequentialGroup()
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(lblTituloReservas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIconoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblCantReservas)
                        .addGap(69, 69, 69)
                        .addComponent(lblCobradasReserva)
                        .addGap(69, 69, 69)
                        .addComponent(lblPendientesReserva))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jspReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnVenderProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpReservasLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtbnCrearReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtbnModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jbtnActualizarTablaReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(236, 236, 236)
                        .addComponent(jtbnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(236, 236, 236)
                        .addComponent(jtbnVerDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpReservasLayout.setVerticalGroup(
            jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReservasLayout.createSequentialGroup()
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblTituloReservas))
                    .addComponent(lblIconoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbFiltroReservas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfBuscarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar1)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantReservas)
                    .addComponent(lblCobradasReserva)
                    .addComponent(lblPendientesReserva))
                .addGap(18, 18, 18)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jtbnCrearReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnVenderProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnActualizarTablaReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnVerDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jpReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 1185, 510));

        jpProductos.setBackground(new java.awt.Color(255, 255, 255));

        lblCantProd.setFont(new java.awt.Font("OCR A Extended", 1, 14)); // NOI18N
        lblCantProd.setText("Productos:");

        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        lblFiltrarPor.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        lblFiltrarPor.setText("Filtrar por ");

        jcbFiltroProductos.setBackground(new java.awt.Color(102, 204, 255));
        jcbFiltroProductos.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jcbFiltroProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre", "Marca", "Categoria", "Stock", "Proveedor", "Precio de compra", "Precio de venta" }));
        jcbFiltroProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroProductosActionPerformed(evt);
            }
        });

        jbtnAgregarProd.setBackground(new java.awt.Color(0, 102, 153));
        jbtnAgregarProd.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnAgregarProd.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAgregarProd.setText("Agregar producto");
        jbtnAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarProdActionPerformed(evt);
            }
        });

        lblBuscarProd.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        lblBuscarProd.setText("Buscar");

        lblProductos.setBackground(new java.awt.Color(255, 255, 255));
        lblProductos.setFont(new java.awt.Font("OCR A Extended", 1, 26)); // NOI18N
        lblProductos.setText("Productos");

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

        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));

        jtbProductos.setFont(new java.awt.Font("Arial Narrow", 0, 20)); // NOI18N
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
        jtbProductos.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtbProductos.setRowHeight(30);
        jtbProductos.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jtbProductos.setSelectionForeground(new java.awt.Color(102, 102, 102));
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

        jbtnActualizarTablaProductos.setBackground(new java.awt.Color(0, 0, 0));
        jbtnActualizarTablaProductos.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnActualizarTablaProductos.setForeground(new java.awt.Color(255, 255, 255));
        jbtnActualizarTablaProductos.setText("Actualizar tabla");
        jbtnActualizarTablaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaProductosActionPerformed(evt);
            }
        });

        jbtnModificarProducto.setBackground(new java.awt.Color(153, 153, 0));
        jbtnModificarProducto.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnModificarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jbtnModificarProducto.setText("Actualizar producto");
        jbtnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnModificarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpProductosLayout = new javax.swing.GroupLayout(jpProductos);
        jpProductos.setLayout(jpProductosLayout);
        jpProductosLayout.setHorizontalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProductosLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(lblFiltrarPor)
                .addGap(4, 4, 4)
                .addComponent(jcbFiltroProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBuscarProd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
            .addGroup(jpProductosLayout.createSequentialGroup()
                .addGroup(jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(lblProductos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIconoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblCantProd))
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jspProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnAgregarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnModificarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jbtnActualizarTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpProductosLayout.setVerticalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProductosLayout.createSequentialGroup()
                .addGroup(jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblProductos))
                    .addComponent(lblIconoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbFiltroProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfBuscarProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscarProd)
                    .addComponent(lblFiltrarPor))
                .addGap(24, 24, 24)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblCantProd)
                .addGap(12, 12, 12)
                .addGroup(jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpProductosLayout.createSequentialGroup()
                        .addComponent(jbtnAgregarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jbtnModificarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jbtnActualizarTablaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        lblIconoHotelTitulo.setText("jLabel4");

        lblIconoUsuario.setText("jLabel4");

        jSeparator12.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(581, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconoHotelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaHoraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuarioLogueado, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIconoHotelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator12)
                    .addGroup(jpTituloLayout.createSequentialGroup()
                        .addComponent(lblUsuarioLogueado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFechaHoraTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpTituloLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblTituloPrincipal))
                    .addComponent(lblIconoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 1360, 120));

        jpInicio.setBackground(new java.awt.Color(255, 255, 255));
        jpInicio.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        lblTituloInicio.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloInicio.setFont(new java.awt.Font("OCR A Extended", 1, 26)); // NOI18N
        lblTituloInicio.setText("Inicio");
        lblTituloInicio.setPreferredSize(new java.awt.Dimension(133, 30));

        lblBienvenida.setBackground(new java.awt.Color(255, 255, 255));
        lblBienvenida.setFont(new java.awt.Font("Maiandra GD", 3, 24)); // NOI18N
        lblBienvenida.setForeground(new java.awt.Color(51, 153, 255));
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
            .addGroup(jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpInicioLayout.createSequentialGroup()
                    .addGap(599, 599, 599)
                    .addComponent(lblIconoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(474, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jcCalendarioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpInicioLayout.createSequentialGroup()
                    .addComponent(lblIconoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 471, Short.MAX_VALUE)))
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
        int filaIndice = jtbReservas.getSelectedRow();
        String estado = dtmReservas.getValueAt(filaIndice, 9).toString();

        if (estado.equals("Cobrado")) {
            activarBotonesPanelReservas(false, false, true, false);
        } else {
            activarBotonesPanelReservas(true, true, true, true);
        }
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
        int filaIndice = jtbReservas.getSelectedRow();
        Long reservaId = Long.parseLong(dtmReservas.getValueAt(filaIndice, 0).toString());
        Reserva reserva = negocioReserva.encontrarReserva(reservaId);

        try {
            int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo("¿Estás seguro que deseas cobrar la reserva con id " + reserva.getId() + "?", "Confirmar cobro");
            if (confirmado == JOptionPane.YES_OPTION) {
                negocioReserva.cobrarReserva(reserva);
                UtilidadJOptionPane.mostrarMensajeInformacion("¡Reserva cobrada exitosamente!", "Reserva cobrada");
                actualizarTablaReservas();
                mostrarCantEstadosReservas();
                jcbFiltroReservas.setSelectedItem("Id");
            }
        } catch (Exception ex) {
            System.out.println("Error al cobrar la reserva " + ex.getMessage());
        }
    }//GEN-LAST:event_jtbnCobrarActionPerformed

    private void jbtnVenderProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnVenderProductoActionPerformed
        reiniciarJDialogProd();
        jdMostrarProductos.setVisible(true);
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
        actualizarTablaClientes();
        mostrarCantClientes();
        jcbFiltroClientes.setSelectedItem("Id");
    }//GEN-LAST:event_jbtnActualizarTablaClienteActionPerformed

    private void jcbFiltroProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFiltroProductosActionPerformed
        String campo = (String) jcbFiltroProductos.getSelectedItem();

        switch (campo) {
            case "Precio de compra":
                campo = "precioCompra";
                break;
            case "Precio de venta":
                campo = "precioVenta";
                break;
        }
        productos = negocioProducto.ordenarProductos(campo);
        mostrarDatosProd(productos);
        activarBotonesPanelProd(false);
    }//GEN-LAST:event_jcbFiltroProductosActionPerformed

    private void jbtnAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarProdActionPerformed
        frmProducto frmProducto = new frmProducto();
        frmProducto.setVisible(true);
    }//GEN-LAST:event_jbtnAgregarProdActionPerformed

    private void jtfBuscarProdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfBuscarProdFocusGained
        if (!jtfBuscarProd.getText().equals("")) {
            mostrarBusquedaProd(jtfBuscarProd.getText(), -1);
        }
    }//GEN-LAST:event_jtfBuscarProdFocusGained

    private void jtfBuscarProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarProdKeyReleased
        mostrarBusquedaProd(jtfBuscarProd.getText(), evt.getKeyCode());
    }//GEN-LAST:event_jtfBuscarProdKeyReleased

    private void jtbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbProductosMouseClicked
        activarBotonesPanelProd(true);
    }//GEN-LAST:event_jtbProductosMouseClicked

    private void jbtnActualizarTablaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarTablaProductosActionPerformed
        actualizarTablaProd();
        mostrarCantProd();
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

    private void jbtnCancelarProdReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarProdReservaActionPerformed
        jdMostrarProductos.dispose();
    }//GEN-LAST:event_jbtnCancelarProdReservaActionPerformed

    private void jbtnAgregarProdReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarProdReservaActionPerformed
        boolean superaStock = false;
        Producto productoSeleccionado = null;
        Reserva reservaSeleccionada = null;

        String producto = (String) jcbProductos.getSelectedItem();
        int cantProd = (int) jspCantProd.getValue();

        int filaIndice = jtbReservas.getSelectedRow();
        Long reservaId = Long.parseLong(dtmReservas.getValueAt(filaIndice, 0).toString());

        int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo("¿Estás seguro que deseas agregar este producto a la reserva " + reservaId + "?", "Confirmar producto");

        if (confirmado == JOptionPane.YES_OPTION) {
            for (Producto prod : productosDisp) {
                if (producto.equals(prod.getNombre())) {
                    if (cantProd > prod.getStock()) {
                        superaStock = true;
                        UtilidadJOptionPane.mostrarMensajeError("La cantidad ingresada supera al stock del producto seleccionado (" + prod.getStock() + ").", "Error");
                    } else {
                        productoSeleccionado = prod;
                        reservaSeleccionada = negocioReserva.encontrarReserva(reservaId);
                    }
                }
            }
            if (!superaStock && productoSeleccionado != null && reservaSeleccionada != null) {
                int indiceProd, cantProdExistente;
                float precioTotalProd, nuevoPrecioTotalProd;

                // Se verifica si ya existe el producto seleccionado para la reserva
                // Si existe, no se duplica sino que se suma la cantidad al producto existente
                // Se calcula el nuevo precio total del producto
                ReservaProducto reservaProd = negocioReserva.verificarExistenciaProdReserva(reservaSeleccionada.getProductos(), productoSeleccionado.getId());
                precioTotalProd = negocioReserva.calcularPrecioTotalXProducto(cantProd, productoSeleccionado.getPrecioVenta());

                if (reservaProd != null) {
                    indiceProd = reservaSeleccionada.getProductos().indexOf(reservaProd);
                    cantProdExistente = reservaSeleccionada.getProductos().get(indiceProd).getCantProducto();
                    reservaSeleccionada.getProductos().get(indiceProd).setCantProducto(cantProdExistente + cantProd);
                    nuevoPrecioTotalProd = negocioReserva.calcularPrecioTotalXProducto((cantProdExistente + cantProd), productoSeleccionado.getPrecioVenta());
                    reservaSeleccionada.getProductos().get(indiceProd).setPrecioTotal(nuevoPrecioTotalProd);
                    reservaSeleccionada.setPrecioTotal(reservaSeleccionada.getPrecioTotal() + precioTotalProd);
                } else {
                    reservaSeleccionada.addProducto(productoSeleccionado, cantProd, precioTotalProd);
                    reservaSeleccionada.setPrecioTotal(reservaSeleccionada.getPrecioTotal() + precioTotalProd);
                }
                try {
                    productoSeleccionado.setStock(productoSeleccionado.getStock() - cantProd);
                    negocioProducto.actualizarStock(productoSeleccionado);
                    negocioReserva.modificarReserva(reservaSeleccionada);
                    UtilidadJOptionPane.mostrarMensajeInformacion("El producto ha sido agregado a la reserva. Para visualizarlo ir a los detalles de la misma.", "Producto agregado");
                    actualizarTablaReservas();
                    jdMostrarProductos.dispose();
                } catch (Exception ex) {
                    System.out.println("Error al agregar el producto. " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jbtnAgregarProdReservaActionPerformed

    private void jcbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbProductosActionPerformed
        String producto = (String) jcbProductos.getSelectedItem();
        if (producto != null) {
            mostrarMarca(producto);
        }
    }//GEN-LAST:event_jcbProductosActionPerformed

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
    private javax.swing.JSeparator jSeparator12;
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
    private javax.swing.JButton jbtnAgregarProdReserva;
    private javax.swing.JButton jbtnCancelarProdReserva;
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
    private javax.swing.JComboBox jcbProductos;
    private javax.swing.JDialog jdMostrarProductos;
    private javax.swing.JPanel jpBotoneraNavegacion;
    private javax.swing.JPanel jpClientes;
    private javax.swing.JPanel jpHabitaciones;
    private javax.swing.JPanel jpInicio;
    private javax.swing.JPanel jpProductos;
    private javax.swing.JPanel jpReservas;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JSpinner jspCantProd;
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
    private javax.swing.JLabel lblCantReservas;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblCobradasReserva;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblFechaHoraTitulo;
    private javax.swing.JLabel lblFiltrarPor;
    private javax.swing.JLabel lblIconoClientes;
    private javax.swing.JLabel lblIconoHabitaciones;
    private javax.swing.JLabel lblIconoHotelTitulo;
    private javax.swing.JLabel lblIconoInicio;
    private javax.swing.JLabel lblIconoProductos;
    private javax.swing.JLabel lblIconoReserva;
    private javax.swing.JLabel lblIconoUsuario;
    private javax.swing.JLabel lblLimpieza;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMarcaProd;
    private javax.swing.JLabel lblOcupadas;
    private javax.swing.JLabel lblPendientesReserva;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblRegistradas;
    private javax.swing.JLabel lblReparación;
    private javax.swing.JLabel lblSeleccionarCant;
    private javax.swing.JLabel lblTituloClientes;
    private javax.swing.JLabel lblTituloHabitaciones;
    private javax.swing.JLabel lblTituloInicio;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JLabel lblTituloReservas;
    private javax.swing.JLabel lblUsuarioLogueado;
    private javax.swing.JLabel seleccionarProd;
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
