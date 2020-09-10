package Negocio.Interfaces;

import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface IJFrame extends IJFrameLogin, IJFrameRegistro {

    public void configurarFrame(String titulo, JFrame frame);

    public void setConfiguracionPanel(JPanel panel);

    public void setConfiguracionPanelTitulo(JPanel panel);

    public Rectangle getLimitesMaxPantalla();
}
