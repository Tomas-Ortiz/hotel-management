package Negocio;

import Datos.ReservaJpaController;
import Negocio.Entidades.Habitacion;
import Negocio.Entidades.Reserva;
import Negocio.Interfaces.IReserva;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class NegocioReserva implements IReserva {
    
    ReservaJpaController reservaController;
    
    public NegocioReserva() {
        reservaController = new ReservaJpaController();
    }
    
    @Override
    public void crearReserva(Reserva reserva) {
        if (reserva != null) {
            reservaController.create(reserva);
        }
    }
    
    @Override
    public String validarReserva(JDateChooser jdcFechaEntrada, JDateChooser jdcFechaSalida, TimePicker tpHoraEntrada, TimePicker tpHoraSalida) {
        
        String mensaje = "ok";
        
        if (jdcFechaEntrada.getDate() == null || jdcFechaSalida.getDate() == null || tpHoraEntrada.getText().equals("") || tpHoraSalida.getText().equals("")) {
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
}
