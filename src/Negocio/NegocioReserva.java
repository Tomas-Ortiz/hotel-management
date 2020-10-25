package Negocio;

import Datos.ReservaJpaController;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.Interfaces.IReserva;
import com.github.lgooddatepicker.components.TimePicker;
import com.mysql.jdbc.StringUtils;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;

public class NegocioReserva implements IReserva {

    private final ReservaJpaController reservaController = new ReservaJpaController();
    private static NegocioReserva negocioReserva;

    public static NegocioReserva getNegocioReserva() {
        if (negocioReserva == null) {
            return negocioReserva = new NegocioReserva();
        }
        return negocioReserva;
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

        if (jdcFechaEntrada.getDate() == null || jdcFechaSalida.getDate() == null || StringUtils.isEmptyOrWhitespaceOnly(tpHoraEntrada.getText())
                || StringUtils.isEmptyOrWhitespaceOnly(tpHoraSalida.getText())) {
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
    public int getCountReservasByEstado(String estado) {
        return reservaController.getCountReservasByState(estado);
    }

    @Override
    public void modificarReserva(Reserva reserva) throws Exception {
        reservaController.edit(reserva);
    }

    @Override
    public List<Reserva> ordenarReservas(String campo, boolean otraTabla) {
        if (otraTabla) {
            return reservaController.reservasOrderByInOtherTable(campo);
        }
        return reservaController.reservasOrderBy(campo);
    }

    @Override
    public List<Reserva> buscarReserva(String busqueda) {
        return reservaController.buscarReserva(busqueda);
    }

    @Override
    public Reserva encontrarReserva(Long id) {
        return reservaController.findReserva(id);
    }

    @Override
    public float calcularPrecioTotalXProducto(int cantProd, float precioProd) {
        return cantProd * precioProd;
    }
}
