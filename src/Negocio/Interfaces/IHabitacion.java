package Negocio.Interfaces;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import Negocio.NegocioHabitacion;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public interface IHabitacion {

    public void crearHabitacion(Habitacion habitacion);

    public void modificarHabitacion(Habitacion habitacion) throws Exception;

    public void eliminarHabitacion(Long id) throws NonexistentEntityException;

    public String validarHabitacion(String numero, String detalles, String precio);

    public List<Habitacion> getHabitaciones();

    public void mostrarHabitaciones(List<Habitacion> habitaciones, DefaultTableModel dtmHabitaciones);

    public int getCantHabitacionesEstado(String estado);

    public void contabilizarEstadosHabitaciones(JLabel lblRegistradas, JLabel lblDisponibles, JLabel lblOcupadas, JLabel lblLimpieza, JLabel lblReparación);

    public List<Habitacion> ordenarHabitaciones(String campo);

    public void agregarDatosTablaHabitaciones(List<Habitacion> habitaciones, NegocioHabitacion negocioHabitacion, DefaultTableModel dtmHabitaciones);

}
