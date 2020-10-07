package Presentación;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.UtilidadJFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Negocio.NegocioHabitacion;
import Negocio.NegocioReserva;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJTable;
import javax.swing.JOptionPane;

public class frmPrincipal extends javax.swing.JFrame implements Runnable {

    UtilidadJFrame utilidadJframe;
    UtilidadJTable utilidadJtable;

    DefaultTableModel dtmHabitaciones;
    List<Habitacion> habitaciones;
    NegocioHabitacion negocioHabitacion;

    DefaultTableModel dtmReservas;
    List<Reserva> reservas;
    NegocioReserva negocioReserva;

    Thread hilo;

    public frmPrincipal() {

        initComponents();

        hilo = new Thread(this);
        hilo.start();

        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Gestión hotelera", this);

        utilidadJframe.guardarPanelesPrincipal(jpHabitaciones, jpClientes, jpReservas, jpProductos, jpInicio);

        utilidadJframe.activarPanelPrincipal(true, false, false, false, false);

        setUsuariologueado();

        dtmHabitaciones = (DefaultTableModel) jtbHabitaciones.getModel();
        dtmReservas = (DefaultTableModel) jtbReservas.getModel();

        habitaciones = new ArrayList<>();
        negocioHabitacion = new NegocioHabitacion();

        utilidadJtable = new UtilidadJTable();

        reservas = new ArrayList<>();
        negocioReserva = new NegocioReserva();

        utilidadJtable.centrarElementosTable(jtbHabitaciones);
        utilidadJtable.centrarElementosTable(jtbReservas);

        activarPanelInicio();
    }

    private void activarPanelHabitaciones() {

        utilidadJframe.activarPanelPrincipal(true, false, false, false, false);
        activarBotonesPanelHabitaciones(false, false);
        agregarDatosTablaHabitaciones();
        contabilizarEstadosHabitacion();
    }

    private void activarPanelInicio() {
        try {

            utilidadJframe.activarPanelPrincipal(false, false, false, false, true);
            //String nombreUsuario = sesionUsuario.getSesionUsuario().getUsuario().getUsuario();
            String nombreUsuario = "temporal";
            lblBienvenida.setText("¡Bienvenido usuario " + nombreUsuario + "!");
            setFechaHoraInicio();

        } catch (Exception e) {
            System.err.println("Error, el usuario todavía no ha iniciado sesión.");
            System.exit(0);
        }
    }

    private void setFechaHoraInicio() {
        lblFechaHora.setText(UtilidadGeneral.getFechaActual() + ", " + UtilidadGeneral.getHoraActual());
        lblFechaHoraTitulo.setText(UtilidadGeneral.getFechaActual() + ", " + UtilidadGeneral.getHoraActual());
    }

    private void setUsuariologueado() {

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

    private void agregarDatosTablaHabitaciones() {
        negocioHabitacion.agregarDatosTablaHabitaciones(habitaciones, negocioHabitacion, dtmHabitaciones);
    }

    private void contabilizarEstadosHabitacion() {
        negocioHabitacion.contabilizarEstadosHabitaciones(lblRegistradas, lblDisponibles, lblOcupadas, lblLimpieza, lblReparación);
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
        jbtnActualizarTabla = new javax.swing.JButton();
        lblLimpieza = new javax.swing.JLabel();
        lblReparación = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jpClientes = new javax.swing.JPanel();
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
        jtfBuscarHabitacion1 = new javax.swing.JTextField();
        lblBuscar1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jtbnCobrar = new javax.swing.JButton();
        lblOcupadas1 = new javax.swing.JLabel();
        lblOcupadas2 = new javax.swing.JLabel();
        lblOcupadas3 = new javax.swing.JLabel();
        jpProductos = new javax.swing.JPanel();
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

        jbtnClientes.setBackground(new java.awt.Color(255, 255, 255));
        jbtnClientes.setFont(new java.awt.Font("Maiandra GD", 3, 18)); // NOI18N
        jbtnClientes.setText("Clientes");
        jbtnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClientesActionPerformed(evt);
            }
        });

        jtbnProductos.setBackground(new java.awt.Color(255, 255, 255));
        jtbnProductos.setFont(new java.awt.Font("Maiandra GD", 3, 18)); // NOI18N
        jtbnProductos.setText("Productos");
        jtbnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnProductosActionPerformed(evt);
            }
        });

        jbtnReservas.setBackground(new java.awt.Color(255, 255, 255));
        jbtnReservas.setFont(new java.awt.Font("Maiandra GD", 3, 18)); // NOI18N
        jbtnReservas.setText("Reservas");
        jbtnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReservasActionPerformed(evt);
            }
        });

        jbtnHabitaciones.setBackground(new java.awt.Color(255, 255, 255));
        jbtnHabitaciones.setFont(new java.awt.Font("Maiandra GD", 3, 18)); // NOI18N
        jbtnHabitaciones.setText("Habitaciones");
        jbtnHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHabitacionesActionPerformed(evt);
            }
        });

        jbtnInicio.setBackground(new java.awt.Color(255, 255, 255));
        jbtnInicio.setFont(new java.awt.Font("Maiandra GD", 3, 18)); // NOI18N
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
                    .addComponent(jtbnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            jtbHabitaciones.getColumnModel().getColumn(0).setResizable(false);
            jtbHabitaciones.getColumnModel().getColumn(0).setPreferredWidth(1);
            jtbHabitaciones.getColumnModel().getColumn(1).setResizable(false);
            jtbHabitaciones.getColumnModel().getColumn(1).setPreferredWidth(1);
            jtbHabitaciones.getColumnModel().getColumn(2).setResizable(false);
            jtbHabitaciones.getColumnModel().getColumn(2).setPreferredWidth(10);
            jtbHabitaciones.getColumnModel().getColumn(3).setResizable(false);
            jtbHabitaciones.getColumnModel().getColumn(3).setPreferredWidth(5);
            jtbHabitaciones.getColumnModel().getColumn(4).setResizable(false);
            jtbHabitaciones.getColumnModel().getColumn(5).setResizable(false);
            jtbHabitaciones.getColumnModel().getColumn(5).setPreferredWidth(1);
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

        jbtnActualizarTabla.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnActualizarTabla.setText("Actualizar tabla");
        jbtnActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarTablaActionPerformed(evt);
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
                                    .addComponent(jbtnActualizarTabla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtbnCrearHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtbnModificarHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
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
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                .addGap(590, 590, 590)
                                .addComponent(lblBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfBuscarHabitacion)
                                .addGap(192, 192, 192))))
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                .addGap(461, 461, 461)
                                .addComponent(lblTituloHabitaciones))
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbFiltroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
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
                        .addComponent(jLabel1)
                        .addComponent(jcbFiltroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBuscar)
                        .addComponent(jtfBuscarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jbtnActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jpHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 1185, 510));

        jpClientes.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jpClientesLayout = new javax.swing.GroupLayout(jpClientes);
        jpClientes.setLayout(jpClientesLayout);
        jpClientesLayout.setHorizontalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jpClientesLayout.setVerticalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        getContentPane().add(jpClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 123, -1, -1));

        jpReservas.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloReservas.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloReservas.setFont(new java.awt.Font("Maiandra GD", 0, 24)); // NOI18N
        lblTituloReservas.setText("Reservas");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jcbFiltroReservas.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbFiltroReservas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre", "Apellido", "Nro. habitación", "Estado", "Fecha entrada", "Fecha salida", "Precio total" }));
        jcbFiltroReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFiltroReservasActionPerformed(evt);
            }
        });

        jtbReservas.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellido", "Nro. habitación", "Fecha entrada", "Fecha salida", "Hora entrada", "Hora salida", "Estado", "Precio total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
            jtbReservas.getColumnModel().getColumn(0).setResizable(false);
            jtbReservas.getColumnModel().getColumn(0).setPreferredWidth(1);
            jtbReservas.getColumnModel().getColumn(1).setResizable(false);
            jtbReservas.getColumnModel().getColumn(2).setResizable(false);
            jtbReservas.getColumnModel().getColumn(3).setResizable(false);
            jtbReservas.getColumnModel().getColumn(4).setResizable(false);
            jtbReservas.getColumnModel().getColumn(5).setResizable(false);
            jtbReservas.getColumnModel().getColumn(6).setResizable(false);
            jtbReservas.getColumnModel().getColumn(7).setResizable(false);
            jtbReservas.getColumnModel().getColumn(8).setResizable(false);
            jtbReservas.getColumnModel().getColumn(9).setResizable(false);
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

        lblOcupadas1.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblOcupadas1.setText("Ocupadas: ");

        lblOcupadas2.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblOcupadas2.setText("Cobradas:");

        lblOcupadas3.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblOcupadas3.setText("Pendientes:");

        javax.swing.GroupLayout jpReservasLayout = new javax.swing.GroupLayout(jpReservas);
        jpReservas.setLayout(jpReservasLayout);
        jpReservasLayout.setHorizontalGroup(
            jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpReservasLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpReservasLayout.createSequentialGroup()
                        .addComponent(lblOcupadas1)
                        .addGap(56, 56, 56)
                        .addComponent(lblOcupadas2)
                        .addGap(56, 56, 56)
                        .addComponent(lblOcupadas3)
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
                                .addComponent(jtfBuscarHabitacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jspReservas, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(19, 19, 19)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtbnModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnCrearReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jtfBuscarHabitacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblOcupadas1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpReservasLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblOcupadas3))))
                    .addComponent(lblOcupadas2))
                .addGap(18, 18, 18)
                .addGroup(jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpReservasLayout.createSequentialGroup()
                        .addComponent(jtbnCrearReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnModificarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))
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

        javax.swing.GroupLayout jpProductosLayout = new javax.swing.GroupLayout(jpProductos);
        jpProductos.setLayout(jpProductosLayout);
        jpProductosLayout.setHorizontalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jpProductosLayout.setVerticalGroup(
            jpProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jpProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 892, -1, -1));

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
        utilidadJframe.activarPanelPrincipal(false, true, false, false, false);
    }//GEN-LAST:event_jbtnClientesActionPerformed

    private void jbtnHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHabitacionesActionPerformed

        activarPanelHabitaciones();
    }//GEN-LAST:event_jbtnHabitacionesActionPerformed

    private void jbtnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReservasActionPerformed

        utilidadJframe.activarPanelPrincipal(false, false, true, false, false);

    }//GEN-LAST:event_jbtnReservasActionPerformed

    private void jtbnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnProductosActionPerformed

        utilidadJframe.activarPanelPrincipal(false, false, false, true, false);

    }//GEN-LAST:event_jtbnProductosActionPerformed

    private void jtbnCrearHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCrearHabitacionActionPerformed

        frmHabitacion jfrHabitacion = new frmHabitacion();

        jfrHabitacion.setVisible(true);

    }//GEN-LAST:event_jtbnCrearHabitacionActionPerformed

    private void jbtnActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarTablaActionPerformed

        activarBotonesPanelHabitaciones(false, false);
        negocioHabitacion.agregarDatosTablaHabitaciones(habitaciones, negocioHabitacion, dtmHabitaciones);
        contabilizarEstadosHabitacion();

    }//GEN-LAST:event_jbtnActualizarTablaActionPerformed

    private void jtbHabitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbHabitacionesMouseClicked

        activarBotonesPanelHabitaciones(true, true);
    }//GEN-LAST:event_jtbHabitacionesMouseClicked

    private void jtbnModificarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnModificarHabitacionActionPerformed

        frmHabitacion habitacion = new frmHabitacion();

        int filaIndice = jtbHabitaciones.getSelectedRow();

        habitaciones = negocioHabitacion.getHabitaciones();

        Long habitacionId = Long.parseLong(dtmHabitaciones.getValueAt(filaIndice, 0).toString());
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getId().equals(habitacionId)) {
                habitacion.setHabitacion(habitaciones.get(i));
            }
        }

        habitacion.setVisible(true);
    }//GEN-LAST:event_jtbnModificarHabitacionActionPerformed

    private void jtbnEliminarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnEliminarHabitacionActionPerformed

        int filaIndice = jtbHabitaciones.getSelectedRow();
        Long habitacionId = (Long) dtmHabitaciones.getValueAt(filaIndice, 0);

        int confirmado = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas eliminar la habitación con id " + habitacionId + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmado == JOptionPane.YES_OPTION) {
            try {
                negocioHabitacion.eliminarHabitacion(habitacionId);
                JOptionPane.showConfirmDialog(null, "Habitación eliminada exitosamente.", "Habitacion eliminada", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                agregarDatosTablaHabitaciones();

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
        agregarDatosTablaHabitaciones();
    }//GEN-LAST:event_jcbFiltroHabitacionesActionPerformed

    private void jbtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInicioActionPerformed

        activarPanelInicio();
    }//GEN-LAST:event_jbtnInicioActionPerformed

    private void jcbFiltroReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFiltroReservasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbFiltroReservasActionPerformed

    private void jtbReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbReservasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbReservasMouseClicked

    private void jtbnCrearReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCrearReservaActionPerformed

        frmReserva frmCrearReserva = new frmReserva();

        frmCrearReserva.setVisible(true);

    }//GEN-LAST:event_jtbnCrearReservaActionPerformed

    private void jtbnModificarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnModificarReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbnModificarReservaActionPerformed

    private void jbtnActualizarTablaReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnActualizarTablaReservasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnActualizarTablaReservasActionPerformed

    private void jtbnVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnVerDetallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbnVerDetallesActionPerformed

    private void jtbnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCobrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbnCobrarActionPerformed

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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton jbtnActualizarTabla;
    private javax.swing.JButton jbtnActualizarTablaReservas;
    private javax.swing.JButton jbtnClientes;
    private javax.swing.JButton jbtnHabitaciones;
    private javax.swing.JButton jbtnInicio;
    private javax.swing.JButton jbtnReservas;
    private javax.swing.JButton jbtnSalir;
    private com.toedter.calendar.JCalendar jcCalendarioInicio;
    private javax.swing.JComboBox jcbFiltroHabitaciones;
    private javax.swing.JComboBox jcbFiltroReservas;
    private javax.swing.JPanel jpBotoneraNavegacion;
    private javax.swing.JPanel jpClientes;
    private javax.swing.JPanel jpHabitaciones;
    private javax.swing.JPanel jpInicio;
    private javax.swing.JPanel jpProductos;
    private javax.swing.JPanel jpReservas;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JScrollPane jspHabitaciones;
    private javax.swing.JScrollPane jspReservas;
    private javax.swing.JTable jtbHabitaciones;
    private javax.swing.JTable jtbReservas;
    private javax.swing.JButton jtbnCobrar;
    private javax.swing.JButton jtbnCrearHabitacion;
    private javax.swing.JButton jtbnCrearReserva;
    private javax.swing.JButton jtbnEliminarHabitacion;
    private javax.swing.JButton jtbnModificarHabitacion;
    private javax.swing.JButton jtbnModificarReserva;
    private javax.swing.JButton jtbnProductos;
    private javax.swing.JButton jtbnVerDetalles;
    private javax.swing.JTextField jtfBuscarHabitacion;
    private javax.swing.JTextField jtfBuscarHabitacion1;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblBuscar1;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblFechaHoraTitulo;
    private javax.swing.JLabel lblLimpieza;
    private javax.swing.JLabel lblOcupadas;
    private javax.swing.JLabel lblOcupadas1;
    private javax.swing.JLabel lblOcupadas2;
    private javax.swing.JLabel lblOcupadas3;
    private javax.swing.JLabel lblRegistradas;
    private javax.swing.JLabel lblReparación;
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
            setFechaHoraInicio();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido.");
            }
        }
    }
}
