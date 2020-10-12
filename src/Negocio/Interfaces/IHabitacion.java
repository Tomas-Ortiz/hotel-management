package Negocio.Interfaces;

import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import java.util.List;

public interface IHabitacion {

    void crearHabitacion(Habitacion habitacion);

    void modificarHabitacion(Habitacion habitacion) throws Exception;

    void eliminarHabitacion(Long id) throws NonexistentEntityException;

    String validarHabitacion(String numero, String detalles, String precio);

    List<Habitacion> getHabitaciones();

    int getCountHabitacionesByEstado(String estado);

    List<Habitacion> ordenarHabitaciones(String campo);

    List<Habitacion> getHabitacionesPorValorEstado(String valor);

    Habitacion getHabitacionSeleccionada(List<Habitacion> habitacionesDisponibles, int nroHabitacion);

    List<Habitacion> buscarHabitacion(String busqueda);

    Habitacion verificarExistenciaHabitacion(int num);

}
