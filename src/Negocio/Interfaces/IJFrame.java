package Negocio.Interfaces;

import java.awt.Container;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface IJFrame extends IJFrameLogin, IJFrameRegistro {

    public void configurarFrame(String titulo, JFrame frame);
    
    public void setPanelTitulo(JPanel panel,JLabel label, Container contentPane);

    public void setConfiguracionPanel(JPanel panel);

    public void setConfiguracionPanelTitulo(JPanel panel);

    public Rectangle getLimitesMaxPantalla();
    
    public void guardarPanelesPrincipal(JPanel habitaciones, JPanel clientes, JPanel reservas, JPanel productos);
    
    public void activarPanelPrincipal(boolean activarHabitacion, boolean activarCliente, boolean activarReservas, boolean activarProductos);
}
