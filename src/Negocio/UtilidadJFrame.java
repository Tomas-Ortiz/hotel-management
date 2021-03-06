package Negocio;

import Negocio.Interfaces.IJFrame;
import Presentacion.frmPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class UtilidadJFrame implements IJFrame {

    private int panelX, panelY, ancho, alto;
    private JPanel jpHabitaciones, jpClientes, jpReservas, jpProductos, jpInicio;

    private static UtilidadJFrame utilidadJframe;

    public static UtilidadJFrame getUtilidadFrame() {
        if (utilidadJframe == null) {
            utilidadJframe = new UtilidadJFrame();
        }
        return utilidadJframe;
    }

    @Override
    public void configurarFrame(String titulo, JFrame frame) {
        frame.setTitle(titulo);
        Rectangle limitesMaximos = getLimitesMaxPantalla();
        frame.setSize(limitesMaximos.width, limitesMaximos.height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        if (frame instanceof frmPrincipal) {
            frame.getContentPane().setBackground(Color.WHITE);
        } else {
            frame.getContentPane().setBackground(new Color(164, 221, 234));
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public Rectangle getLimitesMaxPantalla() {
        Rectangle limitesMaximos = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return limitesMaximos;
    }

    @Override
    public void setTamañoPanelLogin(JPanel jpInicioSesion) {
        panelX = (int) ((getLimitesMaxPantalla().width) / 2.69);
        panelY = (getLimitesMaxPantalla().height) / 4;

        ancho = (int) ((getLimitesMaxPantalla().width) / 4);
        alto = (int) ((getLimitesMaxPantalla().height) / 2);

        jpInicioSesion.setBounds(panelX, panelY, ancho, alto);
    }

    @Override
    public void setTamañoPanelRegistro(JPanel jpRegistro) {
        panelX = (int) ((getLimitesMaxPantalla().width) / 2.69);
        panelY = (int) ((getLimitesMaxPantalla().height) / 8.5);

        ancho = (int) ((getLimitesMaxPantalla().width) / 4);
        alto = (int) ((getLimitesMaxPantalla().height) / 1.35);

        jpRegistro.setBounds(panelX, panelY, ancho, alto);
    }

    @Override
    public void setConfiguracionPanel(JPanel panel) {

        panel.setBackground(Color.white);

        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        Border colorBorde = panel.getBorder();
        Border margenBorde = new EmptyBorder(15, 15, 15, 15);

        panel.setBorder(new CompoundBorder(colorBorde, margenBorde));
    }

    @Override
    public void setConfiguracionPanelTitulo(JPanel panel) {
        panel.setBackground(Color.black);
        panel.setBounds(0, 0, getLimitesMaxPantalla().width, 75);
    }

    @Override
    public void agregarComponentesPanelLogin(JPanel jpInicioSesion, List<JComponent> componentesPanel) {
        for (Component c : componentesPanel) {
            jpInicioSesion.add(c);
        }
    }

    @Override
    public void agregarComponentesPanelRegistro(JPanel jpRegistro, List<JComponent> componentesPanel) {
        for (Component c : componentesPanel) {
            jpRegistro.add(c);
        }
    }

    @Override
    public void setPanelTitulo(JPanel panel, JLabel label, Container contentPane) {

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.white);
        panel.add(label);

        setConfiguracionPanelTitulo(panel);

        contentPane.add(panel, BorderLayout.CENTER);
    }

    @Override
    public void guardarPanelesPrincipal(JPanel habitaciones, JPanel clientes, JPanel reservas, JPanel productos, JPanel inicio) {
        this.jpHabitaciones = habitaciones;
        this.jpClientes = clientes;
        this.jpReservas = reservas;
        this.jpProductos = productos;
        this.jpInicio = inicio;
    }

    @Override
    public void activarPanelPrincipal(boolean activarHabitacion, boolean activarCliente, boolean activarReservas, boolean activarProductos, boolean activarInicio) {

        jpHabitaciones.setVisible(activarHabitacion);
        jpClientes.setVisible(activarCliente);
        jpReservas.setVisible(activarReservas);
        jpProductos.setVisible(activarProductos);
        jpInicio.setVisible(activarInicio);
    }

    @Override
    public void centrarHorizontalLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
