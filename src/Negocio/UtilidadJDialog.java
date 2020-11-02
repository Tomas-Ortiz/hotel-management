package Negocio;

import javax.swing.JDialog;

public class UtilidadJDialog {

    public static void configurarJDialog(JDialog dialog) {
        dialog.setTitle("Agregar producto");
        dialog.setLocationRelativeTo(null);
        dialog.setLocation(500, 300);
        dialog.setResizable(false);
        dialog.setSize(420, 185);
    }
}
