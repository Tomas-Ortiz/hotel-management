package Negocio.Interfaces;

import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

public interface IJFrameLogin {

    void setTamañoPanelLogin(JPanel jpInicioSesion);

    void agregarComponentesPanelLogin(JPanel jpInicioSesion, List<JComponent> componentesPanel);

}
