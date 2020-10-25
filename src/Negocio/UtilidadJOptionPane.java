package Negocio;

import javax.swing.JOptionPane;

public class UtilidadJOptionPane {

    public static void mostrarMensajeInformacion(String mensaje, String titulo) {
        JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarMensajeError(String mensaje, String titulo) {
        JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }

    public static int mostrarMensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    public static int mostrarMensajePreguntaYesNo(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    public static int mostrarMensajePreguntaOkCancel(String mensaje, String titulo) {
        return JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
    }

    public static int mostrarMensajePreguntaPersonalizada(String mensaje, String titulo, Object[] botones) {
        return JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);

    }
}
