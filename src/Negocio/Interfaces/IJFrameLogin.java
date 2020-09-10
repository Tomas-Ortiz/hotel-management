package Negocio.Interfaces;

import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

public interface IJFrameLogin {

    public void setTamañoPanelLogin(JPanel jpInicioSesion);

    public void agregarComponentesPanelLogin(JPanel jpInicioSesion, List<JComponent> componentesPanel);

}
