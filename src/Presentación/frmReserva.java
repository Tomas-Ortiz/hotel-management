package Presentación;

import Negocio.Entidades.Cliente;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.NegocioCliente;
import Negocio.NegocioHabitacion;
import Negocio.NegocioReserva;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJFrame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class frmReserva extends javax.swing.JFrame {

    UtilidadJFrame utilidadJframe;
    NegocioReserva negocioReserva;
    NegocioCliente negocioCliente;
    NegocioHabitacion negocioHabitacion;
    List<Habitacion> habitacionesDisponibles;
    boolean modificarReserva = false;
    Reserva reservaModificada;

    public frmReserva() {
        initComponents();

        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Reserva", this);
        negocioReserva = new NegocioReserva();
        negocioCliente = new NegocioCliente();
        negocioHabitacion = new NegocioHabitacion();
        reservaModificada = new Reserva();

        cargarHabitacionesDisponibles();
    }

    public void setReserva(Reserva reserva) throws ParseException {

        jbtnGuardarReserva.setText("Modificar");
        modificarReserva = true;
        reservaModificada = reserva;

        jtfNombre.setText(reserva.getCliente().getNombres());
        jtfApellido.setText(reserva.getCliente().getApellidos());
        jtfDni.setText(String.valueOf(reserva.getCliente().getDni()));
        jtfCorreo.setText(reserva.getCliente().getCorreo());
        jtfTelefono.setText(String.valueOf(reserva.getCliente().getNroTelefono()));
        jtfNacionalidad.setText(reserva.getCliente().getNacionalidad());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date fechaNacimiento = sdf.parse(reserva.getCliente().getFechaNacimiento());
        jdcFechaNacimiento.setDate(fechaNacimiento);

        Date fechaEntrada = sdf.parse(reserva.getFechaEntrada());
        jdcFechaEntrada.setDate(fechaEntrada);

        Date fechaSalida = sdf.parse(reserva.getFechaSalida());
        jdcFechaSalida.setDate(fechaSalida);

        jcbTipoPago.setSelectedItem(reserva.getTipoPago());

        tpHoraEntrada.setText(reserva.getHoraEntrada());
        tpHoraSalida.setText(reserva.getHoraSalida());

        jcbNroHabitacion.addItem(reserva.getHabitacion().getNumero());
        jcbNroHabitacion.setSelectedItem(reserva.getHabitacion().getNumero());
    }

    private void cargarHabitacionesDisponibles() {

        habitacionesDisponibles = negocioHabitacion.getHabitacionesPorValorEstado("Disponible");

        if (habitacionesDisponibles.isEmpty()) {
            jcbNroHabitacion.addItem("Sin habitaciones");
            jbtnGuardarReserva.setEnabled(false);
        } else {
            negocioHabitacion.cargarHabitacionesDisponibles(habitacionesDisponibles, jcbNroHabitacion);
        }
    }

    private Habitacion getHabitacionSeleccionada(int nroHabitacion) {

        return negocioHabitacion.getHabitacionSeleccionada(habitacionesDisponibles, nroHabitacion);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpTitulo = new javax.swing.JPanel();
        lblTituloPrincipal = new javax.swing.JLabel();
        jbtnGuardarReserva = new javax.swing.JButton();
        jtbnCancelarReserva = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        lblTipoHabitacion2 = new javax.swing.JLabel();
        jcbNroHabitacion = new javax.swing.JComboBox();
        lblApellido = new javax.swing.JLabel();
        jtfApellido = new javax.swing.JTextField();
        lblDatosCliente = new javax.swing.JLabel();
        lblHabitacion = new javax.swing.JLabel();
        jtfDni = new javax.swing.JTextField();
        lblDocumento = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblNacionalidad = new javax.swing.JLabel();
        jtfNacionalidad = new javax.swing.JTextField();
        jtfCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        lblHabitacion1 = new javax.swing.JLabel();
        lblNumeroHabitacion6 = new javax.swing.JLabel();
        jcbTipoPago = new javax.swing.JComboBox();
        lblHabitacion2 = new javax.swing.JLabel();
        lblNumeroHabitacion7 = new javax.swing.JLabel();
        lblNumeroHabitacion8 = new javax.swing.JLabel();
        jdcFechaEntrada = new com.toedter.calendar.JDateChooser();
        jdcFechaSalida = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        lblNroTeléfono = new javax.swing.JLabel();
        jtfTelefono = new javax.swing.JTextField();
        jdcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        tpHoraEntrada = new com.github.lgooddatepicker.components.TimePicker();
        tpHoraSalida = new com.github.lgooddatepicker.components.TimePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpTitulo.setBackground(new java.awt.Color(0, 0, 0));

        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("Reserva");
        lblTituloPrincipal.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(630, 630, 630)
                .addComponent(lblTituloPrincipal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblTituloPrincipal)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jbtnGuardarReserva.setText("Reservar");
        jbtnGuardarReserva.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnGuardarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarReservaActionPerformed(evt);
            }
        });

        jtbnCancelarReserva.setText("Salir");
        jtbnCancelarReserva.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCancelarReservaActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre");
        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jtfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNombreActionPerformed(evt);
            }
        });

        lblTipoHabitacion2.setText("Nro. habitación");
        lblTipoHabitacion2.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jcbNroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblApellido.setText("Apellido");
        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jtfApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfApellidoActionPerformed(evt);
            }
        });

        lblDatosCliente.setText("Cliente");
        lblDatosCliente.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N

        lblHabitacion.setText("Habitación");
        lblHabitacion.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N

        jtfDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDniActionPerformed(evt);
            }
        });

        lblDocumento.setText("Documento");
        lblDocumento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblFechaNacimiento.setText("Fecha de nacimiento");
        lblFechaNacimiento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblNacionalidad.setText("Nacionalidad");
        lblNacionalidad.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jtfNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNacionalidadActionPerformed(evt);
            }
        });

        jtfCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCorreoActionPerformed(evt);
            }
        });

        lblCorreo.setText("Correo electrónico");
        lblCorreo.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblHabitacion1.setText("Pago");
        lblHabitacion1.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N

        lblNumeroHabitacion6.setText("Tipo de pago");
        lblNumeroHabitacion6.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jcbTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Tarjeta de crédito" }));
        jcbTipoPago.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblHabitacion2.setText("Alojamiento");
        lblHabitacion2.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N

        lblNumeroHabitacion7.setText("Fecha de entrada");
        lblNumeroHabitacion7.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblNumeroHabitacion8.setText("Fecha de salida");
        lblNumeroHabitacion8.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jdcFechaEntrada.setBackground(new java.awt.Color(255, 255, 255));
        jdcFechaEntrada.setPreferredSize(new java.awt.Dimension(87, 25));

        jdcFechaSalida.setBackground(new java.awt.Color(255, 255, 255));

        lblNroTeléfono.setText("Nro. teléfono");
        lblNroTeléfono.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jtfTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfTelefonoActionPerformed(evt);
            }
        });

        jdcFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));

        tpHoraEntrada.setBackground(new java.awt.Color(255, 255, 255));
        tpHoraEntrada.setFont(new java.awt.Font("Maiandra GD", 0, 11)); // NOI18N

        tpHoraSalida.setBackground(new java.awt.Color(255, 255, 255));
        tpHoraSalida.setFont(new java.awt.Font("Maiandra GD", 0, 11)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblNacionalidad)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFechaNacimiento)
                                        .addComponent(lblDocumento)
                                        .addComponent(lblNroTeléfono)
                                        .addComponent(lblNombre)
                                        .addComponent(lblApellido)
                                        .addComponent(lblCorreo))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(jtfApellido))
                                .addComponent(jtfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblTipoHabitacion2)
                                            .addGap(18, 18, 18)
                                            .addComponent(jcbNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblNumeroHabitacion6)
                                            .addGap(47, 47, 47)
                                            .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(lblHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(128, 650, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumeroHabitacion7)
                                    .addComponent(lblNumeroHabitacion8))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdcFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tpHoraEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tpHoraSalida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblHabitacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHabitacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(536, 536, 536)
                    .addComponent(jbtnGuardarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                    .addComponent(jtbnCancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(750, Short.MAX_VALUE))
                .addComponent(jpTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHabitacion2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNumeroHabitacion7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tpHoraEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcFechaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumeroHabitacion8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jdcFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tpHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHabitacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoHabitacion2))
                        .addGap(47, 47, 47)
                        .addComponent(lblHabitacion1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroHabitacion6)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDatosCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNombre)
                                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(lblApellido)))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDocumento))
                                .addGap(34, 34, 34)
                                .addComponent(lblFechaNacimiento))
                            .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNacionalidad))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCorreo))))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNroTeléfono))
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(527, 527, 527)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtbnCancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtnGuardarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarReservaActionPerformed

        String nombre = "", apellido = "", nacionalidad = "", correo = "", mensaje = "";
        String fechaNacimiento = "", fechaEntrada = "", fechaSalida = "", tipoPago = "";
        String horaEntrada = "", horaSalida = "";
        Long dni = -1L, nroTelefono = -1L;
        int nroHabitacion = -1;
        float precioTotal;
        Habitacion habitacionSeleccionada;
        Cliente clienteAux;
        boolean datosValidos = true, confirmarModificacion = true;

        try {
            String mensaje1 = negocioCliente.validarCliente(jtfNombre.getText(), jtfApellido.getText(), jtfNacionalidad.getText(), jtfCorreo.getText(), jtfTelefono.getText(), jtfDni.getText(), jdcFechaNacimiento);
            String mensaje2 = negocioReserva.validarReserva(jdcFechaEntrada, jdcFechaSalida, tpHoraEntrada, tpHoraSalida);

            if (!mensaje1.equals("ok") || !mensaje2.equals("ok")) {

                datosValidos = false;

                if (!mensaje1.equals("ok")) {
                    mensaje = mensaje1;
                } else {
                    mensaje = mensaje2;
                }

                JOptionPane.showConfirmDialog(null, mensaje, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

            } else {
                nombre = jtfNombre.getText();
                apellido = jtfApellido.getText();
                nacionalidad = jtfNacionalidad.getText();
                correo = jtfCorreo.getText();
                dni = Long.parseLong(jtfDni.getText());
                nroTelefono = Long.parseLong(jtfTelefono.getText());
                nroHabitacion = (int) jcbNroHabitacion.getSelectedItem();
                tipoPago = (String) jcbTipoPago.getSelectedItem();

                fechaNacimiento = UtilidadGeneral.DateToString(jdcFechaNacimiento.getDate());
                fechaEntrada = UtilidadGeneral.DateToString(jdcFechaEntrada.getDate());
                fechaSalida = UtilidadGeneral.DateToString(jdcFechaSalida.getDate());

                horaEntrada = tpHoraEntrada.getText();
                horaSalida = tpHoraSalida.getText();
            }

        } catch (NumberFormatException nfe) {
            datosValidos = false;
            JOptionPane.showConfirmDialog(null, "Verifica de haber ingresado el formato correcto.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }

        if (datosValidos) {
            if (modificarReserva) {
                try {
                    int confirmado = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas modificar la reserva con id " + reservaModificada.getId() + "?", "Confirmar modificación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (confirmado == JOptionPane.YES_OPTION) {

                        reservaModificada.setFechaEntrada(fechaEntrada);
                        reservaModificada.setFechaSalida(fechaSalida);
                        reservaModificada.setHoraEntrada(horaEntrada);
                        reservaModificada.setHoraSalida(horaSalida);
                        reservaModificada.setTipoPago(tipoPago);

                        reservaModificada.getCliente().setNombres(nombre);
                        reservaModificada.getCliente().setApellidos(apellido);
                        reservaModificada.getCliente().setDni(dni);
                        reservaModificada.getCliente().setFechaNacimiento(fechaNacimiento);
                        reservaModificada.getCliente().setCorreo(correo);
                        reservaModificada.getCliente().setNacionalidad(nacionalidad);
                        reservaModificada.getCliente().setNroTelefono(nroTelefono);

                        Cliente clienteExistente = negocioCliente.verificarExistenciaCliente(dni);

                        //Si no existe ya el cliente ingresado
                        if (clienteExistente == null) {
                            negocioCliente.modificarCliente(reservaModificada.getCliente());
                            confirmarModificacion = true;
                        } else {

                            mensaje = "Advertencia: el cliente ingresado con el dni adjunto ya existe, por lo que se lo asociará directamente con él.";
                            confirmado = JOptionPane.showOptionDialog(null, mensaje, "Cliente existente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                            if (confirmado == JOptionPane.OK_OPTION) {
                                reservaModificada.setCliente(clienteExistente);
                                confirmarModificacion = true;
                            } else {
                                mensaje = "";
                                confirmarModificacion = false;
                            }
                        }
                        if (confirmarModificacion) {

                            // Si se modifico la habitación
                            if (reservaModificada.getHabitacion().getNumero() != nroHabitacion) {
                                reservaModificada.getHabitacion().setEstado("Disponible");
                                negocioHabitacion.modificarHabitacion(reservaModificada.getHabitacion());

                                reservaModificada.setHabitacion(null);

                                habitacionSeleccionada = getHabitacionSeleccionada(nroHabitacion);
                                habitacionSeleccionada.setEstado("Ocupada");
                                negocioHabitacion.modificarHabitacion(habitacionSeleccionada);

                                reservaModificada.setHabitacion(habitacionSeleccionada);
                            }

                            precioTotal = negocioReserva.calcularPrecioTotal(reservaModificada.getHabitacion(), jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());
                            reservaModificada.setPrecioTotal(precioTotal);

                            negocioReserva.modificarReserva(reservaModificada);
                            mensaje = "¡Reserva modificada exitosamente!";
                            modificarReserva = false;
                        }

                    }

                } catch (Exception e) {
                    mensaje = "Error al modificar la reserva." + e.getMessage();
                }
            } else {

                int confirmado = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas crear la reserva?", "Confirmar creación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirmado == JOptionPane.YES_OPTION) {

                    habitacionSeleccionada = getHabitacionSeleccionada(nroHabitacion);

                    precioTotal = negocioReserva.calcularPrecioTotal(habitacionSeleccionada, jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());

                    Cliente clienteNuevo = new Cliente(nombre, apellido, nacionalidad, correo, fechaNacimiento, nroTelefono, dni);

                    habitacionSeleccionada.setEstado("Ocupada");

                    try {
                        negocioHabitacion.modificarHabitacion(habitacionSeleccionada);
                    } catch (Exception ex) {
                        System.out.println("Error al modificar el estado de la habitación: " + ex.getMessage());
                    }

                    Cliente clienteExistente = negocioCliente.verificarExistenciaCliente(dni);

                    //Si no existe ya el cliente ingresado
                    if (clienteExistente == null) {
                        clienteAux = clienteNuevo;
                        negocioCliente.crearCliente(clienteNuevo);
                    } else {
                        clienteAux = clienteExistente;
                        mensaje = "Advertencia: el cliente ingresado con el dni adjunto ya existe, por lo que se lo asociará directamente con él.";
                        JOptionPane.showMessageDialog(null, mensaje, "Cliente existente", JOptionPane.INFORMATION_MESSAGE);
                    }

                    String estado = "Pendiente";
                    Reserva reserva = new Reserva(fechaEntrada, fechaSalida, tipoPago, horaEntrada, horaSalida, precioTotal, estado, clienteAux, habitacionSeleccionada);
                    negocioReserva.crearReserva(reserva);

                    mensaje = "¡Reserva creada exitosamente!";
                }
            }
            //actualizar las habitaciones disponibles
            cargarHabitacionesDisponibles();

            if (!mensaje.equals("")) {
                JOptionPane.showConfirmDialog(null, mensaje, "Reserva", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
            this.dispose();
        }
    }//GEN-LAST:event_jbtnGuardarReservaActionPerformed

    private void jtbnCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCancelarReservaActionPerformed

        this.dispose();

    }//GEN-LAST:event_jtbnCancelarReservaActionPerformed

    private void jtfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNombreActionPerformed

    private void jtfApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfApellidoActionPerformed

    private void jtfDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfDniActionPerformed

    private void jtfNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNacionalidadActionPerformed

    private void jtfCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfCorreoActionPerformed

    private void jtfTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfTelefonoActionPerformed

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
            java.util.logging.Logger.getLogger(frmReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton jbtnGuardarReserva;
    private javax.swing.JComboBox jcbNroHabitacion;
    private javax.swing.JComboBox jcbTipoPago;
    private com.toedter.calendar.JDateChooser jdcFechaEntrada;
    private com.toedter.calendar.JDateChooser jdcFechaNacimiento;
    private com.toedter.calendar.JDateChooser jdcFechaSalida;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JButton jtbnCancelarReserva;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JTextField jtfCorreo;
    private javax.swing.JTextField jtfDni;
    private javax.swing.JTextField jtfNacionalidad;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfTelefono;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDatosCliente;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblHabitacion;
    private javax.swing.JLabel lblHabitacion1;
    private javax.swing.JLabel lblHabitacion2;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNroTeléfono;
    private javax.swing.JLabel lblNumeroHabitacion6;
    private javax.swing.JLabel lblNumeroHabitacion7;
    private javax.swing.JLabel lblNumeroHabitacion8;
    private javax.swing.JLabel lblTipoHabitacion2;
    private javax.swing.JLabel lblTituloPrincipal;
    private com.github.lgooddatepicker.components.TimePicker tpHoraEntrada;
    private com.github.lgooddatepicker.components.TimePicker tpHoraSalida;
    // End of variables declaration//GEN-END:variables
}
