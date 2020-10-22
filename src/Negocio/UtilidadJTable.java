package Negocio;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class UtilidadJTable {

    private static UtilidadJTable utilidadJTable;

    public static UtilidadJTable getUtilidadJTable() {
        if (utilidadJTable == null) {
            return utilidadJTable = new UtilidadJTable();
        }
        return utilidadJTable;
    }

    public void centrarElementosTable(JTable table) {
        DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
        dtc.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(dtc);
            table.getColumnModel().getColumn(i).setCellRenderer(dtc);
        }
    }
}
