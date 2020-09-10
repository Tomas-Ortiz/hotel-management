package Negocio;

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

}
