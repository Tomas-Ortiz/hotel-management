package Negocio.Interfaces;

import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public interface IReserva {

    void crearReserva(Reserva reserva);

    String validarReserva(JDateChooser jdcFechaEntrada, JDateChooser jdcFechaSalida, TimePicker tpHoraEntrada, TimePicker horaSalida);

    float calcularPrecioTotal(Habitacion habitacion, Date fechaEntrada, Date fechaSalida);

}
