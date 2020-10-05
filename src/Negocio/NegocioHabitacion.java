package Negocio;

import Datos.HabitacionJpaController;
import Datos.exceptions.NonexistentEntityException;
import Negocio.Entidades.Habitacion;
import Negocio.Interfaces.IHabitacion;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class NegocioHabitacion implements IHabitacion {

    private HabitacionJpaController habitacionController;

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

        for (int i = 0; i < habitaciones.size(); i++) {
            dtmHabitaciones.addRow(new Object[]{habitaciones.get(i).getId(), habitaciones.get(i).getNumero(), habitaciones.get(i).getTipo(),
                habitaciones.get(i).getEstado(), habitaciones.get(i).getDetalles(), habitaciones.get(i).getPrecioDia()});
        }
    }

    @Override
    public int getCantHabitacionesEstado(String estado) {
        return habitacionController.getHabitacionesByState(estado);
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
    public void agregarDatosTablaHabitaciones(List<Habitacion> habitaciones, NegocioHabitacion negocioHabitacion, DefaultTableModel dtmHabitaciones) {

        if (habitaciones.isEmpty()) {
            habitaciones = negocioHabitacion.getHabitaciones();
        }
        negocioHabitacion.mostrarHabitaciones(habitaciones, dtmHabitaciones);
    }
}
