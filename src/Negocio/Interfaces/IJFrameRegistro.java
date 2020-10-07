package Negocio.Interfaces;

import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

public interface IJFrameRegistro {

    void setTamañoPanelRegistro(JPanel jpRegistro);

    void agregarComponentesPanelRegistro(JPanel jpRegistro, List<JComponent> componentesPanel);

}
