package Negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
