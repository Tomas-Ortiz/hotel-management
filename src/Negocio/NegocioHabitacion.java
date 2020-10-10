package Negocio;

import Datos.HabitacionJpaController;
import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import Negocio.Interfaces.IHabitacion;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

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
    public String validarHabitacion(String numero, String detalles, String precio) {

        String mensaje = "ok";

        if (numero.equals("") || detalles.length() == 0 || precio.equals("")) {
            mensaje = "Debes completar todos los campos.";
        }

        return mensaje;
    }

    @Override
    public List<Habitacion> getHabitaciones() {
        return habitacionController.findHabitacionEntities();
    }

    @Override
    public void mostrarHabitaciones(List<Habitacion> habitaciones, DefaultTableModel dtmHabitaciones) {

        dtmHabitaciones.setRowCount(0);

        for (Habitacion habitacion : habitaciones) {
            dtmHabitaciones.addRow(new Object[]{habitacion.getId(), habitacion.getNumero(), habitacion.getTipo(), habitacion.getEstado(), habitacion.getDetalles(), habitacion.getPrecioDia()});
        }
    }

    @Override
    public int getCantHabitacionesEstado(String estado) {
        return habitacionController.getCountHabitacionesByState(estado);
    }

    @Override
    public void contabilizarEstadosHabitaciones(JLabel lblRegistradas, JLabel lblDisponibles, JLabel lblOcupadas, JLabel lblLimpieza, JLabel lblReparación) {

        lblRegistradas.setText("Registradas (" + getHabitaciones().size() + ")");
        lblDisponibles.setText("Disponibles (" + getCantHabitacionesEstado("Disponible") + ")");
        lblOcupadas.setText("Ocupadas (" + getCantHabitacionesEstado("Ocupada") + ")");
        lblLimpieza.setText("Limpieza (" + getCantHabitacionesEstado("Limpieza") + ")");
        lblReparación.setText("Reparación (" + getCantHabitacionesEstado("Reparación") + ")");
    }

    @Override
    public List<Habitacion> ordenarHabitaciones(String campo) {
        return habitacionController.habitacionesOrderBy(campo);
    }

    @Override
    public void agregarDatosTablaHabitaciones(List<Habitacion> habitaciones, DefaultTableModel dtmHabitaciones) {

        if (habitaciones.isEmpty()) {
            habitaciones = getHabitaciones();
        }
        mostrarHabitaciones(habitaciones, dtmHabitaciones);
    }

    @Override
    public List<Habitacion> getHabitacionesPorValorEstado(String valor) {
        return habitacionController.getHabitacionesByValorEstado(valor);
    }

    @Override
    public void cargarHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles, JComboBox jcbNroHabitacion) {

        for (Habitacion habitacion : habitacionesDisponibles) {
            jcbNroHabitacion.addItem(habitacion.getNumero());
        }
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
}
