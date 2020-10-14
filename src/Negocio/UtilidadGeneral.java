package Negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UtilidadGeneral {

    public UtilidadGeneral() {
    }

    public static boolean esNumerico(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String getFechaActual() {
        Date date = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(date);
    }

    public static String getHoraActual() {
        Date date = new Date();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(date);
    }

    public static String DateToString(Date fecha) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = dateFormat.format(fecha);

        return fechaString;
    }

    public static long calcularDiasEntreFechas(Date fechaEntrada, Date fechaSalida) {

        long tiempoMilisegundos = fechaSalida.getTime() - fechaEntrada.getTime();
        long dias = TimeUnit.DAYS.convert(tiempoMilisegundos, TimeUnit.MILLISECONDS);
        return dias;
    }
}
