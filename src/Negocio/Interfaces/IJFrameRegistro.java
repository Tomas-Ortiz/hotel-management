package Negocio.Interfaces;

import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

public interface IJFrameRegistro {

    public void setTamañoPanelRegistro(JPanel jpRegistro);

    public void agregarComponentesPanelRegistro(JPanel jpRegistro, List<JComponent> componentesPanel);

}
