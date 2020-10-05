package Presentación;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import Negocio.UtilidadJFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Negocio.NegocioHabitacion;
import Negocio.UtilidadJTable;
import javax.swing.JOptionPane;

public class frmPrincipal extends javax.swing.JFrame {

    UtilidadJFrame utilidadJframe;
    UtilidadJTable utilidadJtable;
    DefaultTableModel dtmHabitaciones;
    List<Habitacion> habitaciones;
    NegocioHabitacion negocioHabitacion;

    public frmPrincipal() {

        initComponents();

        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Gestión hotelera", this);

        utilidadJframe.guardarPanelesPrincipal(jpHabitaciones, jpClientes, jpReservas, jpProductos);

        utilidadJframe.activarPanelPrincipal(true, false, false, false);

        dtmHabitaciones = (DefaultTableModel) jtbHabitaciones.getModel();

        habitaciones = new ArrayList<>();
        negocioHabitacion = new NegocioHabitacion();
        utilidadJtable = new UtilidadJTable();

        utilidadJtable.centrarElementosTable(jtbHabitaciones);

        activarPanelHabitaciones();
    }

    private void activarPanelHabitaciones() {

        utilidadJframe.activarPanelPrincipal(true, false, false, false);
        activarBotonesPanelHabitaciones(false, false);
        agregarDatosTablaHabitaciones();
        contabilizarEstadosHabitacion();
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
        jpClientes = new javax.swing.JPanel();
        jpReservas = new javax.swing.JPanel();
        jpProductos = new javax.swing.JPanel();
        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();

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

        jpBotoneraNavegacion.setBackground(new java.awt.Color(51, 153, 255));

        jbtnClientes.setBackground(new java.awt.Color(255, 255, 255));
        jbtnClientes.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jbtnClientes.setText("Clientes");
        jbtnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClientesActionPerformed(evt);
            }
        });

        jtbnProductos.setBackground(new java.awt.Color(255, 255, 255));
        jtbnProductos.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jtbnProductos.setText("Productos");
        jtbnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnProductosActionPerformed(evt);
            }
        });

        jbtnReservas.setBackground(new java.awt.Color(255, 255, 255));
        jbtnReservas.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jbtnReservas.setText("Reservas");
        jbtnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReservasActionPerformed(evt);
            }
        });

        jbtnHabitaciones.setBackground(new java.awt.Color(255, 255, 255));
        jbtnHabitaciones.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jbtnHabitaciones.setText("Habitaciones");
        jbtnHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHabitacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBotoneraNavegacionLayout = new javax.swing.GroupLayout(jpBotoneraNavegacion);
        jpBotoneraNavegacion.setLayout(jpBotoneraNavegacionLayout);
        jpBotoneraNavegacionLayout.setHorizontalGroup(
            jpBotoneraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoneraNavegacionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpBotoneraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbtnHabitaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtbnProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jpBotoneraNavegacionLayout.setVerticalGroup(
            jpBotoneraNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoneraNavegacionLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jbtnHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jbtnReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jbtnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jtbnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        getContentPane().add(jpBotoneraNavegacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, -1, 526));

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

        lblDisponibles.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblDisponibles.setText("Disponibles:");

        lblOcupadas.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblOcupadas.setText("Ocupadas: ");

        lblRegistradas.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
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

        lblLimpieza.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblLimpieza.setText("Limpieza:");

        lblReparación.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblReparación.setText("Reparación:");

        javax.swing.GroupLayout jpHabitacionesLayout = new javax.swing.GroupLayout(jpHabitaciones);
        jpHabitaciones.setLayout(jpHabitacionesLayout);
        jpHabitacionesLayout.setHorizontalGroup(
            jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addGap(461, 461, 461)
                        .addComponent(lblTituloHabitaciones))
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpHabitacionesLayout.createSequentialGroup()
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
                                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jpHabitacionesLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbFiltroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(365, 365, 365)
                                        .addComponent(lblBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfBuscarHabitacion))
                                    .addComponent(jspHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtnActualizarTabla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtbnCrearHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtbnModificarHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtbnEliminarHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(18, 18, 18))
        );
        jpHabitacionesLayout.setVerticalGroup(
            jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHabitacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloHabitaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jpHabitacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbFiltroHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar)
                    .addComponent(jtfBuscarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jpReservasLayout = new javax.swing.GroupLayout(jpReservas);
        jpReservas.setLayout(jpReservasLayout);
        jpReservasLayout.setHorizontalGroup(
            jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        jpReservasLayout.setVerticalGroup(
            jpReservasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        getContentPane().add(jpReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 1131, -1, -1));

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

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(623, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(580, 580, 580))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTituloPrincipal)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1360, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jbtnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClientesActionPerformed
        utilidadJframe.activarPanelPrincipal(false, true, false, false);
    }//GEN-LAST:event_jbtnClientesActionPerformed

    private void jbtnHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHabitacionesActionPerformed

        activarPanelHabitaciones();
    }//GEN-LAST:event_jbtnHabitacionesActionPerformed

    private void jbtnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReservasActionPerformed

        utilidadJframe.activarPanelPrincipal(false, false, true, false);

    }//GEN-LAST:event_jbtnReservasActionPerformed

    private void jtbnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnProductosActionPerformed

        utilidadJframe.activarPanelPrincipal(false, false, false, true);

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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnActualizarTabla;
    private javax.swing.JButton jbtnClientes;
    private javax.swing.JButton jbtnHabitaciones;
    private javax.swing.JButton jbtnReservas;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JComboBox jcbFiltroHabitaciones;
    private javax.swing.JPanel jpBotoneraNavegacion;
    private javax.swing.JPanel jpClientes;
    private javax.swing.JPanel jpHabitaciones;
    private javax.swing.JPanel jpProductos;
    private javax.swing.JPanel jpReservas;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JScrollPane jspHabitaciones;
    private javax.swing.JTable jtbHabitaciones;
    private javax.swing.JButton jtbnCrearHabitacion;
    private javax.swing.JButton jtbnEliminarHabitacion;
    private javax.swing.JButton jtbnModificarHabitacion;
    private javax.swing.JButton jtbnProductos;
    private javax.swing.JTextField jtfBuscarHabitacion;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblLimpieza;
    private javax.swing.JLabel lblOcupadas;
    private javax.swing.JLabel lblRegistradas;
    private javax.swing.JLabel lblReparación;
    private javax.swing.JLabel lblTituloHabitaciones;
    private javax.swing.JLabel lblTituloPrincipal;
    // End of variables declaration//GEN-END:variables
}
