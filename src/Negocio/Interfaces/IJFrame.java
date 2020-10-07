package Negocio.Interfaces;

import java.awt.Container;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface IJFrame extends IJFrameLogin, IJFrameRegistro {

    void configurarFrame(String titulo, JFrame frame);

    void setPanelTitulo(JPanel panel, JLabel label, Container contentPane);

    void setConfiguracionPanel(JPanel panel);

    void setConfiguracionPanelTitulo(JPanel panel);

    Rectangle getLimitesMaxPantalla();

    void guardarPanelesPrincipal(JPanel habitaciones, JPanel clientes, JPanel reservas, JPanel productos, JPanel inicio);

    void activarPanelPrincipal(boolean activarHabitacion, boolean activarCliente, boolean activarReservas, boolean activarProductos, boolean activarInicio);
}
