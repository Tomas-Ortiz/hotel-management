package Negocio;

import java.awt.Image;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UtilidadIcono {

    public static void cargarIconoLabel(Image icono, JLabel label, int ancho, int alto) {
        ImageIcon iconoRedimensionado = new ImageIcon(icono.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        label.setIcon(iconoRedimensionado);
    }

    public static void cargarIconoButton(Image icono, JButton button, int ancho, int alto) {
        ImageIcon iconoRedimensionado = new ImageIcon(icono.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        button.setIcon(iconoRedimensionado);
    }

    public static void cargarIconoButtonRedimensionable(Image icono, JButton button, int ancho, int alto) {
        ImageIcon iconoRedimensionado = new ImageIcon(icono.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        button.setIcon(iconoRedimensionado);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING);
    }

    public static void cargarIconoFrame(JFrame frame) {
        Image iconoFrame = new ImageIcon("Recursos\\Iconos\\iconoFrame.png").getImage();
        frame.setIconImage(iconoFrame);
    }
}
