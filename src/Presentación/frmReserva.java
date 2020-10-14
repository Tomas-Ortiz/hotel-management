package Presentación;

import Negocio.Entidades.Cliente;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.NegocioCliente;
import Negocio.NegocioHabitacion;
import Negocio.NegocioReserva;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

        mostrarHabitacionesDisponibles();
        mostrarListaPaises();
    }

    public void mostrarReserva(Reserva reserva) throws ParseException {

        jbtnGuardarReserva.setText("Modificar");
        modificarReserva = true;
        reservaModificada = reserva;

        jtfNombre.setText(reserva.getCliente().getNombres());
        jtfApellido.setText(reserva.getCliente().getApellidos());
        jtfDni.setText(String.valueOf(reserva.getCliente().getDni()));
        jtfCorreo.setText(reserva.getCliente().getCorreo());
        jtfTelefono.setText(String.valueOf(reserva.getCliente().getNroTelefono()));
        jcbPaises.setSelectedItem(reserva.getCliente().getNacionalidad());

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

        habitacionesDisponibles.add(reserva.getHabitacion());
        agregarHabitacionesDisp();
        jcbNroHabitacion.setSelectedItem(reserva.getHabitacion().getNumero());
    }

    private void mostrarListaPaises() {
        String[] countryCodes = java.util.Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            String pais = locale.getDisplayCountry();
            jcbPaises.addItem(pais);
        }
    }

    private void mostrarHabitacionesDisponibles() {
        habitacionesDisponibles = negocioHabitacion.getHabitacionesPorValorEstado("Disponible");

        if (habitacionesDisponibles.isEmpty()) {
            jcbNroHabitacion.addItem("Sin habitaciones");
            jbtnGuardarReserva.setEnabled(false);
        } else {
            jbtnGuardarReserva.setEnabled(true);
            agregarHabitacionesDisp();
        }
    }

    private void agregarHabitacionesDisp() {
        jcbNroHabitacion.removeAllItems();
        for (Habitacion habitacion : habitacionesDisponibles) {
            jcbNroHabitacion.addItem(habitacion.getNumero());
        }
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
        jcbPaises = new javax.swing.JComboBox();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addContainerGap(605, Short.MAX_VALUE))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(lblTituloPrincipal)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        jbtnGuardarReserva.setText("Reservar");
        jbtnGuardarReserva.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jbtnGuardarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarReservaActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnGuardarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 610, 146, 54));

        jtbnCancelarReserva.setText("Salir");
        jtbnCancelarReserva.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jtbnCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCancelarReservaActionPerformed(evt);
            }
        });
        getContentPane().add(jtbnCancelarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 610, 146, 54));

        lblNombre.setText("Nombre");
        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));
        getContentPane().add(jtfNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 270, 33));

        lblTipoHabitacion2.setText("Nro. habitación");
        lblTipoHabitacion2.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblTipoHabitacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, -1, -1));

        jcbNroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jcbNroHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNroHabitacionActionPerformed(evt);
            }
        });
        getContentPane().add(jcbNroHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 310, 177, 31));

        lblApellido.setText("Apellido");
        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));
        getContentPane().add(jtfApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 270, 33));

        lblDatosCliente.setText("Cliente");
        lblDatosCliente.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        getContentPane().add(lblDatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 86, -1));

        lblHabitacion.setText("Habitación");
        lblHabitacion.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        getContentPane().add(lblHabitacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 152, -1));
        getContentPane().add(jtfDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 270, 33));

        lblDocumento.setText("DNI");
        lblDocumento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        lblFechaNacimiento.setText("Fecha de nacimiento");
        lblFechaNacimiento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        lblNacionalidad.setText("Nacionalidad");
        lblNacionalidad.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, -1, -1));
        getContentPane().add(jtfCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 270, 33));

        lblCorreo.setText("Correo electrónico");
        lblCorreo.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, -1, -1));

        lblHabitacion1.setText("Pago");
        lblHabitacion1.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        getContentPane().add(lblHabitacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 390, 100, -1));

        lblNumeroHabitacion6.setText("Tipo de pago");
        lblNumeroHabitacion6.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblNumeroHabitacion6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, -1, -1));

        jcbTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Tarjeta de crédito" }));
        jcbTipoPago.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(jcbTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 440, 177, 31));

        lblHabitacion2.setText("Alojamiento");
        lblHabitacion2.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N
        getContentPane().add(lblHabitacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 152, -1));

        lblNumeroHabitacion7.setText("Fecha de entrada");
        lblNumeroHabitacion7.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblNumeroHabitacion7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, -1, 25));

        lblNumeroHabitacion8.setText("Fecha de salida");
        lblNumeroHabitacion8.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblNumeroHabitacion8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jdcFechaEntrada.setBackground(new java.awt.Color(255, 255, 255));
        jdcFechaEntrada.setPreferredSize(new java.awt.Dimension(87, 25));
        getContentPane().add(jdcFechaEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 150, 163, 33));

        jdcFechaSalida.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jdcFechaSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 200, 163, 33));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 446, 5));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 435, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 270, 435, 10));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 390, 435, 10));

        lblNroTeléfono.setText("Nro. teléfono");
        lblNroTeléfono.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(lblNroTeléfono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, -1, -1));
        getContentPane().add(jtfTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, 270, 33));

        jdcFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jdcFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 163, 33));

        tpHoraEntrada.setBackground(new java.awt.Color(255, 255, 255));
        tpHoraEntrada.setFont(new java.awt.Font("Maiandra GD", 0, 11)); // NOI18N
        getContentPane().add(tpHoraEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 150, 110, 33));

        tpHoraSalida.setBackground(new java.awt.Color(255, 255, 255));
        tpHoraSalida.setFont(new java.awt.Font("Maiandra GD", 0, 11)); // NOI18N
        getContentPane().add(tpHoraSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 200, 110, 33));

        getContentPane().add(jcbPaises, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 163, 33));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 435, 10));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 435, 10));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, 435, 10));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 446, 10));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarReservaActionPerformed

        String nombre = "", apellido = "", nacionalidad = "", correo = "", mensaje = "", titulo = "";
        String fechaNacimiento = "", fechaEntrada = "", fechaSalida = "", tipoPago = "";
        String horaEntrada = "", horaSalida = "";
        Long dni = -1L, nroTelefono = -1L;
        int nroHabitacion = -1;
        float precioTotal;
        Habitacion habitacionSeleccionada;
        Cliente clienteAux;
        boolean datosValidos = true;

        try {
            String mensaje1 = negocioCliente.validarCliente(jtfNombre.getText(), jtfApellido.getText(), jtfCorreo.getText(), jtfTelefono.getText(), jtfDni.getText(), jdcFechaNacimiento);
            String mensaje2 = negocioReserva.validarReserva(jdcFechaEntrada, jdcFechaSalida, tpHoraEntrada, tpHoraSalida);

            // Error en los datos de entrada de cliente o reserva
            if (!mensaje1.equals("ok") || !mensaje2.equals("ok")) {
                datosValidos = false;

                if (!mensaje1.equals("ok")) {
                    mensaje = mensaje1;
                } else {
                    mensaje = mensaje2;
                }
                titulo = "Error";
                UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
            } else {
                nombre = jtfNombre.getText();
                apellido = jtfApellido.getText();
                nacionalidad = (String) jcbPaises.getSelectedItem();
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
            mensaje = "Verifica de haber ingresado el formato correcto.";
            titulo = "Error";
            UtilidadJOptionPane.mostrarMensajeError(mensaje, titulo);
        }

        if (datosValidos) {
            if (modificarReserva) {
                try {
                    mensaje = "¿Estás seguro que deseas modificar la reserva con id " + reservaModificada.getId() + "?";
                    titulo = "Confirmar modificación";
                    int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo(mensaje, titulo);

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

                        if (clienteExistente == null) {
                            negocioCliente.modificarCliente(reservaModificada.getCliente());
                        } else {
                            mensaje = "El cliente ingresado con el dni adjunto ya existe,\n"
                                    + "¿deseas actualizar los datos del cliente existente o asociarse al cliente existente "
                                    + "sin actualizar sus datos?";
                            titulo = "Modificar cliente";
                            Object[] botones = {"Actualizar datos", "Mantener datos"};

                            confirmado = UtilidadJOptionPane.mostrarMensajePreguntaPersonalizada(mensaje, titulo, botones);

                            if (confirmado == JOptionPane.YES_OPTION) {
                                clienteExistente.setNombres(reservaModificada.getCliente().getNombres());
                                clienteExistente.setApellidos(reservaModificada.getCliente().getApellidos());
                                clienteExistente.setFechaNacimiento(reservaModificada.getCliente().getFechaNacimiento());
                                clienteExistente.setCorreo(reservaModificada.getCliente().getCorreo());
                                clienteExistente.setNacionalidad(reservaModificada.getCliente().getNacionalidad());
                                clienteExistente.setNroTelefono(reservaModificada.getCliente().getNroTelefono());

                                negocioCliente.modificarCliente(clienteExistente);
                            }
                            reservaModificada.setCliente(clienteExistente);
                        }
                        // Si se modifico la habitación
                        if (reservaModificada.getHabitacion().getNumero() != nroHabitacion) {
                            reservaModificada.getHabitacion().setEstado("Disponible");
                            negocioHabitacion.modificarHabitacion(reservaModificada.getHabitacion());

                            reservaModificada.setHabitacion(null);

                            habitacionSeleccionada = negocioHabitacion.getHabitacionSeleccionada(habitacionesDisponibles, nroHabitacion);
                            habitacionSeleccionada.setEstado("Ocupada");

                            negocioHabitacion.modificarHabitacion(habitacionSeleccionada);
                            reservaModificada.setHabitacion(habitacionSeleccionada);
                        }
                        precioTotal = negocioReserva.calcularPrecioTotal(reservaModificada.getHabitacion(), jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());
                        reservaModificada.setPrecioTotal(precioTotal);

                        negocioReserva.modificarReserva(reservaModificada);
                        mensaje = "¡Reserva modificada exitosamente!";
                        modificarReserva = false;
                    } else {
                        mensaje = "";
                    }
                } catch (Exception e) {
                    mensaje = "Error al modificar la reserva." + e.getMessage();
                }
            } else {
                mensaje = "¿Estás seguro que deseas crear la reserva?";
                titulo = "Confirmar creación";
                int confirmado = UtilidadJOptionPane.mostrarMensajePreguntaYesNo(mensaje, titulo);

                if (confirmado == JOptionPane.YES_OPTION) {
                    habitacionSeleccionada = negocioHabitacion.getHabitacionSeleccionada(habitacionesDisponibles, nroHabitacion);
                    habitacionSeleccionada.setEstado("Ocupada");

                    precioTotal = negocioReserva.calcularPrecioTotal(habitacionSeleccionada, jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());

                    Cliente clienteNuevo = new Cliente(nombre, apellido, nacionalidad, correo, fechaNacimiento, nroTelefono, dni);

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
                        mensaje = "El cliente ingresado con el dni adjunto ya existe, por lo que se lo asociará directamente con él.";
                        titulo = "Cliente existente";
                        UtilidadJOptionPane.mostrarMensajeInformacion(mensaje, titulo);
                    }

                    String estado = "Pendiente";
                    Reserva reserva = new Reserva(fechaEntrada, fechaSalida, tipoPago, horaEntrada, horaSalida, precioTotal, estado, clienteAux, habitacionSeleccionada);
                    negocioReserva.crearReserva(reserva);

                    mensaje = "¡Reserva creada exitosamente!";
                } else {
                    mensaje = "";
                }
            }
            //actualizar las habitaciones disponibles
            mostrarHabitacionesDisponibles();

            if (!mensaje.equals("")) {
                titulo = "Reserva";
                UtilidadJOptionPane.mostrarMensajeInformacion(mensaje, titulo);
            }
            this.dispose();
        }
    }//GEN-LAST:event_jbtnGuardarReservaActionPerformed

    private void jtbnCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCancelarReservaActionPerformed
        this.dispose();
    }//GEN-LAST:event_jtbnCancelarReservaActionPerformed

    private void jcbNroHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNroHabitacionActionPerformed
        if (jcbNroHabitacion.getItemCount() > 0 && jcbNroHabitacion.getSelectedItem().equals("Sin habitaciones")) {
            jbtnGuardarReserva.setEnabled(false);
        } else {
            jbtnGuardarReserva.setEnabled(true);
        }
    }//GEN-LAST:event_jcbNroHabitacionActionPerformed

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
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JButton jbtnGuardarReserva;
    private javax.swing.JComboBox jcbNroHabitacion;
    private javax.swing.JComboBox jcbPaises;
    private javax.swing.JComboBox jcbTipoPago;
    private com.toedter.calendar.JDateChooser jdcFechaEntrada;
    private com.toedter.calendar.JDateChooser jdcFechaNacimiento;
    private com.toedter.calendar.JDateChooser jdcFechaSalida;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JButton jtbnCancelarReserva;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JTextField jtfCorreo;
    private javax.swing.JTextField jtfDni;
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
