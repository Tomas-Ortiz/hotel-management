package Presentación;

import Negocio.Entidades.Cliente;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.NegocioCliente;
import Negocio.NegocioHabitacion;
import Negocio.NegocioReserva;
import Negocio.UtilidadGeneral;
import Negocio.UtilidadIcono;
import Negocio.UtilidadJFrame;
import Negocio.UtilidadJOptionPane;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class frmReserva extends javax.swing.JFrame {

    private final UtilidadJFrame utilidadJframe;
    private final NegocioReserva negocioReserva;
    private final NegocioCliente negocioCliente;
    private final NegocioHabitacion negocioHabitacion;
    private final SimpleDateFormat sdf;
    private List<Habitacion> habitacionesDisponibles;
    private boolean modificarReserva = false;
    private Reserva reservaModificada;

    public frmReserva() {
        initComponents();
        mostrarIconos();
        utilidadJframe = UtilidadJFrame.getUtilidadFrame();
        utilidadJframe.configurarFrame("Reserva", this);
        negocioReserva = NegocioReserva.getNegocioReserva();
        negocioCliente = NegocioCliente.getNegocioCliente();
        negocioHabitacion = NegocioHabitacion.getNegocioHabitacion();
        reservaModificada = new Reserva();
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        mostrarHabitacionesDisponibles();
        mostrarListaPaises();
    }

    private void mostrarIconos() {
        UtilidadIcono.cargarIconoFrame(this);
        Image iconoReserva = new ImageIcon("src/Recursos/Iconos/iconoReserva2.png").getImage();
        UtilidadIcono.cargarIconoLabel(iconoReserva, lblIconoReserva, 50, 50);
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
        for (String pais : UtilidadGeneral.getPaises()) {
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
        lblIconoReserva = new javax.swing.JLabel();
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
        jSeparator9 = new javax.swing.JSeparator();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIconoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(528, Short.MAX_VALUE))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblIconoReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        jbtnGuardarReserva.setText("Reservar");
        jbtnGuardarReserva.setBackground(new java.awt.Color(0, 102, 204));
        jbtnGuardarReserva.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jbtnGuardarReserva.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGuardarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarReservaActionPerformed(evt);
            }
        });

        jtbnCancelarReserva.setText("Salir");
        jtbnCancelarReserva.setBackground(new java.awt.Color(153, 0, 51));
        jtbnCancelarReserva.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jtbnCancelarReserva.setForeground(new java.awt.Color(255, 255, 255));
        jtbnCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCancelarReservaActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre");
        lblNombre.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblTipoHabitacion2.setText("Nro. habitación");
        lblTipoHabitacion2.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jcbNroHabitacion.setBackground(new java.awt.Color(102, 204, 255));
        jcbNroHabitacion.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jcbNroHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNroHabitacionActionPerformed(evt);
            }
        });

        lblApellido.setText("Apellido");
        lblApellido.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblDatosCliente.setText("Cliente");
        lblDatosCliente.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N

        lblHabitacion.setText("Habitación");
        lblHabitacion.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N

        jtfDni.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        lblDocumento.setText("DNI");
        lblDocumento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblFechaNacimiento.setText("Fecha de nacimiento");
        lblFechaNacimiento.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblNacionalidad.setText("Nacionalidad");
        lblNacionalidad.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblCorreo.setText("Correo electrónico");
        lblCorreo.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        lblHabitacion1.setText("Pago");
        lblHabitacion1.setFont(new java.awt.Font("Maiandra GD", 3, 22)); // NOI18N

        lblNumeroHabitacion6.setText("Tipo de pago");
        lblNumeroHabitacion6.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N

        jcbTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Tarjeta de crédito" }));
        jcbTipoPago.setBackground(new java.awt.Color(102, 204, 255));
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

        jdcFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));

        tpHoraEntrada.setBackground(new java.awt.Color(255, 255, 255));
        tpHoraEntrada.setFont(new java.awt.Font("Maiandra GD", 0, 11)); // NOI18N

        tpHoraSalida.setBackground(new java.awt.Color(255, 255, 255));
        tpHoraSalida.setFont(new java.awt.Font("Maiandra GD", 0, 11)); // NOI18N

        jcbPaises.setBackground(new java.awt.Color(102, 204, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHabitacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblNombre)
                .addGap(104, 104, 104)
                .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(lblNumeroHabitacion7)
                .addGap(6, 6, 6)
                .addComponent(jdcFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(tpHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblApellido)
                .addGap(103, 103, 103)
                .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(lblNumeroHabitacion8)
                .addGap(22, 22, 22)
                .addComponent(jdcFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(tpHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDocumento)
                    .addComponent(lblFechaNacimiento))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTipoHabitacion2)
                        .addGap(25, 25, 25)
                        .addComponent(jcbNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(710, 710, 710)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHabitacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblNacionalidad)
                .addGap(65, 65, 65)
                .addComponent(jcbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNumeroHabitacion6)
                        .addGap(45, 45, 45)
                        .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblCorreo)
                .addGap(19, 19, 19)
                .addComponent(jtfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(lblNroTeléfono)
                .addGap(61, 61, 61)
                .addComponent(jtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator9)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnGuardarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jtbnCancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatosCliente)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHabitacion2))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblNombre))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNumeroHabitacion7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblApellido))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNumeroHabitacion8))
                    .addComponent(jdcFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblDocumento)
                        .addGap(27, 27, 27)
                        .addComponent(lblFechaNacimiento))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jtfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHabitacion)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblTipoHabitacion2))
                            .addComponent(jcbNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHabitacion1))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNacionalidad))
                    .addComponent(jcbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumeroHabitacion6)
                            .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCorreo))
                    .addComponent(jtfCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNroTeléfono))
                    .addComponent(jtfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnGuardarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtbnCancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
                        precioTotal = negocioReserva.calcularPrecioTotalAlojamiento(reservaModificada.getHabitacion(), jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());
                        float precioTotalProductos = negocioReserva.calcularPrecioTotalProductos(reservaModificada.getProductos());
                        reservaModificada.setPrecioTotal(precioTotal + precioTotalProductos);

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

                    precioTotal = negocioReserva.calcularPrecioTotalAlojamiento(habitacionSeleccionada, jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());

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
    private javax.swing.JSeparator jSeparator9;
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
    private javax.swing.JLabel lblIconoReserva;
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
