package Negocio;

import Datos.HabitacionJpaController;
import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import Negocio.Interfaces.IHabitacion;
import java.util.List;

public class NegocioHabitacion implements IHabitacion {

    private final HabitacionJpaController habitacionController;

    public NegocioHabitacion() {
        habitacionController = new HabitacionJpaController();
    }

    @Override
    public void crearHabitacion(Habitacion habitacion) {
        if (habitacion != null) {
            habitacionController.create(habitacion);
        }
    }

    @Override
    public void modificarHabitacion(Habitacion habitacion) throws Exception {

        if (habitacion != null) {
            habitacionController.edit(habitacion);
        }
    }

    @Override
    public void eliminarHabitacion(Long id) throws NonexistentEntityException {
        if (id != null) {
            habitacionController.destroy(id);
        }
    }

    @Override
    public String validarHabitacion(String detalles, String precio) {

        String mensaje = "ok";

        if (detalles.length() == 0 || precio.equals("")) {
            mensaje = "Debes completar todos los campos.";
        }
        return mensaje;
    }

    @Override
    public List<Habitacion> getHabitaciones() {
        return habitacionController.findHabitacionEntities();
    }

    @Override
    public int getCountHabitacionesByEstado(String estado) {
        return habitacionController.getCountHabitacionesByState(estado);
    }

    @Override
    public List<Habitacion> ordenarHabitaciones(String campo) {
        return habitacionController.habitacionesOrderBy(campo);
    }

    @Override
    public List<Habitacion> getHabitacionesPorValorEstado(String valor) {
        return habitacionController.getHabitacionesByValorEstado(valor);
    }

    @Override
    public Habitacion getHabitacionSeleccionada(List<Habitacion> habitacionesDisponibles, int nroHabitacion) {

        for (Habitacion habitacion : habitacionesDisponibles) {
            if (habitacion.getNumero() == nroHabitacion) {
                return habitacion;
            }
        }
        return null;
    }

    @Override
    public Habitacion verificarExistenciaHabitacion(int num) {
        return habitacionController.findHabitacionByNum(num);
    }

    @Override
    public List<Habitacion> buscarHabitacion(String busqueda) {
        return habitacionController.buscarHabitacion(busqueda);
    }

    @Override
    public Habitacion encontrarHabitacion(Long id) {
        return habitacionController.findHabitacion(id);
    }
}
