package Negocio.Interfaces;

import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.Entidades.ReservaProducto;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.List;

public interface IReserva {

    void crearReserva(Reserva reserva);

    String validarReserva(JDateChooser jdcFechaEntrada, JDateChooser jdcFechaSalida, TimePicker tpHoraEntrada, TimePicker horaSalida);

    List<Reserva> getReservas();

    float calcularPrecioTotalAlojamiento(Habitacion habitacion, Date fechaEntrada, Date fechaSalida);

    void modificarReserva(Reserva reserva) throws Exception;

    List<Reserva> ordenarReservas(String campo, boolean otraTabla);

    int getCountReservasByEstado(String estado);

    List<Reserva> buscarReserva(String busqueda);

    Reserva encontrarReserva(Long id);

    float calcularPrecioTotalXProducto(int cantProd, float precioProd);

    float calcularPrecioTotalProductos(List<ReservaProducto> reservaProductos);

    ReservaProducto verificarExistenciaProdReserva(List<ReservaProducto> reservaProd, Long idProdSeleccionado);
    
    void cobrarReserva(Reserva reserva) throws Exception;
}
