package Negocio.Interfaces;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import Negocio.NegocioHabitacion;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public interface IHabitacion {

    void crearHabitacion(Habitacion habitacion);

    void modificarHabitacion(Habitacion habitacion) throws Exception;

    void eliminarHabitacion(Long id) throws NonexistentEntityException;

    String validarHabitacion(String numero, String detalles, String precio);

    List<Habitacion> getHabitaciones();

    void mostrarHabitaciones(List<Habitacion> habitaciones, DefaultTableModel dtmHabitaciones);

    int getCantHabitacionesEstado(String estado);

    void contabilizarEstadosHabitaciones(JLabel lblRegistradas, JLabel lblDisponibles, JLabel lblOcupadas, JLabel lblLimpieza, JLabel lblReparación);

    List<Habitacion> ordenarHabitaciones(String campo);

    void agregarDatosTablaHabitaciones(List<Habitacion> habitaciones, DefaultTableModel dtmHabitaciones);

    List<Habitacion> getHabitacionesPorValorEstado(String valor);

    void cargarHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles, JComboBox jcbNroHabitacion);

    public Habitacion getHabitacionSeleccionada(List<Habitacion> habitacionesDisponibles, int nroHabitacion);
}
