package Negocio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    public CellRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setHorizontalAlignment(SwingConstants.CENTER);

        //Si las filas son pares, se cambia el color a gris
        if (row % 2 == 0) {
            setBackground(new Color(255, 255, 255));
        } else {
            setBackground(new Color(200, 200, 200));
        }
        return this;
    }
}
