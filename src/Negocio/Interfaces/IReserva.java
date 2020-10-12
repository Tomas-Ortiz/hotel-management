package Negocio.Interfaces;

import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public interface IReserva {

    void crearReserva(Reserva reserva);

    String validarReserva(JDateChooser jdcFechaEntrada, JDateChooser jdcFechaSalida, TimePicker tpHoraEntrada, TimePicker horaSalida);

    List<Reserva> getReservas();

    float calcularPrecioTotal(Habitacion habitacion, Date fechaEntrada, Date fechaSalida);

    void modificarReserva(Reserva reserva) throws Exception;

    void mostrarReservas(List<Reserva> reservas, DefaultTableModel dtmReservas);

    void agregarDatosTablaReservas(List<Reserva> reservas, DefaultTableModel dtmReservas);

    void actualizarDatosTablaReservas(DefaultTableModel dtmReservas);

    void contabilizarEstadosReserva(JLabel lblOcupadas, JLabel cobradas, JLabel pendientes);

    List<Reserva> ordenarReservas(String campo, boolean otraTabla);

    int getCountReservasByEstado(String estado);

    List<Reserva> buscarReserva(String busqueda);
}
