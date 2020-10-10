package Negocio;

import Datos.ReservaJpaController;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.Interfaces.IReserva;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class NegocioReserva implements IReserva {

    private final ReservaJpaController reservaController;

    public NegocioReserva() {
        reservaController = new ReservaJpaController();
    }

    @Override
    public void crearReserva(Reserva reserva) {
        if (reserva != null) {
            reservaController.create(reserva);
        }
    }

    @Override
    public List<Reserva> getReservas() {
        return reservaController.findReservaEntities();
    }

    @Override
    public String validarReserva(JDateChooser jdcFechaEntrada, JDateChooser jdcFechaSalida, TimePicker tpHoraEntrada, TimePicker tpHoraSalida) {

        String mensaje = "ok";

        if (jdcFechaEntrada.getDate() == null || jdcFechaSalida.getDate() == null || tpHoraEntrada.getText().equals("") || tpHoraSalida.getText().equals("")) {
            mensaje = "Debes completar todos los campos.";
        }

        if (mensaje.equals("ok")) {

            long dias = UtilidadGeneral.calcularDiasEntreFechas(jdcFechaEntrada.getDate(), jdcFechaSalida.getDate());

            String fechaEntrada = UtilidadGeneral.DateToString(jdcFechaEntrada.getDate());
            String fechaSalida = UtilidadGeneral.DateToString(jdcFechaSalida.getDate());

            // Si 13-09-2020 == 13-09-2020 es válido (dia = 1)
            if (dias <= 0 && !fechaEntrada.equals(fechaSalida)) {
                mensaje = "Verifica las fechas de alojamiento.";
            }
        }

        return mensaje;
    }

    @Override
    public float calcularPrecioTotal(Habitacion habitacion, Date fechaEntrada, Date fechaSalida) {

        long dias = UtilidadGeneral.calcularDiasEntreFechas(fechaEntrada, fechaSalida);

        if (dias == 0) {
            dias = 1;
        }

        return dias * habitacion.getPrecioDia();
    }

    @Override
    public void mostrarReservas(List<Reserva> reservas, DefaultTableModel dtmReservas) {

        dtmReservas.setRowCount(0);

        for (Reserva reserva : reservas) {
            dtmReservas.addRow(new Object[]{reserva.getId(), reserva.getCliente().getNombres(), reserva.getCliente().getApellidos(), reserva.getHabitacion().getNumero(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getHoraEntrada(), reserva.getHoraSalida(), reserva.getEstado(), reserva.getPrecioTotal()});
        }
    }

    @Override
    public void agregarDatosTablaReservas(List<Reserva> reservas, DefaultTableModel dtmReservas) {
        if (reservas.isEmpty()) {
            reservas = getReservas();
        }
        mostrarReservas(reservas, dtmReservas);
    }

    @Override
    public void contabilizarEstadosReserva(JLabel lblOcupadas, JLabel cobradas, JLabel pendientes) {
        lblOcupadas.setText("Ocupadas (" + getReservas().size() + ")");
    }

    @Override
    public void modificarReserva(Reserva reserva) throws Exception {
        reservaController.edit(reserva);
    }
}
